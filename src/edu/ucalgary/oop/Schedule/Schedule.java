/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 2.10.0
 * @since 1.7.0
 */

package edu.ucalgary.oop.Schedule;
import java.util.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;
import edu.ucalgary.oop.Medical.*;
import edu.ucalgary.oop.Animals.*;

public class Schedule {
    
    // ImportData Object For Storing The Data Compiled From The Database
    // Copies Of The HashMaps / ArrayList In The ImportData Class
    // Schedule Holds Hour, ArrayList Of Tasks
    private ImportData importData = new ImportData(); 
    private HashMap<Integer, Animal> animalHashMap = new HashMap<>();
    private HashMap<Integer, Tasks> tasksHashMap = new HashMap<>();
    private ArrayList<Treatments> treatmentsArrayList = new ArrayList<>();


    // Maps each hour of the day to a list of scheduled tasks (Items).
    // Keeps track of which animals have had their food prepared at each hour.
    // Maps each hour to the remaining available time for scheduling tasks.
    // Stores the maximum available time for each hour, allowing for adjustments.
    private HashMap<Integer, ArrayList<Item>> schedule = new HashMap<>();
    private HashMap<Integer, HashMap<String, Boolean>> foodPrepFeeding = new HashMap<>();
    private HashMap<Integer, Integer> timeAvailability = new HashMap<>(24);
    private HashMap<Integer, Integer> maxTimeAvailability = new HashMap<>(24);

    //The entire schedule formatted as a string to then be displayed via GUI and written to the .txt file
    private String scheduleString;

    /**
     * Initializes the Schedule by connecting to the database with provided credentials and loading animal, task, and treatment data.
     * Sets up default tasks for cage cleaning and special porcupine cage cleaning, and initializes the daily schedule with time availability.
     * @param username
     * @param password 
     * @throws IllegalArgumentException
     */

     public Schedule(String username, String password) throws IllegalArgumentException {
        importData.establishDBConnection(username, password);
        
        try {
            animalHashMap = importData.importAnimalsTable();
            tasksHashMap = importData.importTasksTable();
            treatmentsArrayList = importData.importTreatmentsTable();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    
        tasksHashMap.put(-2, new Tasks(-2, "Cage Cleaning", 5, 24));
        tasksHashMap.put(-1, new Tasks(-1, "Porcupine Cage Cleaning", 10, 24));
        
        for(int i = 0; i < 24; i++) {
            timeAvailability.put(i, 60);
            maxTimeAvailability.put(i, 60);
            schedule.put(i, null);
        } 
       
    }
 
    /**
     * Schedules all tasks by prioritizing their flexibility and handles unscheduled tasks by either adding a volunteer
     * or prompting for rescheduling. Finally, generates the text schedule.
     */

    public void createSchedule() {
        String[] options = {"NO", "YES"};
        ArrayList<Item> items = new ArrayList<>();
        items.addAll(addMedical());
        items.addAll(addCage());
        items.addAll(addFeeding());
        // items.sort(Comparator.comparingInt(Item::getMaxWindow));
        
        for (Item item : items) {
            if (!addItem(item)) {
                int volunteerHour = findHourForVolunteer(item);
                if (volunteerHour >= 0) {
                    handleVolunteerRequired(volunteerHour, item, options);
                } else {
                    promptReschedule(item);
                }
            }
        }
        createScheduleString();
        createTextSchedule();
        createGUIschedule();
    }

    /**
     * Finds the hour during which a volunteer is available within the specified time window.
     * @param item The item representing the task with start hour and maximum window.
     * @return The hour available for volunteering, or -1 if no hour is available.
     */

     private int findHourForVolunteer(Item item) {
        for (int i = item.getStartHour(); i < item.getStartHour() + item.getMaxWindow(); i++) {
            if (maxTimeAvailability.get(i) == 60) {
                return i;
            }
        }
        return -1; // No hour available for volunteer
    }

    /**
     * Handles the scenario where a backup volunteer is required for a specific hour.
     * It displays a dialog asking whether to add a volunteer for the hour. If confirmed,
     * it adjusts the time availability accordingly and attempts to add the item to the schedule.
     * If declined, it prompts the user to reschedule the item.
     * @param volunteerHour The hour requiring a backup volunteer.
     * @param item The schedule item that needs to be added or rescheduled.
     * @param options Array of options presented to the user in the dialog.
     */

    private void handleVolunteerRequired(int volunteerHour, Item item, String[] options) {
        int selectedValue = JOptionPane.showOptionDialog(null,
                "A backup volunteer is required for " + volunteerHour, "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (selectedValue == 1) {
            adjustTimeAvailability(volunteerHour);
            item.setNeedsVolunteer(true);
            addItem(item);
        } else {
            promptReschedule(item);
        }
    }


    /**
     * Adjusts the time availability for a specific hour to account for an added volunteer.
     * Increases the maximum time availability to 120 minutes and adds an additional 60 minutes
     * to the current available time for the specified hour.
     * @param hour The hour at which to adjust time availability.
     */

    private void adjustTimeAvailability(int hour) {
        maxTimeAvailability.put(hour, 120);
        timeAvailability.put(hour, timeAvailability.get(hour) + 60);
    }

    /**
     * Prompts the user to reschedule a task to a new start hour based on available time slots.
     * If the user inputs a valid new start hour with full availability (60 minutes), the item is rescheduled,
     * and the treatment start hour in the database is updated. If the input is invalid, the user is prompted again.
     * @param item The item to be rescheduled, containing its current scheduling details and treatment ID.
     */

    private void promptReschedule(Item item) {
        String timesAvailable = getTimeAvailabilityForRescheduling();
        String userInput = JOptionPane.showInputDialog(null,
                "Please reschedule the task to a different time: " + timesAvailable);
        try {
            int newStartHour = Integer.parseInt(userInput);
            if (timeAvailability.containsKey(newStartHour) && timeAvailability.get(newStartHour) == 60) {
                item.setStartHour(newStartHour);
                addItem(item);
                importData.updateTreatmentStartHour(item.getTreatmentID(), newStartHour);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input, try again.");
                promptReschedule(item); // Recursive call to prompt again
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input, try again.");
            promptReschedule(item); // Recursive call to prompt again
        }
    }

    /**
     * Generates a string listing all hours where the full time slot (60 minutes) is available for rescheduling.
     * @return A trimmed string of hours with full availability, separated by spaces.
     */

    private String getTimeAvailabilityForRescheduling() {
        StringBuilder times = new StringBuilder();
        for (Integer hour : timeAvailability.keySet()) {
            if (timeAvailability.get(hour) == 60) {
                times.append(hour).append(" ");
            }
        }
        return times.toString().trim();
    }

    /**
     * Attempts to add a scheduled item to the timetable, adjusting for animal-specific preparation needs.
     * It iterates through available hours within the item's maximum window, adding extra prep time for certain animals.
     * The item is added to the first suitable hour slot, updating time availability accordingly.
     * @param item The item to be scheduled, containing details like start hour, duration, and animal ID.
     * @return boolean True if the item is successfully added within its window, false if no suitable slot is found.
     */

     public boolean addItem(Item item) {
        int duration = item.getDuration();
        int priority = item.getMaxWindow();
    
        for (int hour = item.getStartHour(); hour < item.getStartHour() + item.getMaxWindow(); hour++) {
            if (item.getTaskID() == 0) {
                duration += adjustDurationForAnimalPrep(hour, item.getAnimalID());
            }
            int newTimeAvailability = timeAvailability.getOrDefault(hour, 0) - duration;
            if (newTimeAvailability >= 0) {
                schedule.computeIfAbsent(hour, k -> new ArrayList<>()).add(item);
                timeAvailability.put(hour, newTimeAvailability);
                return true;
            } else {
                priority--;
                if (priority <= 0) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Calculates additional prep time for feeding based on animal type at a given hour, updating the prep status.
     * Adds 5 minutes for Foxes and 10 minutes for Coyotes if not already accounted for, returning the added time.
     * @param hour The schedule hour to check for food preparation.
     * @param animalID The identifier of the animal.
     * @return The additional time required for preparation, if any.
     */

    private int adjustDurationForAnimalPrep(int hour, int animalID) {
        Object animal = animalHashMap.get(animalID);
        HashMap<String, Boolean> foodPrep = foodPrepFeeding.getOrDefault(hour, new HashMap<>());
        int additionalDuration = 0;
    
        if (animal instanceof Fox && foodPrep.putIfAbsent("fox", true) == null) {
            additionalDuration = 5;
        } else if (animal instanceof Coyote && foodPrep.putIfAbsent("coyote", true) == null) {
            additionalDuration = 10;
        }
    
        if (additionalDuration > 0) {
            foodPrepFeeding.put(hour, foodPrep);
        }
    
        return additionalDuration;
    }

    /**
     * Generates a list of medical treatment items for animals, each item detailing the treatment schedule and requirements.
     * Updates animal kit status for specific treatments and returns an ArrayList of these items.
     * @return medicalItems
     */
    
     public ArrayList<Item> addMedical() {

        ArrayList<Item> medicalItems = new ArrayList<>();
        for(Treatments treatment : treatmentsArrayList) {
            int animalID = treatment.getAnimalID();
            int taskID = treatment.getTaskID();
            int startHour = treatment.getStartHour();
            int treatmentID = treatment.getTreatmentID();

            if (taskID == 1) {
                animalHashMap.get(animalID).setKitStatus();
            }
            
            int maxWindow = tasksHashMap.get(taskID).getMaxWindow();
            int duration = tasksHashMap.get(taskID).getDuration();
    
            Item medicalItem = new Item(animalID, taskID, startHour, maxWindow, duration, treatmentID);
            medicalItems.add(medicalItem);
            
        }
        return medicalItems;
    }

    /**
     * Prepares a list of cage cleaning tasks, with special consideration for porcupines, and returns the compiled items.
     * @return A list of cage cleaning tasks differentiated by animal type.
     */

    public ArrayList<Item> addCage() {
        ArrayList<Item> cageItems = new ArrayList<>();
        for (int animalID : animalHashMap.keySet()) {
            Item cageCleaning;
            if (animalHashMap.get(animalID) instanceof Porcupine) {
                cageCleaning = new Item(animalID, -1, 0, 24, 10);
            }
            else {
                cageCleaning = new Item(animalID, -2, 0, 24, 5);
            }
        cageItems.add(cageCleaning);
        }
        return cageItems;
    }

    /**
     * Collects feeding tasks for animals without a kit status, creating an item for each and compiling them into a list.
     * @return A list of feeding items for animals ready for feeding.
     */

    public ArrayList<Item> addFeeding() {
        ArrayList<Item> feedingItems = new ArrayList<>();
        for (int animalID : animalHashMap.keySet()) {
            if (animalHashMap.get(animalID).getKitStatus()) {
                continue;
            }
            Item feedingItem = animalHashMap.get(animalID).feeding();
            feedingItems.add(feedingItem);
        }
        return feedingItems;
    }

    /**
     * Formats an item into a readable string that includes the task description, animal nickname, and duration.
     * @param item The item to be formatted.
     * @return A formatted string representing the item's details.
     */

    public String formatItem(Item item, int nickNameWidth, int descriptionWidth) {
        String nickName = animalHashMap.get(item.getAnimalID()).getNickName();
        if (item.getNeedsVolunteer()) {
            nickName += " (+ Volunteer)";
        }
        String description = (item.getTaskID() == 0) ? "Feeding" : tasksHashMap.get(item.getTaskID()).getDescription();
        int duration = item.getDuration();
        return String.format("%-" + nickNameWidth + "s%-"+ descriptionWidth + "s%5d mins", nickName, description, duration);
    }

    /**
     * Writes the daily schedule of tasks to "Schedule.txt", detailing each hour's assignments or marking it as empty.
     * For each hour with scheduled tasks, it formats the tasks for readability and outputs them to the file.
     */

    public void createTextSchedule() {
        String fileName = "Schedule.txt";
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {

            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(this.scheduleString);
        } catch (IOException e) {
            System.err.println("Error writing to Schedule.txt: " + e.getMessage());
        }finally{
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if(fileWriter != null){
                    fileWriter.close();
                }
                
            }catch(IOException e){
                System.err.println("Excetption occured when trying to close schedule.txt");
            }
        }
    }

    /**
     * Displays the daily schedule in a GUI dialog. It lists tasks for each hour or marks them as empty.
     * The schedule is shown in a non-editable, scrollable text area within a JOptionPane.
     */

    public void createGUIschedule() {
        
       
        JTextArea textArea = new JTextArea(scheduleString);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Schedule", JOptionPane.DEFAULT_OPTION);   
    }

    /*
     * @param none.
     * @return a formatted string that represents the schedule
     */
    public void createScheduleString(){
        StringBuilder scheduleBuilder = new StringBuilder();
    
        for (Map.Entry<Integer, ArrayList<Item>> entry : schedule.entrySet()) {
            int hour = entry.getKey();
            scheduleBuilder.append("Hour: ").append(hour).append("\n");
            
            ArrayList<Item> items = entry.getValue();
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
                    scheduleBuilder.append(formatItem(item, 30, 25)).append("\n");
                }
            } else {
                scheduleBuilder.append("Empty\n");
            }
            scheduleBuilder.append("\n");
        }
        this.scheduleString = scheduleBuilder.toString();
    }
}
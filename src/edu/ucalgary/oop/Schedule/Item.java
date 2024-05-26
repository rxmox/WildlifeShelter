package edu.ucalgary.oop.Schedule;

/**
 * Represents a schedule item for tracking and managing tasks associated with animal care
 * in a wildlife rescue center. Each item combines information about an animal, a specific task,
 * and optionally a treatment, along with timing information such as when the task should start,
 * its maximum allowable duration, and how long it is expected to take.
 * <p>
 * The class enforces validation rules to ensure that timing information is logical and feasible,
 * supporting the creation of schedule items that accurately reflect the needs and constraints
 * of animal care activities.
 * </p>
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.7.0
 * @since 1.5.0
 */
public class Item {
    private int animalID;
    private int taskID;
    private int startHour;
    private int maxWindow;
    private int duration;
    private int treatmentID;
    private boolean needsVolunteer = false;

    /**
     * Constructs a new Item without specifying a treatment ID. This constructor is suitable
     * for schedule items where a treatment is not applicable.
     *
     * @param animalID  The unique identifier of the animal this item is related to.
     * @param taskID    The unique identifier of the task to be performed.
     * @param startHour The hour of the day (0-23) when the task is scheduled to start.
     * @param maxWindow The maximum duration in hours within which the task should be completed.
     * @param duration  The expected duration of the task in hours.
     * @throws IllegalArgumentException If any timing parameters are outside their valid ranges.
     */
    public Item(int animalID, int taskID, int startHour, int maxWindow, int duration) {
        this(animalID, taskID, startHour, maxWindow, duration, 0); // Delegates to the main constructor.
    }

    /**
     * Constructs a new Item, including a treatment ID. This constructor is used when the schedule
     * item is directly related to a specific treatment.
     *
     * @param animalID    The unique identifier of the animal this item is related to.
     * @param taskID      The unique identifier of the task to be performed.
     * @param startHour   The hour of the day (0-23) when the task is scheduled to start.
     * @param maxWindow   The maximum duration in hours within which the task should be completed.
     * @param duration    The expected duration of the task in hours.
     * @param treatmentID The unique identifier of the treatment this item is associated with, if any.
     * @throws IllegalArgumentException If any timing parameters are outside their valid ranges.
     */
    public Item(int animalID, int taskID, int startHour, int maxWindow, int duration, int treatmentID) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be non-negative.");
        }
        if (maxWindow < 0 || maxWindow > 24) {
            throw new IllegalArgumentException("Max window must be between 0 and 24 hours.");
        }
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException("Start hour must be between 0 and 23.");
        }

        this.animalID = animalID;
        this.taskID = taskID;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
        this.treatmentID = treatmentID;
    }

    // Getter methods with straightforward documentation; their purpose is clear.
    
    /**
     * Updates the start hour for this item. The start hour indicates when the task is scheduled
     * to begin and must fall within the range of a typical 24-hour day.
     *
     * @param startHour The new start hour, must be between 0 and 23 inclusive.
     * @throws IllegalArgumentException If the provided start hour is outside the valid range.
     */
    public void setStartHour(int startHour) throws IllegalArgumentException {
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException("Start hour must be between 0 and 23.");
        }
        this.startHour = startHour;
    }

    /**
     * Updates volunteer flag if a schedule hour conflict
     * User must choose to select a volunteer for the conflict
     * @param needsVolunteer
     */
    public void setNeedsVolunteer(boolean needsVolunteer) { 
        this.needsVolunteer = needsVolunteer;
    }

    // Getters
    public int getAnimalID() { return animalID; }
    public int getTaskID() { return taskID; }
    public int getStartHour() { return startHour; }
    public int getMaxWindow() { return maxWindow; }
    public int getDuration() { return duration; }
    public int getTreatmentID() { return treatmentID; }
    public boolean getNeedsVolunteer() { return needsVolunteer; }
}

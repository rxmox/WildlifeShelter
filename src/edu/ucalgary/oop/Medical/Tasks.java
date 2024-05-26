package edu.ucalgary.oop.Medical;

/**
 * Represents a task associated with animal treatment.
 * This class is used to define and manage tasks related to the treatments of animals, such as medication administration,
 * feeding schedules, or any other treatment-related activities. It ensures tasks are clearly defined with a unique
 * identifier, a description, a maximum allowable time window, and a duration.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.4.0
 * @since 1.2.0
 */
public class Tasks {
    
    private final int TASKID;
    private final String DESCRIPTION;
    private final int MAXWINDOW; // Maximum time window in hours within which the task must be completed.
    private final int DURATION; // Duration in hours that the task is expected to take.

    /**
     * Constructs a new {@code Tasks} object with specified details. Ensures that the duration and maximum window
     * are within logical bounds before assignment.
     *
     * @param taskID the unique identifier for the task
     * @param description a descriptive statement of the task
     * @param duration the time in hours the task is expected to take, must be non-negative
     * @param maxWindow the maximum time window in hours within which the task must be completed, between 0 and 24
     * @throws IllegalArgumentException if {@code duration} is negative or {@code maxWindow} is not within the 0-24 range,
     * indicating an invalid time frame for task completion or duration.
     */
    public Tasks(int taskID, String description, int duration, int maxWindow) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be non-negative.");
        }
        if (maxWindow < 0 || maxWindow > 24) {
            throw new IllegalArgumentException("Max window must be between 0 and 24.");
        }

        this.TASKID = taskID;
        this.DESCRIPTION = description;
        this.MAXWINDOW = maxWindow;
        this.DURATION = duration;
    }
    
    /**
     * Returns the unique task ID.
     * 
     * @return the task ID as an integer
     */
     public int getTaskID() {
        return TASKID;
    }

    /**
     * Returns the description of the task.
     * 
     * @return the task description as a String
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Returns the maximum time window allowed for completing the task.
     * 
     * @return the max window as an integer (hours)
     */
     public int getMaxWindow() {
        return MAXWINDOW;
    }

    /**
     * Returns the duration of the task.
     * 
     * @return the duration as an integer (hours)
     */
     public int getDuration() {
        return DURATION;
    }
}

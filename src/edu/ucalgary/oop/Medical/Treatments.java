package edu.ucalgary.oop.Medical;

/**
 * Manages the assignment of treatments to animals. Each treatment is uniquely identified and
 * associated with a specific animal and task, along with a designated start hour for when the treatment should begin.
 * This class is crucial for scheduling and tracking the treatment processes for animals, ensuring timely and organized
 * care.
 *
 * <p>The class enforces validation on the start hour to ensure it falls within a 24-hour day format, throwing an
 * IllegalArgumentException for values outside of 0-23 hours range. This validation is applied both during instantiation
 * and when setting a new start hour.</p>
 *
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.5.0
 * @since 1.2.0
 */
public class Treatments {
    
    private final int TREATMENTID;
    private final int ANIMALID;
    private final int TASKID;
    private int startHour; // Hour of the day (0-23) when the treatment should start.

    /**
     * Constructs a Treatments object with specified details. Validates the start hour to ensure it's within a
     * 24-hour format.
     *
     * @param treatmentID The unique identifier for the treatment.
     * @param animalID    The identifier for the animal receiving the treatment.
     * @param taskID      The identifier for the task associated with this treatment.
     * @param startHour   The hour of the day (0-23) when the treatment is scheduled to start.
     * @throws IllegalArgumentException if the startHour is outside the 0-23 range.
     */
    public Treatments(int treatmentID, int animalID, int taskID, int startHour) {
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException("Start hour must be between 0 and 23.");
        }
        
        this.TREATMENTID = treatmentID;
        this.ANIMALID = animalID;
        this.TASKID = taskID;
        this.startHour = startHour;
    }

    /**
     * Returns the unique treatment ID.
     * 
     * @return The treatment ID.
     */
     public int getTreatmentID() {
        return TREATMENTID;
    }

    /**
     * Returns the ID of the animal receiving the treatment.
     * 
     * @return The animal ID.
     */
    public int getAnimalID() {
        return ANIMALID;
    }

    /**
     * Returns the ID of the task associated with the treatment.
     * 
     * @return The task ID.
     */
    public int getTaskID() {
        return TASKID;
    }

    /**
     * Returns the start hour for the treatment.
     * 
     * @return The start hour, within the range of 0-23.
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Sets a new start hour for the treatment, validating that it is within the 24-hour day format.
     * 
     * @param startHour The new start hour for the treatment.
     * @throws IllegalArgumentException if the startHour is outside the 0-23 range.
     */
    public void setStartHour(int startHour) {
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException("Start hour must be between 0 and 23.");
        }
        this.startHour = startHour;
    }
}

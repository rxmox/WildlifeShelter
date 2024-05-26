package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents an animal in the Wildlife Shelter. This class encapsulates details such as
 * the animal's unique identifier, nickname, species, and provides a structured way to manage animal information
 * and their basic care needs, such as feeding schedules.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.4.0
 * @since 1.0.0
 */
public class Animal {
    private final int ID;  // Unique identifier for each animal.
    private final String nickName;  // Animal's nickname.
    private final String animalSpecies; // Species of the animal.
    private boolean kitStatus = false; // Kit status of the animal.

    /**
     * Constructs an Animal instance with specified ID, nickname, and species. Validates the input
     * to ensure they are not negative, null, or empty.
     *
     * @param ID the unique identifier for the animal. Must be non-negative.
     * @param nickName the nickname of the animal. Cannot be null or empty.
     * @param animalSpecies the species of the animal. Cannot be null or empty.
     * @throws IllegalArgumentException if any input is invalid (ID < 0, nickName or animalSpecies is null/empty).
     */
    public Animal(int ID, String nickName, String animalSpecies) {
        if (ID < 0)
            throw new IllegalArgumentException("ID must be non-negative.");
        if (nickName == null || nickName.trim().isEmpty())
            throw new IllegalArgumentException("Nickname cannot be null or empty.");
        if (animalSpecies == null || animalSpecies.trim().isEmpty())
            throw new IllegalArgumentException("Animal species cannot be null or empty.");

        this.ID = ID;
        this.nickName = nickName;
        this.animalSpecies = animalSpecies;
    }

    /**
     * Gets the unique ID of the animal.
     *
     * @return The unique identifier for this animal.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the nickname of the animal.
     *
     * @return The nickname of the animal. Cannot be null or empty.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Gets the species of the animal.
     *
     * @return The species of the animal. Cannot be null or empty.
     */
    public String getAnimalSpecies() {
        return animalSpecies;
    }

    /**
     * Retrieves the kit status of the animal.
     *
     * @return The current status of the animal.
     */
    public boolean getKitStatus() {
        return kitStatus;
    }

    /**
     * Sets the kit status of the animal to true.
     * Note: This method can only set the status to true.
     */
    public void setKitStatus() {
        this.kitStatus = true;
    }

    /**
     * Generates a default feeding schedule item for the animal. This method is intended to be
     * overridden by subclasses to provide species-specific feeding schedules if necessary.
     *
     * @return An Item object representing the default feeding schedule for this animal, 
     *         assuming a 5-minute duration within a 3-hour window starting from midnight.
     */
    public Item feeding() {
        return new Item(this.ID, 0, 0, 3, 5);
    }
}

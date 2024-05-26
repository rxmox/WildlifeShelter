package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents a Fox, extending the {@link Animal} class with specific behaviors and properties
 * unique to foxes.
 * This class overrides the feeding behavior to account for the fox's nocturnal feeding habits,
 * setting a specific feeding schedule that starts at midnight.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */
public class Fox extends Animal {
    
    /**
     * Constructs a Fox instance with an ID, nickname, and species.
     * Initializes the Fox with the specified attributes by calling the superclass (Animal) constructor.
     * 
     * @param ID The unique identifier for the Fox.
     * @param nickName The nickname of the Fox.
     * @param animalSpecies The species of the animal, expected to be "Fox".
     */
    public Fox(int ID, String nickName, String animalSpecies) {
        super(ID, nickName, animalSpecies);
    }

    /**
     * Creates and returns an Item object with Fox-specific feeding values, reflecting its nocturnal nature.
     * This method overrides the Animal class's feeding method to provide a feeding schedule that starts at midnight.
     * 
     * @return An Item object representing the Fox's feeding schedule, starting at 12 AM, within a 3-hour window, for a duration of 5 minutes.
     */
    @Override
    public Item feeding() {
        return new Item(getID(), 0, 0, 3, 5);
    }
}

 
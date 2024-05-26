package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents a Raccoon, extending the {@link Animal} class with specific behaviors and properties
 * unique to raccoons.
 * The feeding schedule is tailored to the Raccoon's nocturnal nature,
 * beginning at midnight.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */
public class Raccoon extends Animal {
    
    /**
     * Constructs a Raccoon instance with specified ID, nickname, and species.
     * Initializes the Raccoon with the given attributes by calling the superclass (Animal) constructor.
     * 
     * @param ID Unique identifier for the Raccoon.
     * @param nickName Nickname of the Raccoon.
     * @param animalSpecies Species of the animal, expected to be "Raccoon".
     */
    public Raccoon(int ID, String nickName, String animalSpecies) {
        super(ID, nickName, animalSpecies);
    }

    /**
     * Creates and returns an Item object with Raccoon-specific feeding values, reflecting its nocturnal activity.
     * This method overrides the Animal class's feeding method to set a feeding schedule that starts at midnight (0 hour).
     * 
     * @return An Item object representing the Raccoon's feeding schedule, with a start hour of 0 (midnight), 
     *         a 3-hour feeding window, and a duration of 5 minutes.
     */
    @Override
    public Item feeding() {
        return new Item(getID(), 0, 0, 3, 5);
    }    

}

package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents a Porcupine, extending the {@link Animal} class with specific behaviors and properties
 * unique to porcupines.
 * This class provides functionality to set up a porcupine-specific feeding schedule,
 * taking into account its crepuscular activity patterns.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */
public class Porcupine extends Animal {
    
    /**
     * Constructs a Porcupine instance with specified ID, nickname, and species.
     * Utilizes the superclass constructor to initialize the basic animal properties.
     * 
     * @param ID Unique identifier for the Porcupine.
     * @param nickName Nickname of the Porcupine.
     * @param animalSpecies Species of the animal, expected to be "Porcupine".
     */
    public Porcupine(int ID, String nickName, String animalSpecies) {
        super(ID, nickName, animalSpecies);
    }

    /**
     * Creates and returns an Item object with Porcupine-specific feeding values, reflecting its crepuscular nature.
     * Overrides the Animal class's feeding method to provide a feeding schedule starting at 7 PM.
     * 
     * @return An Item object representing the Porcupine's feeding schedule, with a start hour of 7 PM, 
     *         within a 3-hour window, for a duration of 5 minutes.
     */
    @Override
    public Item feeding() {
        return new Item(getID(), 0, 19, 3, 5);
    }
}

 
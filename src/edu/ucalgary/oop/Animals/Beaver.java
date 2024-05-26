package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents a Beaver, extending the {@link Animal} class with specific behaviors and properties
 * unique to beavers.
 * This class overrides the feeding method to provide feeding schedule specifics
 * suited for a Beaver, taking into account its diurnal nature.

 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */
public class Beaver extends Animal {
    
    /**
     * Constructs a Beaver instance with the specified ID, nickname, and species. This constructor
     * simply calls the superclass constructor of {@link Animal}.
     * 
     * @param ID The unique identifier for the beaver.
     * @param nickName The nickname of the beaver.
     * @param animalSpecies The species of the animal, which should typically be "Beaver" for instances of this class.
     */
    public Beaver(int ID, String nickName, String animalSpecies) {
        super(ID, nickName, animalSpecies);
    }

    /**
     * Generates a {@link Item} object representing a beaver-specific feeding schedule. This method overrides
     * the feeding method in the superclass {@link Animal} to provide values appropriate for a beaver's diurnal
     * feeding pattern, with a feeding start time of 8 AM.
     * 
     * @return An {@link Item} object that details the beaver's feeding schedule, which includes a start hour of 8 AM,
     * a duration of 5 minutes, and a maximum time window of 3 hours.
     */
    @Override
    public Item feeding() {
        // Feeding schedule: Diurnal with a specific start hour of 8 AM.
        return new Item(getID(), 0, 8, 3, 5);
    }
}

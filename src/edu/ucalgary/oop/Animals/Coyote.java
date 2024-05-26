package edu.ucalgary.oop.Animals;

import edu.ucalgary.oop.Schedule.*;

/**
 * Represents a Coyote, extending the {@link Animal} class with specific behaviors and properties
 * unique to coyotes.
 * This class overrides the feeding method to provide feeding schedule specifics
 * suited for a Coyote, taking into account its crepuscular nature.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */
public class Coyote extends Animal {
    
    /**
     * Constructs a Coyote instance with an ID, nickname, and species.
     * 
     * @param ID The unique identifier for the Coyote.
     * @param nickName The nickname of the Coyote.
     * @param animalSpecies The species of the animal, expected to be "Coyote".
     */
    public Coyote(int ID, String nickName, String animalSpecies) {
        super(ID, nickName, animalSpecies);
    }

    /**
     * Creates and returns an {@link Item} object with Coyote-specific feeding values.
     * This method overrides the Animal class's feeding method to reflect the Coyote's
     * crepuscular activity pattern, with feeding starting at 7 PM.
     * 
     * @return An {@link Item} object representing the Coyote's feeding schedule.
     */
    @Override
    public Item feeding() {
        return new Item(getID(), 0, 19, 3, 5);
    }
}

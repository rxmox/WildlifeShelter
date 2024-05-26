package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Animals.Raccoon;
import edu.ucalgary.oop.Schedule.Item;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.0.0
 * @since 1.0.0
*/

/**
 * Test suite for the Raccoon class.
 * Validates the specific feeding behavior of Raccoons and ensures
 * that the constructor initializes Raccoon objects correctly, inheriting
 * behavior from the Animal class.
 */
public class RaccoonTest {

    /**
     * Tests the Raccoon constructor to ensure it correctly initializes a Raccoon object.
     * Verifies that the ID, nickname, and species are set properly, leveraging the constructor
     * from the Animal class.
     */
    @Test
    public void testRaccoonConstructor() {
        Raccoon raccoon = new Raccoon(1, "Bandit", "Raccoon");
        
        assertEquals("The ID should be set correctly", 1, raccoon.getID());
        assertEquals("The nickname should be set correctly", "Bandit", raccoon.getNickName());
        assertEquals("The species should be set correctly", "Raccoon", raccoon.getAnimalSpecies());
    }
    
    /**
     * Tests the feeding method to ensure it returns an Item object with Raccoon-specific
     * feeding values. Checks for a feeding schedule that starts at midnight (12 AM).
     */
    @Test
    public void testFeeding() {
        Raccoon raccoon = new Raccoon(2, "Rascal", "Raccoon");
        Item feedingSchedule = raccoon.feeding();
        
        assertEquals("The feeding schedule should start at 0 (12 AM)", 0, feedingSchedule.getStartHour());
        assertEquals("The max window should be 3 hours", 3, feedingSchedule.getMaxWindow());
        assertEquals("The duration should be 5 minutes", 5, feedingSchedule.getDuration());
        // Verifying that the Item's animalID matches the Raccoon's ID.
        assertEquals("The Item's animalID should match Raccoon's ID", 2, feedingSchedule.getAnimalID());
    }
}

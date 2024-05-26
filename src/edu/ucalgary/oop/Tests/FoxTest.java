package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Animals.Fox;
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
 * Test suite for the Fox class.
 * Validates the behavior of the Fox-specific feeding schedule and ensures
 * that the constructor properly initializes Fox objects as expected, inheriting
 * behavior from the Animal class.
 */
public class FoxTest {

    /**
     * Tests the Fox constructor to ensure it correctly initializes a Fox object.
     * Since Fox inherits its constructor from Animal, this test verifies that the 
     * inherited behavior sets the ID, nickname, and species correctly.
     */
    @Test
    public void testFoxConstructor() {
        Fox fox = new Fox(1, "Sly", "Fox");
        
        assertEquals("The ID should be set correctly", 1, fox.getID());
        assertEquals("The nickname should be set correctly", "Sly", fox.getNickName());
        assertEquals("The species should be set correctly", "Fox", fox.getAnimalSpecies());
    }
    
    /**
     * Tests the feeding method to ensure it returns an Item object with Fox-specific
     * feeding values. Specifically, it checks for a feeding schedule that starts at midnight (12 AM).
     */
    @Test
    public void testFeeding() {
        Fox fox = new Fox(2, "Red", "Fox");
        Item feedingSchedule = fox.feeding();
        
        assertEquals("The feeding schedule should start at 0 (12 AM)", 0, feedingSchedule.getStartHour());
        assertEquals("The max window should be 3 hours", 3, feedingSchedule.getMaxWindow());
        assertEquals("The duration should be 5 minutes", 5, feedingSchedule.getDuration());
        // Assuming the animalID in the Item object should match the Fox's ID.
        assertEquals("The Item's animalID should match Fox's ID", 2, feedingSchedule.getAnimalID());
    }
}

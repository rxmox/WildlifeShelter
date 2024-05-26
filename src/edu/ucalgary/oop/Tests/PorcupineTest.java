package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Animals.Porcupine;
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
 * Test suite for the Porcupine class.
 * Validates the behavior of the Porcupine-specific feeding schedule and ensures
 * that the constructor properly initializes Porcupine objects as expected, inheriting
 * behavior from the Animal class.
 */
public class PorcupineTest {

    /**
     * Tests the Porcupine constructor to ensure it correctly initializes a Porcupine object.
     * Since Porcupine inherits its constructor from Animal, this test verifies that the 
     * inherited behavior sets the ID, nickname, and species correctly.
     */
    @Test
    public void testPorcupineConstructor() {
        Porcupine porcupine = new Porcupine(1, "Poky", "Porcupine");
        
        assertEquals("The ID should be set correctly", 1, porcupine.getID());
        assertEquals("The nickname should be set correctly", "Poky", porcupine.getNickName());
        assertEquals("The species should be set correctly", "Porcupine", porcupine.getAnimalSpecies());
    }
    
    /**
     * Tests the feeding method to ensure it returns an Item object with Porcupine-specific
     * feeding values. Specifically, it checks for a feeding schedule that starts at 7 PM.
     */
    @Test
    public void testFeeding() {
        Porcupine porcupine = new Porcupine(2, "Spike", "Porcupine");
        Item feedingSchedule = porcupine.feeding();
        
        assertEquals("The feeding schedule should start at 19 (7 PM)", 19, feedingSchedule.getStartHour());
        assertEquals("The max window should be 3 hours", 3, feedingSchedule.getMaxWindow());
        assertEquals("The duration should be 5 minutes", 5, feedingSchedule.getDuration());
        // Assuming the animalID in the Item object should match the Porcupine's ID.
        assertEquals("The Item's animalID should match Porcupine's ID", 2, feedingSchedule.getAnimalID());
    }
}

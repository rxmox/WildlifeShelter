/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.1.0
 * @since 1.0.0
 */

 package edu.ucalgary.oop.Tests;

 import edu.ucalgary.oop.Animals.Coyote;
 import edu.ucalgary.oop.Schedule.Item;
 import org.junit.Test;
 import static org.junit.Assert.*;
 
 /**
  * Test suite for the Coyote class.
  * Validates the behavior of the Coyote-specific feeding schedule and ensures
  * that the constructor properly initializes Coyote objects as expected, inheriting
  * behavior from the Animal class.
  */
 public class CoyoteTest {
 
     /**
      * Tests the Coyote constructor to ensure it correctly initializes a Coyote object.
      * Since Coyote inherits its constructor from Animal, this test verifies that the 
      * inherited behavior sets the ID, nickname, and species correctly.
      */
     @Test
     public void testCoyoteConstructor() {
         Coyote coyote = new Coyote(1, "Wile", "Coyote");
         
         assertEquals("The ID should be set correctly", 1, coyote.getID());
         assertEquals("The nickname should be set correctly", "Wile", coyote.getNickName());
         assertEquals("The species should be set correctly", "Coyote", coyote.getAnimalSpecies());
     }
     
     /**
      * Tests the feeding method to ensure it returns an Item object with Coyote-specific
      * feeding values. Specifically, it checks for a feeding schedule that starts at 7 PM.
      */
     @Test
     public void testFeeding() {
         Coyote coyote = new Coyote(2, "Runner", "Coyote");
         Item feedingSchedule = coyote.feeding();
         
         assertEquals("The feeding schedule should start at 19 (7 PM)", 19, feedingSchedule.getStartHour());
         assertEquals("The max window should be 3 hours", 3, feedingSchedule.getMaxWindow());
         assertEquals("The duration should be 5 minutes", 5, feedingSchedule.getDuration());
         // Assuming the animalID in the Item object should match the Coyote's ID.
         assertEquals("The Item's animalID should match Coyote's ID", 2, feedingSchedule.getAnimalID());
     }
 }
 
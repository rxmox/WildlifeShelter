/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
*/

 package edu.ucalgary.oop.Tests;

 import edu.ucalgary.oop.Schedule.Item;
 import edu.ucalgary.oop.Animals.Animal;
 import org.junit.Test;
 import static org.junit.Assert.*;
 
 /**
  * Tests for the Animal class.
  * This test suite aims to cover both positive and negative test scenarios for the Animal class,
  * including the proper setting and retrieval of properties, the behavior of the constructor with invalid inputs,
  * and the functionality of the feeding() method. It ensures that the Animal class behaves as expected
  * in various scenarios, adhering to the principles of encapsulation and data integrity.
  */
 public class AnimalTest {
 
     /**
      * Tests that the Animal constructor correctly initializes an animal object with valid inputs.
      * Ensures that the ID, nickname, and species are properly set and can be retrieved.
      */
     @Test
     public void testAnimalConstructor_ValidInputs() {
         Animal animal = new Animal(1, "Fido", "Dog");
  
         assertEquals("The ID should be 1", 1, animal.getID());
         assertEquals("The nickname should be Fido", "Fido", animal.getNickName());
         assertEquals("The species should be Dog", "Dog", animal.getAnimalSpecies());
     }
 
     /**
      * Tests the functionality of setting and getting the kit status.
      * Verifies that after setting an animal's kit status, the getKitStatus method reflects this change.
      * Also checks that other getters remain accurate after setting kit status.
      */
     @Test
     public void testSetKitStatus_And_Getters() {
         Animal animal = new Animal(2, "Spot", "Cat");
         animal.setKitStatus();
  
         assertTrue("Kit status should be true", animal.getKitStatus());
  
         assertEquals("The ID should be 2", 2, animal.getID());
         assertEquals("The nickname should be Spot", "Spot", animal.getNickName());
         assertEquals("The species should be Cat", "Cat", animal.getAnimalSpecies());
     }
 
     /**
      * Tests the Animal constructor with a negative ID to verify that an IllegalArgumentException is thrown.
      * This test ensures that the class properly validates the ID parameter.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testAnimalConstructor_NegativeID() {
         new Animal(-1, "Fido", "Dog");
     }
 
     /**
      * Tests the Animal constructor with a null nickname to verify that an IllegalArgumentException is thrown.
      * This test ensures that the class does not accept null values for essential string fields.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testAnimalConstructor_NullNickname() {
         new Animal(1, null, "Dog");
     }
 
     /**
      * Tests the Animal constructor with an empty species string to verify that an IllegalArgumentException is thrown.
      * Ensures that essential string fields cannot be empty, upholding data integrity.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testAnimalConstructor_EmptySpecies() {
         new Animal(1, "Fido", "");
     }
 
     /**
      * Tests the feeding() method to ensure it creates and returns an Item object with the correct default feeding schedule.
      * Verifies that the returned Item object has the expected properties set, according to the specification.
      */
     @Test
     public void testFeeding() {
         Animal animal = new Animal(1, "Fido", "Dog");
         Item item = animal.feeding();
  
         assertEquals("Animal ID should match", 1, item.getAnimalID());
         assertEquals("Start hour should be 0", 0, item.getStartHour());
         assertEquals("Max window should be 3", 3, item.getMaxWindow());
         assertEquals("Duration should be 5", 5, item.getDuration());
     }
 }
 

/**
 * Tests for the Tasks class.
 * This test suite aims to cover both positive and negative test scenarios for the Tasks class,
 * including the correct initialization of task objects with valid inputs, and the behavior of the constructor with invalid inputs.
 * It ensures that the Tasks class behaves as expected in various scenarios, adhering to the principles of encapsulation and data integrity.
 * /**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.0.0
 * @since 1.0.0
 */

 package edu.ucalgary.oop.Tests;

 import edu.ucalgary.oop.Medical.Tasks;
 import org.junit.Test;
 import static org.junit.Assert.*;
 
 public class TasksTest {
 
     /**
      * Tests that the Tasks constructor correctly initializes a task object with valid inputs.
      * Ensures that the TASKID, DESCRIPTION, DURATION, and MAXWINDOW are properly set and can be retrieved.
      */
     @Test
     public void testTasksConstructor_ValidInputs() {
         Tasks task = new Tasks(1, "Check temperature", 1, 24);
 
         assertEquals("The TASKID should be 1", 1, task.getTaskID());
         assertEquals("The DESCRIPTION should be Check temperature", "Check temperature", task.getDescription());
         assertEquals("The DURATION should be 1", 1, task.getDuration());
         assertEquals("The MAXWINDOW should be 24", 24, task.getMaxWindow());
     }
 
     /**
      * Tests the Tasks constructor with a negative duration to verify that an IllegalArgumentException is thrown.
      * This test ensures that the class properly validates the duration parameter.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testTasksConstructor_NegativeDuration() {
         new Tasks(2, "Administer medication", -1, 12);
     }
 
     /**
      * Tests the Tasks constructor with a maxWindow out of the allowed range to verify that an IllegalArgumentException is thrown.
      * Verifies that the maxWindow parameter must be between 0 and 24, inclusive.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testTasksConstructor_InvalidMaxWindow() {
         new Tasks(3, "Feed animal", 1, 25); // Max window too high
     }
 
     /**
      * Additional test to cover the lower bound of the maxWindow parameter.
      */
     @Test(expected = IllegalArgumentException.class)
     public void testTasksConstructor_MaxWindowBelowZero() {
         new Tasks(4, "Groom animal", 1, -1); // Max window too low
     }
 }
 
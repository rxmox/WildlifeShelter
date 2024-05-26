package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Medical.Treatments;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Treatments class.
 * This test suite aims to verify the functionality of the Treatments class, including the correct initialization
 * of treatment objects with valid inputs, validation of start hour, and the behavior of getters and setters.
 * It ensures that the Treatments class behaves as expected in various scenarios, maintaining data integrity and
 * adhering to the defined constraints.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.0.0
 * @since 1.0.0
 */

public class TreatmentsTest {

    /**
     * Tests the successful creation of a Treatments object with valid parameters.
     * Ensures that the constructor initializes all properties correctly and they can be retrieved using getters.
     */
    @Test
    public void testTreatmentsConstructor_ValidInputs() {
        Treatments treatment = new Treatments(1, 100, 200, 10);

        assertEquals("The TREATMENTID should be 1", 1, treatment.getTreatmentID());
        assertEquals("The ANIMALID should be 100", 100, treatment.getAnimalID());
        assertEquals("The TASKID should be 200", 200, treatment.getTaskID());
        assertEquals("The startHour should be 10", 10, treatment.getStartHour());
    }

    /**
     * Tests setting a valid start hour through the setter after object creation.
     * Verifies the change is correctly applied and retrievable.
     */
    @Test
    public void testSetStartHour_Valid() {
        Treatments treatment = new Treatments(2, 101, 201, 12);
        treatment.setStartHour(15); // Changing the start hour

        assertEquals("The startHour should now be 15", 15, treatment.getStartHour());
    }

    /**
     * Tests the Treatments constructor with a start hour below the valid range.
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTreatmentsConstructor_StartHourBelowRange() {
        new Treatments(3, 102, 202, -1);
    }

    /**
     * Tests the Treatments constructor with a start hour above the valid range.
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTreatmentsConstructor_StartHourAboveRange() {
        new Treatments(4, 103, 203, 24);
    }

    /**
     * Tests setting a start hour below the valid range via the setter.
     * Expects an IllegalArgumentException to confirm validation is in place.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHour_BelowRange() {
        Treatments treatment = new Treatments(5, 104, 204, 11);
        treatment.setStartHour(-1); // Invalid start hour
    }

    /**
     * Tests setting a start hour above the valid range via the setter.
     * Expects an IllegalArgumentException to confirm validation is enforced.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHour_AboveRange() {
        Treatments treatment = new Treatments(6, 105, 205, 11);
        treatment.setStartHour(24); // Invalid start hour
    }
}

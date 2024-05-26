package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Schedule.Item;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Item class in the Schedule package.
 * This test suite verifies the functionality of the Item class, focusing on the correct
 * initialization of Item objects with valid inputs, the enforcement of constraints on
 * timing parameters, and the behavior of getters.
 * It aims to ensure that Item instances behave as expected across various use cases,
 * maintaining consistency and enforcing business rules.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItemTest {

    /**
     * Tests the successful creation of an Item object with valid parameters and without specifying a treatment ID.
     * Verifies that all properties are initialized correctly and can be retrieved using getters.
     */
    @Test
    public void testItemConstructor_ValidInputs_NoTreatmentID() {
        Item item = new Item(1, 2, 10, 3, 1);

        assertEquals("Animal ID should be 1", 1, item.getAnimalID());
        assertEquals("Task ID should be 2", 2, item.getTaskID());
        assertEquals("Start hour should be 10", 10, item.getStartHour());
        assertEquals("Max window should be 3", 3, item.getMaxWindow());
        assertEquals("Duration should be 1", 1, item.getDuration());
        assertEquals("Treatment ID should default to 0", 0, item.getTreatmentID());
    }

    /**
     * Tests the successful creation of an Item object with valid parameters, including specifying a treatment ID.
     * Ensures correct initialization and retrievability of all properties.
     */
    @Test
    public void testItemConstructor_ValidInputs_WithTreatmentID() {
        Item item = new Item(1, 2, 10, 3, 1, 100);

        assertEquals("Treatment ID should be 100", 100, item.getTreatmentID());
    }

    /**
     * Tests Item constructor with a start hour below the valid range.
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemConstructor_StartHourBelowRange() {
        new Item(1, 2, -1, 3, 1);
    }

    /**
     * Tests Item constructor with a start hour above the valid range.
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemConstructor_StartHourAboveRange() {
        new Item(1, 2, 24, 3, 1);
    }

    /**
     * Tests Item constructor with a duration below the valid range (negative).
     * Expects an IllegalArgumentException to confirm validation is enforced.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemConstructor_NegativeDuration() {
        new Item(1, 2, 10, 3, -1);
    }

    /**
     * Tests Item constructor with a max window time out of valid range (above 24).
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemConstructor_MaxWindowOutOfRange() {
        new Item(1, 2, 10, 25, 1);
    }

    /**
     * Tests updating the start hour to a valid value through the setter method.
     * Verifies the change is applied correctly.
     */
    @Test
    public void testSetStartHour_Valid() {
        Item item = new Item(1, 2, 10, 3, 1);
        item.setStartHour(12); // Valid start hour within range
        assertEquals("Start hour should be updated to 12", 12, item.getStartHour());
    }

    /**
     * Tests updating the start hour to a value below the valid range via the setter.
     * Expects an IllegalArgumentException to confirm validation is in place.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHour_BelowRange() {
        Item item = new Item(1, 2, 10, 3, 1);
        item.setStartHour(-1); // Invalid start hour below range
    }

    /**
     * Tests updating the start hour to a value above the valid range via the setter.
     * Expects an IllegalArgumentException to ensure proper validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHour_AboveRange() {
        Item item = new Item(1, 2, 10, 3, 1);
        item.setStartHour(24); // Invalid start hour above range
    }
}

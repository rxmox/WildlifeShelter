/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.0.0
 * @since 1.0.0
 */


package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Schedule.Item;
import edu.ucalgary.oop.Animals.*;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class BeaverTest {

    /**
     * Test the constructor of the "Beaver" class.
     */
    @Test
    public void testConstructor() {
        // create a new beaver object with valid ID, nickname, and species.
        Beaver beaver = new Beaver(1, "Benny", "Beaver");

        // check all the attributes of the beaver object to ensure they are correct.
        assertEquals("The wrong ID was returned", 1, beaver.getID());
        assertEquals("The wrong NickName was returned", "Benny", beaver.getNickName());
        assertEquals("The wrong Animal Species was returned", "Beaver", beaver.getAnimalSpecies());
    }

    /**
     * Test the constructor of the "Beaver" class with invalid input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_InvalidInput() {
        // Test invalid input - nickname cannot be null or empty.
        Beaver invalidBeaver = new Beaver(2, "", "Beaver");
    }

    /**
     * Test the feeding method of the "Beaver" class.
     */
    @Test
    public void testFeeding() {
        // create a new beaver object and call the feeding method.
        Beaver beaver = new Beaver(1, "Benny", "Beaver");
        Item feedingItem = beaver.feeding();

        // verify that the feeding method returns the correct Item attributes.
        assertEquals("The animal ID in the feeding schedule should match the Beaver's ID", 1, feedingItem.getAnimalID());
        assertEquals("The start hour should be set to 8 for diurnal feeding", 8, feedingItem.getStartHour());
        assertEquals("The max window for feeding should be 3 hours", 3, feedingItem.getMaxWindow());
        assertEquals("The feeding duration should be 5 minutes", 5, feedingItem.getDuration());
    }
}

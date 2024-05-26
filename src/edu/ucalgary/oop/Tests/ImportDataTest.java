package edu.ucalgary.oop.Tests;

import edu.ucalgary.oop.Schedule.ImportData;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration tests for the ImportData class.
 * 
 * Please note that you need to access a test database to run these tests.
 * 
 * These tests directly interact with a real database configured for testing purposes.
 * Ensure that a test database is used to prevent affecting production data.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 1.2.0
 * @since 1.0.0
 */

@SuppressWarnings("unused")
public class ImportDataTest {

    private final String username = "testUser";  // Replace with your test database username
    private final String password = "testPassword";  // Replace with your test database password
    private final String dbName = "ewr";  // The test database name

    /**
     * Test establishing a connection to the database.
     */
    @Test
    public void testEstablishDbConnection() {
        boolean connectionResult;
        try {
            ImportData importData = new ImportData();
            connectionResult = importData.establishDBConnection(username, password);
            assertTrue("Connection to the database should be successful", connectionResult);
        } catch (Exception e) {
            fail("Creating ImportData should not throw an exception");
        }
    }

    /**
     * Test importing animals from the database.
     * 
     * Note: This test assumes the 'animals' table is properly populated in the test database.
     */
    @Test
    public void testImportAnimalsTable() {
        try {
            ImportData importData = new ImportData();
            importData.establishDBConnection(username, password);
            assertNotNull("Imported animals HashMap should not be null", importData.importAnimalsTable());
        } catch (SQLException e) {
            fail("Importing animals should not throw SQLException");
        }
    }

    /**
     * Test importing tasks from the database.
     * 
     * Note: This test assumes the 'tasks' table is properly populated in the test database.
     */
    @Test
    public void testImportTasksTable() {
        try {
            ImportData importData = new ImportData();
            importData.establishDBConnection(username, password);
            assertNotNull("Imported tasks HashMap should not be null", importData.importTasksTable());
        } catch (SQLException e) {
            fail("Importing tasks should not throw SQLException");
        }
    }

    /**
     * Test importing treatments from the database.
     * 
     * Note: This test assumes the 'treatments' table is properly populated in the test database.
     */
    @Test
    public void testImportTreatmentsTable() {
        try {
            ImportData importData = new ImportData();
            importData.establishDBConnection(username, password);
            assertNotNull("Imported treatments ArrayList should not be null", importData.importTreatmentsTable());
        } catch (SQLException e) {
            fail("Importing treatments should not throw SQLException");
        }
    }

    /**
     * Test updating the start hour of a treatment.
     * 
     * Note: This test assumes the 'treatments' table contains at least one record in the test database.
     */
    @Test
    public void testUpdateTreatmentStartHour() {
        try {
            ImportData importData = new ImportData();
            importData.establishDBConnection(username, password);
            assertTrue("Update treatment start hour should return true", 
                importData.updateTreatmentStartHour(1, 10));  // Assumes there is a treatment with ID 1
        } catch (Exception e) {
            fail("Updating treatment start hour should not throw SQLException");
        }
    }
}

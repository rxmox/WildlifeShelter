package edu.ucalgary.oop.Schedule;

import java.sql.*;
import java.util.*;
import edu.ucalgary.oop.Medical.*;
import edu.ucalgary.oop.Animals.*;

/**
 * Handles the database interactions necessary to import and manage data pertaining to animals, tasks,
 * and treatments. It provides functionality to establish a database connection and retrieve data required for
 * the daily operations of a wildlife rescue center, such as Example Wildlife Rescue (EWR).
 * 
 * The class ensures that data retrieval is consistent with the application's data structures by creating
 * mappings from SQL query results to Java objects. It also offers a means to update treatment schedules based on
 * new information, reflecting the dynamic nature of the rescue center's needs.
 * 
 * Usage of this class includes creating a connection to the database and retrieving tables as
 * HashMaps or ArrayLists for animals, tasks, and treatments.
 * 
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 2.2.0
 * @since 1.6.0
 */
public class ImportData { 
    private Connection dbConnection;
    private final List<String> validTableNames = Arrays.asList("animals", "tasks", "treatments");

    /**
     * Establishes a connection to the specified database using the provided credentials. The method attempts
     * to connect to the PostgreSQL database at the specified local URL for the EWR database system.
     * 
     * @param username The database username
     * @param password The database password
     * @return A boolean value indicating whether the connection was successful.
     */
    public boolean establishDBConnection(String username, String password) {
        try {
            this.dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost/ewr", username, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the 'animals' table from the connected database and maps it to a HashMap with the AnimalID as the key
     * and the corresponding Animal object as the value. This enables quick access to animal objects based on their ID.
     * 
     * @return A HashMap<Integer, Animal> where each key is an AnimalID and each value is an Animal object.
     * @throws SQLException If a database access error occurs or the 'animals' table name is invalid.
     */
    public HashMap<Integer, Animal> importAnimalsTable() throws SQLException {
        HashMap<Integer, Animal> animalHashMap = new HashMap<>();
        String tableName = "animals";
        validateTableName(tableName);

        String query = "SELECT * FROM " + tableName;

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("AnimalID");
                String nickName = resultSet.getString("AnimalNickname");
                String animalSpecies = resultSet.getString("AnimalSpecies");
                
                Animal animal = createAnimalBySpecies(id, nickName, animalSpecies);
                if (animal != null) {
                    animalHashMap.put(id, animal);
                }
            }
        }
        return animalHashMap;
    }

    /**
     * Retrieves the 'tasks' table from the connected database and maps it to a HashMap with the TaskID as the key
     * and the corresponding Tasks object as the value. This mapping is used to manage the tasks associated with
     * different treatments.
     * 
     * @return A HashMap<Integer, Tasks> where each key is a TaskID and each value is a Tasks object.
     * @throws SQLException If a database access error occurs or the 'tasks' table name is invalid.
     */
    public HashMap<Integer, Tasks> importTasksTable() throws SQLException {
        HashMap<Integer, Tasks> tasksHashMap = new HashMap<>();
        String tableName = "tasks";
        validateTableName(tableName);

        String query = "SELECT * FROM " + tableName;

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int taskID = resultSet.getInt("TaskID");
                String description = resultSet.getString("Description");
                int duration = resultSet.getInt("Duration");
                int maxWindow = resultSet.getInt("MaxWindow");

                Tasks task = new Tasks(taskID, description, duration, maxWindow);
                tasksHashMap.put(taskID, task);
            }
        }
        return tasksHashMap;
    }

    /**
     * Retrieves the 'treatments' table from the connected database and stores the treatment records in an ArrayList.
     * Each record is encapsulated in a Treatments object which represents a specific treatment assigned to an animal.
     * 
     * @return An ArrayList<Treatments> containing treatment objects extracted from the database.
     * @throws SQLException If a database access error occurs or the 'treatments' table name is invalid.
     */
    public ArrayList<Treatments> importTreatmentsTable() throws SQLException {
        ArrayList<Treatments> treatmentsArrayList = new ArrayList<>(); 
        String tableName = "treatments";
        validateTableName(tableName);

        String query = "SELECT * FROM " + tableName;

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int treatmentID = resultSet.getInt("TreatmentID");
                int animalID = resultSet.getInt("AnimalID");
                int taskID = resultSet.getInt("TaskID");
                int startHour = resultSet.getInt("StartHour");

                Treatments treatment = new Treatments(treatmentID, animalID, taskID, startHour);
                treatmentsArrayList.add(treatment);
            }
        }
        return treatmentsArrayList;   
    }

    /**
     * Updates the start hour of a specific treatment in the database. This allows adjustments to treatment schedules
     * based on real-time changes or corrections made by the user.
     * 
     * @param treatmentID  The unique identifier of the treatment to be updated.
     * @param newStartHour The new start hour for the treatment (must be between 0 and 23).
     * @return A boolean indicating whether the update was successful.
     */
    public boolean updateTreatmentStartHour(int treatmentID, int newStartHour) {
        if (newStartHour < 0 || newStartHour > 23) {
            throw new IllegalArgumentException("Start hour must be between 0 and 23.");
        }

        String query = "UPDATE treatments SET StartHour = ? WHERE TreatmentID = ?";
        
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setInt(1, newStartHour);
            preparedStatement.setInt(2, treatmentID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to validate the table name is within the expected list of valid tables.
    private void validateTableName(String tableName) throws SQLException {
        if (!validTableNames.contains(tableName.toLowerCase())) {
            throw new SQLException("Invalid Table Name: " + tableName);
        }
    }

    // Helper method to create an Animal object based on its species.
    private Animal createAnimalBySpecies(int id, String nickName, String species) {
        switch (species) {
            case "beaver":
                return new Beaver(id, nickName, species);
            case "coyote":
                return new Coyote(id, nickName, species);
            case "fox":
                return new Fox(id, nickName, species);
            case "porcupine":
                return new Porcupine(id, nickName, species);
            case "raccoon":
                return new Raccoon(id, nickName, species);
            default:
                System.err.println("Unknown species: " + species);
                return null;
        }
    }
}

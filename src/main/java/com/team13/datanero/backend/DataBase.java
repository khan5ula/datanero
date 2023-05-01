package com.team13.datanero.backend;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
    private static DataBase instance;
    private Connection dbConnection = null;

    private DataBase() {
        try {
            System.out.println("Status: MessageDatabase constructor calls init()");
            init();
        } catch (final SQLException e) {
            System.out.println("Error: Something went wrong in the DB constructor: " + e.getMessage());
        }
    }

    /**
     * Method that returns the database instance. If instance does not exists,
     * creates a new one.
     * 
     * @return DataBase instance.
     */
    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    /**
     * Method that is used to initialize the database table(s).
     * 
     * @return boolean, true if SQL database connection exists, false otherwise
     * @throws SQLException
     */
    private boolean init() throws SQLException {
        System.out.println("Status: Initializing database");

        if (this.dbConnection != null) {
            System.out.println("Status: Calling method for Score Table creation");
            createScoreTable();
            return true;
        }

        return false;
    }

    /**
     * Method that opens an existing database file or creates a new one.
     * <p>
     * Uses the parameter value to look for a database file.
     * If a file with the given name is not found, creates a new file.
     * <p>
     * Combines the given name with "jdbc:sqlite:" to create a
     * database connection.
     * 
     * @param dbName, String that provides a name for the database file
     * @throws SQLException
     */
    public void open(final String dbName) throws SQLException {
        System.out.println("Status: Opening the database file");
        boolean exists = false;
        final File file = new File(dbName);
        System.out.println("Status: Checking the given database file: " + file.toString());

        /*
         * Check if the given file can be found. Also make sure that it is not a
         * directory
         */
        if (!file.isDirectory()) {
            System.out.println("Status: " + file.toString() + " is not a directory, good");
            if (file.isFile()) {
                System.out.println("Status: " + file.toString() + " is a file. Database item found");
                exists = true;
            }
        }

        try {
            final String address = "jdbc:sqlite:" + dbName;
            this.dbConnection = DriverManager.getConnection(address);
        } catch (final Exception e) {
            System.out.println("Error while estabilishing dbConnection: " + e.getMessage());
        }

        /*
         * If the given file could not be found, initialize a new database using that
         * name
         */
        if (!exists) {
            System.out.println("Status: Database was not found, initializing a new one");
            init();
        }
    }

    /**
     * Method that creates a Score Table to the database.
     * <p>
     * The table will contain following attributes:
     * <ul>
     * <li>id (int), auto-incremented, primary key
     * <li>nickname (varchar)</li>
     * <li>score (int)</li>
     * </ul>
     * <p>
     * @throws SQLException
     */
    private void createScoreTable() throws SQLException {
        System.out.println("Status: Creating Score Table");

        try {
            final String createPrompt = "CREATE TABLE scores (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nickname VARCHAR (20) NOT NULL," +
                    "score INT)";

            final Statement createStatement = this.dbConnection.createStatement();
            createStatement.executeUpdate(createPrompt);
            createStatement.close();

            System.out.println("Success: Score Table initialized");
        } catch (final SQLException e) {
            System.out.println("SQLException occured while creating Score Table: " + e.getMessage());
        } catch (final Exception e) {
            System.out.println("Error occured while creating Score Table: " + e.getMessage());
        }
    }

    /**
     * Method that closes the database connection.
     * <p>Calls the Connection.close() method and 
     * sets the Connection class member variable to null.
     * @throws SQLException
     */
    public void closeDB() throws SQLException {
        if (this.dbConnection == null) {
            this.dbConnection.close();
            System.out.println("Status: Closing database connection");
            this.dbConnection = null;
        }
    }

    /**
     * Method that inserts a new Score to the Scores Table.
     * <p>Creates a Insert into messages statement.
     * @param score object.
     * @throws SQLException
     */
    public void setScore(final Score score) throws SQLException {
        System.out.println("Status: Setting a new Score to database");

        final StringBuilder temp = new StringBuilder("INSERT INTO scores (nickname, score) ");

        temp.append("VALUES('");
        temp.append(score.getNick());
        temp.append("','");
        temp.append(score.getScore());
        temp.append("')");

        final String setScoreString = temp.toString();
        Statement createStatement;
        createStatement = this.dbConnection.createStatement();
        createStatement.executeUpdate(setScoreString);
        createStatement.close();
    }

    /**
      * Method that checks if there are any Scores in the database.
      * <p>Breaks the loop if one score instance has been found.
      * @return int, 0 if there were not scores, 1 if any were found
      * @throws SQLException
      */
      public int checkIfThereAreScores() throws SQLException {
        int count = 0;
        final Statement queryStatement = this.dbConnection.createStatement();
        final ResultSet result = queryStatement.executeQuery("SELECT * FROM scores ORDER BY rowid");

        while (result.next()) {
            count++;
            if (count > 0) {
                break;
            }
        }

        return count;
    }

    /**
     * Method for getting scores from the database.
     * <p>Creates a Select from scores statement.
     * <p>Selects all Scores from the scores table.
     * @return ArrayList of all Scores stored in the database.
     * @throws SQLException
     */
    public ArrayList<Score> getScores() throws SQLException {
        System.out.println("Status: Getting scores from database");
        ArrayList<Score> scoresArrayList = new ArrayList<>();
        if (checkIfThereAreScores() > 0) {
            final Statement queryStatement = this.dbConnection.createStatement();
            final ResultSet result = queryStatement.executeQuery("SELECT * FROM scores ORDER BY rowid");
    
            while (result.next()) {
                Score score = new Score(result.getString("nickname"), result.getInt("score"));
                scoresArrayList.add(score);
            }
        }

        return scoresArrayList;
    }

    /**
     * Method that deletes all High Scores from the scores table.
     * <p>Should be used before inserting a new set of scores.
     * @throws SQLException
     */
    public void clearScoresTable() throws SQLException {
        System.out.println("Status: Clearing scores table");
    
        try {
            final String clearPrompt = "DELETE FROM scores";
    
            final Statement clearStatement = this.dbConnection.createStatement();
            clearStatement.executeUpdate(clearPrompt);
            clearStatement.close();
    
            System.out.println("Success: Scores table cleared");
        } catch (final SQLException e) {
            System.out.println("SQLException occurred while clearing scores table: " + e.getMessage());
        } catch (final Exception e) {
            System.out.println("Error occurred while clearing scores table: " + e.getMessage());
        }
    }

}

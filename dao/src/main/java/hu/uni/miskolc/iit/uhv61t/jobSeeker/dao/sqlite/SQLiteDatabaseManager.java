package hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages connection with SQLite database.
 */
final class SQLiteDatabaseManager {

    /**
     * Connection to the database.
     */
    private Connection connection = null;

    /**
     * Name of JDBC driver class to load into memory.
     */
    private final String JDBCDriverName = "org.sqlite.JDBC";

    /**
     * Connection URL to SQLite database.
     */
    private final String connectionURL = "jdbc:sqlite:jobSeeker.db";

    /**
     * Valid SQL commands to initialize SQLite tables.
     */
    private final String[] tableInitializatorCommands = {
            "CREATE TABLE IF NOT EXISTS Applicants (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, " +
                    "name TEXT, birthDate TIMESTAMP, email TEXT, mobile TEXT, educationLevel INT, profession TEXT);",
            "CREATE TABLE IF NOT EXISTS Companies (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, " +
                    "employeeCount INT);",
            "CREATE TABLE IF NOT EXISTS Jobs (id INTEGER PRIMARY KEY AUTOINCREMENT, companyId INTEGER, description TEXT, " +
                    "minimumSalary INT, maximumSalary INT, educationLevel INT, " +
                    "FOREIGN KEY(companyId) REFERENCES Companies(id));",
            "CREATE TABLE IF NOT EXISTS Applications (id INTEGER PRIMARY KEY AUTOINCREMENT, applicantId INTEGER, " +
                    "jobId INTEGER, salaryDemand INT, motivationLetter TEXT, " +
                    "FOREIGN KEY(applicantId) REFERENCES Applicants(id), " +
                    "FOREIGN KEY(jobId) REFERENCES Jobs(id));"
    };

    public SQLiteDatabaseManager() throws SQLException, ClassNotFoundException {
        Class.forName(this.JDBCDriverName);
        buildConnection();
        initializeTables();
    }

    /**
     * Builds connection to SQLite database.
     */
    private void buildConnection () throws SQLException {
        this.connection = DriverManager.getConnection(this.connectionURL);
    }

    /**
     * Initializes tables in SQLite database.
     * @throws SQLException
     */
    private void initializeTables () throws SQLException {
        for (String command : this.tableInitializatorCommands) {
            Statement stmt = connection.createStatement();
            stmt.execute(command);
        }
    }

    /**
     * Returns a valid connection object to SQLite database.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        buildConnection();
        return connection;
    }
}

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
     * Name of JDBC driver class to load into memory.
     */
    private final String JDBCDriverName = "org.sqlite.JDBC";

    /**
     * Connection URL to SQLite database.
     */
    private final String connectionURL = "jdbc:sqlite::memory:";

    /**
     * Connection to the database.
     */
    private final Connection connection;

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

    private final String[] dummyData = {
            "INSERT OR IGNORE INTO Applicants VALUES (1, 'bela1977', '$2a$04$PdyG7gcv2G/WreOnnibMoezB9ghCAViqsh4iDukCLNiN3nGlF.AYS', 'Kovacs Bela', '1977-05-11', 'kb@example.com', '06301234567', 10, 'DevOp' );",
            "INSERT OR IGNORE INTO Applicants VALUES (2, 'agnes.t', '$2a$04$9jpEAi7HKVVSrehkheXjOOZ2x/tkfnuIN85VDEhhqqa4Xtowsdvdm', 'Toth Agnes', '1950-01-02', 'agnes@example.com', '06207654321', 20, 'Officer');",
            "INSERT OR IGNORE INTO Applicants VALUES (3, 'asdwasd12', '$2a$04$dz3Z5lmuSGiaoC9hJhWnuePUXPXEJMntU5SC2znKnrkSAm/PfEsn6', 'Nagy Ferenc', '1980-03-22', 'feri@example.com', '06701234567', 40, 'Guard' );",
            "INSERT OR IGNORE INTO Applicants VALUES (4, 'kkrisz', '$2a$04$WffSLMqs0zS8Ek0EYzJyQO2B2AECHDpWMtEKGRQDFkdxhbxguEzBC', 'Kiss Krisztina', '1991-08-19', 'kk@example.com', '06311234567', 50, 'Volunteer');",
            "INSERT OR IGNORE INTO Applicants VALUES (5, 'borz', '$2a$04$RQVsDhRwZ/CNAkEWJbmeGeTOJZFyezLebPpeZkK3r7xAFpz66VWie', 'Kispal Andras', '1964-03-27', 'ka@example.com', '06301234566', 60, 'Musician' );",

            "INSERT OR IGNORE INTO Companies VALUES (1, 'Folttisztito2000 Kft', 'Takaritast vallalunk, szerte az orszagban', 15);",
            "INSERT OR IGNORE INTO Companies VALUES (2, 'N3rd Bt.', 'Szamitogepes jatekok fejlesztese', 200);",
            "INSERT OR IGNORE INTO Companies VALUES (3, 'Valami Zrt.', 'Valamit csinalunk, de azt mindenkepp', 106);",

            "INSERT OR IGNORE INTO Jobs VALUES (1, 1, 'Semmittevesre keresunk embereket', 100000, 250000, 30);",
            "INSERT OR IGNORE INTO Jobs VALUES (2, 2, 'Adminisztratort keresunk', 75000, 125000, 20);",
            "INSERT OR IGNORE INTO Jobs VALUES (3, 3, 'Utepitesre keresunk ebben jartas embereket', 100000, 130000, 10);",
            "INSERT OR IGNORE INTO Jobs VALUES (4, 1, 'Szabaduszo programozot keresunk', 240000, 360000, 40);",
            "INSERT OR IGNORE INTO Jobs VALUES (5, 1, 'jelentkezz ide', 240000, 388000, 50);",
            "INSERT OR IGNORE INTO Jobs VALUES (6, 2, 'jelentkezz hozzank', 300000, 800000, 60);",
            "INSERT OR IGNORE INTO Jobs VALUES (7, 2, 'jelentkezz cegunkhoz...', 190000, 220000, 30);",

            "INSERT OR IGNORE INTO Applications VALUES (1, 1, 1, 150000, 'I rly want to work'); ",
            "INSERT OR IGNORE INTO Applications VALUES (2, 2, 2, 100000, 'I rly want money'); ",
            "INSERT OR IGNORE INTO Applications VALUES (3, 3, 3, 120000, 'Im hardworker'); ",
            "INSERT OR IGNORE INTO Applications VALUES (4, 4, 4, 300000, 'Dear Company, I want to work in this position, because...'); ",
            "INSERT OR IGNORE INTO Applications VALUES (5, 5, 5, 380000, 'Dear Company, I want to work in this position, because...'); ",
            "INSERT OR IGNORE INTO Applications VALUES (6, 5, 6, 500000, 'Dear Company, I want to work in this position, because...'); ",
            "INSERT OR IGNORE INTO Applications VALUES (7, 5, 7, 195000, 'I rly want to work');",
            "INSERT OR IGNORE INTO Applications VALUES (8, 5, 7, 193000, 'Dear Company, I want to work in this position, because...');",
            "INSERT OR IGNORE INTO Applications VALUES (9, 4, 1, 156000, 'I rly want money'); ",
            "INSERT OR IGNORE INTO Applications VALUES (10, 3, 1, 120000, 'Dear Company, I want to work in this position, because...');"
    };

    public SQLiteDatabaseManager() throws SQLException, ClassNotFoundException {
        Class.forName(this.JDBCDriverName);
        this.connection = DriverManager.getConnection(this.connectionURL);
        this.initializeTables();
        this.fillDatabaseWithData();
    }

    /**
     * Returns a valid connection object to SQLite database.
     * @throws SQLException
     */
    public Connection getConnection () {
        return this.connection;
    }

    /**
     * Initializes tables in SQLite database.
     * @throws SQLException
     */
    private void initializeTables () throws SQLException {
        for (String command : this.tableInitializatorCommands) {
            Statement stmt = this.getConnection().createStatement();
            stmt.execute(command);
        }
    }

    private void fillDatabaseWithData () throws SQLException {
        for (String command : this.dummyData) {
            Statement stmt = this.getConnection().createStatement();
            stmt.execute(command);
        }
    }
}

package hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.ExistingJobException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Company;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.EducationLevel;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.JobDAO;

import java.sql.*;

/**
 * SQLite implementation of JobDAO interface.
 */
public class JobDAOImpl implements JobDAO {

    private Connection connection;

    public JobDAOImpl() throws PersistenceException {
        try {
            connection = new SQLiteDatabaseManager().getConnection();
        } catch (SQLException|ClassNotFoundException e) {
            throw new PersistenceException("Unable to connect to database.", e);
        }
    }

    /**
     * Creates a new job in database and returns the added job.
     *
     * @param job The job to add.
     * @return The added job (with the record ID).
     * @throws ExistingJobException
     */
    public Job createJob(Job job) throws PersistenceException {
        //get values to add to database
        int companyId = job.getCompany().getCompanyId();
        String description = job.getDescription();
        long minimumSalary = job.getMinimumSalary();
        long maximumSalary = job.getMaximumSalary();
        String educationLevel = job.getRequiredEducationLevel().toString();

        //add the job to database
        int recordId;
        try {
            recordId = persistJob(companyId, description, minimumSalary, maximumSalary, educationLevel);
        } catch (SQLException e) {
            throw new PersistenceException("Unable to add job to database.", e);
        }

        //query the added job and return it
        try {
            return getJobById(recordId);
        } catch (SQLException e) {
            throw new PersistenceException("Job record added to database, but unable to get.", e);
        }
    }

    /**
     * Adds job to the database.
     * @param companyId ID of the company, what advertises the job.
     * @param description Description of the job advertise.
     * @param minimumSalary Offered minimum salary.
     * @param maximumSalary Offered maximum salary.
     * @param educationLevel Required minimum education level.
     * @return ID of the added job.
     * @throws SQLException
     */
    private int persistJob (int companyId, String description, long minimumSalary, long maximumSalary,
                             String educationLevel) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Jobs (companyId, description, minimumSalary, maximumSalary, educationLevel) " +
                        "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setInt(1, companyId);
        stmt.setString(2, description);
        stmt.setLong(3, minimumSalary);
        stmt.setLong(4, maximumSalary);
        stmt.setString(5, educationLevel);

        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();

        if (!rs.next()) {
            throw new SQLException();
        }

        return rs.getInt(1);
    }

    /**
     * Queries for job with the given id.
     * @param id ID of the job.
     * @return Job object with the given id.
     * @throws SQLException
     * @throws MalformedSalaryIntervalException
     */
    private Job getJobById (int id) throws SQLException {
        // query data
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT j.id jobId, " +
                        "j.description jobDescription, " +
                        "j.minimumSalary, " +
                        "j.maximumSalary, " +
                        "j.educationLevel, " +
                        "c.id companyId, " +
                        "c.name companyName, " +
                        "c.description companyDescription, " +
                        "c.employeeCount " +
                        "FROM Jobs j INNER JOIN Companies c ON j.companyId = c.id " +
                        "WHERE jobId=?"
        );
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            throw new SQLException();
        }

        // get Job data
        int jobId = rs.getInt("jobId");
        String jobDescription = rs.getString("jobDescription");
        long minimumSalary = rs.getLong("minimumSalary");
        long maximumSalary = rs.getLong("maximumSalary");
        EducationLevel educationLevel = EducationLevel.valueOf(rs.getString("educationLevel"));

        // get Company data
        int companyId = rs.getInt("companyId");
        String companyName = rs.getString("companyName");
        String companyDescription = rs.getString("companyDescription");
        int employeeCount = rs.getInt("employeeCount");

        // return Job instance
        try {
            return new Job(
                jobId,
                new Company(companyId, companyName, companyDescription, employeeCount),
                jobDescription,
                minimumSalary,
                maximumSalary,
                educationLevel
            );
        } catch (MalformedSalaryIntervalException e) {
            System.err.printf("Wrong Job data has been added to database (id: %d, minimumSalary: %o, maximumSalary: %o)",
                    jobId, minimumSalary, maximumSalary);
            throw new SQLException(e);
        }
    }
}

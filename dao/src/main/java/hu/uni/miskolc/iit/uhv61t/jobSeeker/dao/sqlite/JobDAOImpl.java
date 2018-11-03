package hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.ExistingJobException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
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

    /**
     * Creates new DAO instance.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JobDAOImpl() throws SQLException, ClassNotFoundException {
        connection = new SQLiteDatabaseManager().getConnection();
    }

    /**
     * Creates a new job in database and returns the added job as Job object.
     *
     * @param job The job to add.
     * @throws ExistingJobException
     */
    public Job createJob(Job job) throws SQLException, MalformedSalaryIntervalException {
        int companyId = job.getCompany().getCompanyId();
        String description = job.getDescription();
        long minimumSalary = job.getMinimumSalary();
        long maximumSalary = job.getMaximumSalary();
        int educationLevel = job.getRequiredEducationLevel().getLevel();

        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Jobs (companyId, description, minimumSalary, maximumSalary, educationLevel) " +
                        "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setInt(1, companyId);
        stmt.setString(2, description);
        stmt.setLong(3, minimumSalary);
        stmt.setLong(4, maximumSalary);
        stmt.setInt(5, educationLevel);

        int changes = stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();

        if (!rs.next()) {
            throw new SQLException("Error occured on INSERT query.");
        }

        int id = rs.getInt(1);
        return getJobById(id);
    }

    /**
     * Queries for job with the given id.
     * @param id ID of the job.
     * @return Job object with the given id.
     * @throws SQLException
     * @throws MalformedSalaryIntervalException
     */
    private Job getJobById (int id) throws SQLException, MalformedSalaryIntervalException {
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
                        "WHERE j.id=?"
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
        EducationLevel educationLevel = EducationLevel.getByNumber(rs.getInt("educationLevel"));

        // get Company data
        int companyId = rs.getInt("companyId");
        String companyName = rs.getString("companyName");
        String companyDescription = rs.getString("companyDescription");
        int employeeCount = rs.getInt("employeeCount");

        // return Job instance
        return new Job(
                jobId,
                new Company(companyId, companyName, companyDescription, employeeCount),
                jobDescription,
                minimumSalary,
                maximumSalary,
                educationLevel
        );
    }
}

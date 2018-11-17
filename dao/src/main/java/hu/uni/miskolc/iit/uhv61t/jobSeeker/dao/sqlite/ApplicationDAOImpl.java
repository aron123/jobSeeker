package hu.uni.miskolc.iit.uhv61t.jobSeeker.dao.sqlite;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * SQLite implementation of ApplicationDAO interface.
 */
public class ApplicationDAOImpl implements ApplicationDAO {

    /**
     * Connection to SQLite database.
     */
    private Connection connection;

    public ApplicationDAOImpl() throws PersistenceException, ClassNotFoundException {
        try {
            this.connection = new SQLiteDatabaseManager().getConnection();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to connect to database", e);
        }
    }

    /**
     * Reads all the applications from the database.
     *
     * @return All persisted applications.
     * @throws PersistenceException
     * @throws NoApplicationFoundException
     */
    public Collection<Application> readAllApplications() throws PersistenceException, NoApplicationFoundException {
        ResultSet rs;

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT id, applicantId, jobId, salaryDemand, motivationLetter FROM Applications");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }

    /**
     * Reads all applications of the given company from the database.
     *
     * @param company The company to search applications of.
     * @throws NoApplicationFoundException
     * @throws NotExistingCompanyException
     */
    public Collection<Application> readApplicationsByCompany(Company company)
            throws NoApplicationFoundException, PersistenceException {
        ResultSet rs;

        try {
            rs = this.queryWithOneIntParam(
                "SELECT a.id, a.applicantId, a.jobId, a.salaryDemand, a.motivationLetter " +
                        "FROM Applications a INNER JOIN Jobs j ON a.jobId=j.id " +
                        "INNER JOIN Companies c ON j.companyId = c.id " +
                        "WHERE c.id=?",
                    company.getCompanyId()
            );
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }

    /**
     * Reads all applications of the given applicant from the database.
     *
     * @param applicant The applicant to search applications of.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    public Collection<Application> readApplicationsByApplicant(Applicant applicant) throws NoApplicationFoundException,
            PersistenceException {
        ResultSet rs;

        try {
            rs = this.queryWithOneIntParam(
                    "SELECT id, applicantId, jobId, salaryDemand, motivationLetter FROM Applications WHERE applicantId=?",
                    applicant.getApplicantId()
            );
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }

    /**
     * Reads all applications to the given job from the database.
     *
     * @param job The job to search applications to.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    public Collection<Application> readApplicationsByJob(Job job)
            throws NoApplicationFoundException, PersistenceException {
        ResultSet rs;
        try {
            rs = this.queryWithOneIntParam(
                "SELECT id, applicantId, jobId, salaryDemand, motivationLetter FROM Applications WHERE jobId=?",
                    job.getJobId()
            );
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }

    /**
     * Reads all applications, that contain salary demand between the given salary interval from the database.
     *
     * @param minimumSalary Minimum salary demand.
     * @param maximumSalary Maximum salary demand.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    public Collection<Application> readApplicationsBySalaryDemand(long minimumSalary, long maximumSalary)
            throws NoApplicationFoundException, PersistenceException, MalformedSalaryIntervalException {

        if (maximumSalary < minimumSalary) {
            throw new MalformedSalaryIntervalException();
        }

        ResultSet rs;

        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, applicantId, jobId, salaryDemand, motivationLetter FROM Applications WHERE " +
                        "salaryDemand BETWEEN ? AND ?"
            );
            stmt.setLong(1, minimumSalary);
            stmt.setLong(2, maximumSalary);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }

    /**
     * Reads all applications which are above the required education level.
     *
     * @param level The required minimal education level.
     * @throws NoApplicationFoundException
     * @throws PersistenceException
     */
    public Collection<Application> readApplicationsByRequiredEducationLevel(EducationLevel level)
            throws NoApplicationFoundException, PersistenceException {
        ResultSet rs;

        try {
            rs = this.queryWithOneIntParam(
                    "SELECT id, applicantId, jobId, salaryDemand, motivationLetter FROM Applications WHERE educationLevel >= ?",
                    //TODO
                    Integer.valueOf(level.toString())

            );
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return instantiateApplicationResultSet(rs);
    }





    /**
     * Creates Application objects from database records.
     * @param rs The result set.
     * @return ArrayList with the instantiated objects.
     * @throws PersistenceException
     * @throws NoApplicationFoundException
     */
    private ArrayList<Application> instantiateApplicationResultSet(ResultSet rs)
            throws PersistenceException, NoApplicationFoundException {

        ArrayList<Application> results = new ArrayList<>();

        try {
            while (rs.next()) {
                results.add(new Application(
                        rs.getInt("id"),
                        this.getApplicantById(rs.getInt("applicantId")),
                        this.getJobById(rs.getInt("jobId")),
                        rs.getLong("salaryDemand"),
                        rs.getString("motivationLetter")
                ));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (MalformedMobileNumberException|MalformedEmailAddressException|MalformedSalaryIntervalException|
                NotExistingCompanyException|InvalidSalaryDemandException|ParseException e) {
            e.printStackTrace();
        }

        if (results.isEmpty()) {
            throw new NoApplicationFoundException();
        }

        return results;
    }

    /**
     * Reads applicant with the given ID from database.
     * @param id ID of the searched applicant.
     * @return The created Applicant object.
     * @throws SQLException
     * @throws MalformedMobileNumberException
     * @throws MalformedEmailAddressException
     */
    private Applicant getApplicantById (int id) throws SQLException, MalformedMobileNumberException, MalformedEmailAddressException, ParseException {
        ResultSet rs = queryWithOneIntParam(
            "SELECT id, username, password, name, birthDate, email, mobile, educationLevel, profession " +
                    "FROM Applicants WHERE id=?", id
        );

        return new Applicant(
            rs.getInt("id"),
            rs.getString("username"),
            null,
            rs.getString("name"),
            new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("birthdate")),
            rs.getString("email"),
            rs.getString("mobile"),
            EducationLevel.valueOf(rs.getString("educationLevel")),
            rs.getString("profession")
        );
    }

    /**
     * Reads the job with the given ID from the database.
     * @param id ID of the searched job.
     * @return The created Job object.
     * @throws SQLException
     * @throws MalformedSalaryIntervalException
     * @throws NotExistingCompanyException
     */
    private Job getJobById (int id) throws SQLException, MalformedSalaryIntervalException, NotExistingCompanyException {
        ResultSet rs = queryWithOneIntParam(
            "SELECT id, companyId, description, minimumSalary, maximumSalary, educationLevel FROM Jobs WHERE id=?", id
        );

        return new Job(
            rs.getInt("id"),
            this.getCompanyById(rs.getInt("companyId")),
            rs.getString("description"),
            rs.getLong("minimumSalary"),
            rs.getLong("maximumSalary"),
            EducationLevel.valueOf(rs.getString("educationLevel"))
        );
    }

    /**
     * Reads company with the given ID from the database.
     * @param id ID of the searched company.
     * @return The created Company object.
     * @throws SQLException
     * @throws NotExistingCompanyException
     */
    private Company getCompanyById (int id) throws SQLException, NotExistingCompanyException {
        ResultSet rs = queryWithOneIntParam("SELECT id, name, description, employeeCount FROM Companies WHERE id=?", id);

        if (!rs.next()) {
            throw new NotExistingCompanyException();
        }

        return new Company(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getInt("employeeCount")
        );
    }

    /**
     * Reads records from database, with the given SQL statement and integer parameter.
     * @param sql SQL statement to run in database.
     * @param param The integer parameter of the SQL statement.
     * @return Result set of the query.
     * @throws SQLException
     */
    private ResultSet queryWithOneIntParam(String sql, int param) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setInt(1, param);
        ResultSet rs = stmt.executeQuery();

        return rs;
    }
}
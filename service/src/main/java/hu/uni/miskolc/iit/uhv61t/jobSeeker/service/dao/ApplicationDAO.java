package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;

import java.util.Collection;

/**
 * CRUD method declarations for managing Application objects.
 * @see hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Application
 */
public interface ApplicationDAO {
    /**
     * Reads all the applications from the database.
     * @return All persisted applications.
     * @throws NoApplicationFoundException
     */
    Collection<Application> readAllApplications() throws NoApplicationFoundException, PersistenceException;

    /**
     * Reads all applications of the given company from the database.
     * @param companyId The company to search applications of.
     * @throws NoApplicationFoundException
     * @throws NotExistingCompanyException
     */
    Collection<Application> readApplicationsByCompany(int companyId)
            throws NoApplicationFoundException, PersistenceException;

    /**
     * Reads all applications of the given applicant from the database.
     * @param applicantId The applicant to search applications of.
     * @throws NoApplicationFoundException
     * @throws NotExistingApplicantException
     */
    Collection<Application> readApplicationsByApplicant(int applicantId)
            throws NoApplicationFoundException, PersistenceException;

    /**
     * Reads all applications to the given job from the database.
     * @param jobId The job to search applications to.
     * @throws NoApplicationFoundException
     * @throws NotExistingJobException
     */
    Collection<Application> readApplicationsByJob(int jobId)
            throws NoApplicationFoundException, PersistenceException;

    /**
     * Reads all applications, that contain salary demand between the given salary interval from the database.
     * @param minimumSalary Minimum salary demand.
     * @param maximumSalary Maximum salary demand.
     * @throws NoApplicationFoundException
     * @throws MalformedSalaryIntervalException
     */
    Collection<Application> readApplicationsBySalaryDemand(long minimumSalary, long maximumSalary)
            throws NoApplicationFoundException, MalformedSalaryIntervalException, PersistenceException;

    /**
     * Reads all applications which are above the required education level.
     * @param level The required minimal education level.
     * @throws NoApplicationFoundException
     */
    Collection<Application> readApplicationsByRequiredEducationLevel(EducationLevel level)
            throws NoApplicationFoundException, PersistenceException;

}

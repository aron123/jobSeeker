package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;

import java.util.Collection;

/**
 * Represents service for the applicants.
 */
public interface ApplicantService {

    /**
     * Lists all of the applications.
     * @return All applications in the database.
     */
    Collection<Application> listAllApplications () throws NoApplicationFoundException, PersistenceException;

    /**
     * Lists all applications of the given applicant.
     * @param applicant The applicant who's applications are searched.
     */
    Collection<Application> listApplicationsByApplicant(Applicant applicant) throws NoApplicationFoundException, NotExistingApplicantException, PersistenceException;

    /**
     * Lists all applications to the given job.
     * @param job The job what's applications are searched.
     */
    Collection<Application> listApplicationsByJob(Job job) throws NoApplicationFoundException, NotExistingJobException, PersistenceException;

    /**
     * Lists all applications with the given salary demand interval.
     * @param minimumSalary The minimum salary demand.
     * @param maximumSalary The maximum salary demand.
     */
    Collection<Application> listApplicationsBySalaryDemand(long minimumSalary, long maximumSalary) throws NoApplicationFoundException, MalformedSalaryIntervalException, PersistenceException;

    /**
     * Lists all applications above the required education level.
     * @param level The requied education level.
     */
    Collection<Application> listApplicationsByRequiredEducationLevel(EducationLevel level) throws NoApplicationFoundException, PersistenceException;

}

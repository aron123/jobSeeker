package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.ApplicationService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;

import java.util.Collection;

public class ApplicationServiceImpl implements ApplicationService {

    ApplicationDAO dao;

    public ApplicationServiceImpl(ApplicationDAO dao) {
        this.dao = dao;
    }

    /**
     * Lists all of the applications.
     *
     * @return All applications in the database.
     */
    public Collection<Application> listAllApplications() throws NoApplicationFoundException, PersistenceException {
        return dao.readAllApplications();
    }

    /**
     * Lists all applications of the given applicant.
     *
     * @param applicant The applicant who's applications are searched.
     */
    public Collection<Application> listApplicationsByApplicant(Applicant applicant)
            throws NoApplicationFoundException, PersistenceException {
        return dao.readApplicationsByApplicant(applicant);
    }

    /**
     * Lists all applications to the given job.
     *
     * @param job The job what's applications are searched.
     */
    public Collection<Application> listApplicationsByJob(Job job) throws NoApplicationFoundException, PersistenceException {
        return dao.readApplicationsByJob(job);
    }

    /**
     * Lists all applications with the given salary demand interval.
     *
     * @param minimumSalary The minimum salary demand.
     * @param maximumSalary The maximum salary demand.
     */
    public Collection<Application> listApplicationsBySalaryDemand(long minimumSalary, long maximumSalary)
            throws NoApplicationFoundException, MalformedSalaryIntervalException, PersistenceException {
        return dao.readApplicationsBySalaryDemand(minimumSalary, maximumSalary);
    }

    /**
     * Lists all applications above the required education level.
     *
     * @param level The required minimal education level.
     */
    public Collection<Application> listApplicationsByRequiredEducationLevel(EducationLevel level)
            throws NoApplicationFoundException, PersistenceException {
        return dao.readApplicationsByRequiredEducationLevel(level);
    }

    /**
     * Lists applications to the given company.
     *
     * @param company The company to search for.
     */
    public Collection<Application> listApplicationsByCompany(Company company)
            throws NoApplicationFoundException, PersistenceException {
        return dao.readApplicationsByCompany(company);
    }
}

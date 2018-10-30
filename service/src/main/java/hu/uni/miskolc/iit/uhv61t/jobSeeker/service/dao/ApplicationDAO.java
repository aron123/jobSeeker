package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;

import java.util.Collection;

/**
 * CRUD method declarations for managing Application objects.
 * @see hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Application
 */
public interface ApplicationDAO {
    Collection<Application> readAllApplications() throws NoApplicationFoundException;

    Collection<Application> readApplicationsByCompany(Company company)
            throws NoApplicationFoundException, NotExistingCompanyException;

    Collection<Application> readApplicationsByApplicant(Applicant applicant)
            throws NoApplicationFoundException, NotExistingApplicantException;

    Collection<Application> readApplicationsByJob(Job job) throws NoApplicationFoundException, NotExistingJobException;

    Collection<Application> readApplicationsBySalaryDemand(long minimumSalary, long maximumSalary)
            throws NoApplicationFoundException, MalformedSalaryIntervalException;

    Collection<Application> readApplicationsByRequiredEducationLevel(EducationLevel level)
            throws NoApplicationFoundException;

}

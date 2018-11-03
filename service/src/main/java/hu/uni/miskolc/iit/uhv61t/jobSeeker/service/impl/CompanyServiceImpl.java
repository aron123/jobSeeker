package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.ExistingJobException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.NoApplicationFoundException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.NotExistingCompanyException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Application;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.CompanyService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.ApplicationDAO;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.JobDAO;

import java.util.Collection;

public class CompanyServiceImpl implements CompanyService {

    JobDAO jobDAO;
    ApplicationDAO applicationDAO;

    public CompanyServiceImpl(JobDAO jobDAO, ApplicationDAO applicationDAO) {
        this.jobDAO = jobDAO;
        this.applicationDAO = applicationDAO;
    }

    /**
     * Adds new job to the database.
     *
     * @param job The job advertise to add.
     * @return The Job object what is added to the database (ID is set).
     */
    public Job advertiseJob(Job job) throws ExistingJobException {
        return jobDAO.createJob(job);
    }

    /**
     * Lists applications to the given company.
     *
     * @param companyId ID of the company to search for.
     */
    public Collection<Application> listApplications(int companyId)
            throws NoApplicationFoundException, NotExistingCompanyException {
        return applicationDAO.readApplicationsByCompany(companyId);
    }
}

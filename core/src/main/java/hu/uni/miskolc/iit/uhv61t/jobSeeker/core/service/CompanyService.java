package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service;


import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.ExistingJobException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.NoApplicationFoundException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.NotExistingCompanyException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Application;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Represents service for the companies.
 */
public interface CompanyService {

    /**
     * Adds new job to the database.
     * @param job The job advertise to add.
     * @return The Job object what is added to the database (ID is set).
     */
    public Job advertiseJob (Job job) throws ExistingJobException, SQLException, MalformedSalaryIntervalException;

    /**
     * Lists applications to the given company.
     * @param companyId ID of the company to search for.
     */
    public Collection<Application> listApplications (int companyId) throws NoApplicationFoundException, NotExistingCompanyException;
}

package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.ExistingJobException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;

import java.sql.SQLException;

/**
 * CRUD method declarations for managing Job objects.
 * @see hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job
 */
public interface JobDAO {
    /**
     * Creates a new job in database and returns the added job as Job object.
     * @param job The job to add.
     * @throws ExistingJobException
     */
    Job createJob(Job job) throws ExistingJobException, SQLException, MalformedSalaryIntervalException;
}

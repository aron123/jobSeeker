package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service;


import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;

/**
 * Represents service for the companies.
 */
public interface JobService {

    /**
     * Adds new job to the database.
     * @param job The job advertise to add.
     * @return The Job object what is added to the database (ID is set).
     */
    public Job advertiseJob (Job job) throws PersistenceException;
}

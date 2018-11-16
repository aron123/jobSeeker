package hu.uni.miskolc.iit.uhv61t.jobSeeker.service.impl;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.service.JobService;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.service.dao.JobDAO;

public class JobServiceImpl implements JobService {

    JobDAO dao;

    public JobServiceImpl(JobDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds new job to the database.
     *
     * @param job The job advertise to add.
     * @return The Job object what is added to the database (ID is set).
     */
    public Job advertiseJob(Job job) throws PersistenceException {
        return dao.createJob(job);
    }
}

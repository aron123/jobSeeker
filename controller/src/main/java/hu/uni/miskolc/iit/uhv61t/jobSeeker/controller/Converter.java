package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.JobRequest;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.JobType;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Company;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.EducationLevel;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.Job;

/**
 * This class converts DTOs and business objects vice versa.
 */
class Converter {
    /**
     * Converts Job object to JobType DTO.
     * @param job The job to convert.
     * @return The DTO object.
     */
   static JobType convertJobToDTO (Job job) {
        JobType jobDTO = new JobType();

        jobDTO.setJobId(job.getJobId());
        jobDTO.setCompanyId(job.getCompany().getCompanyId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinimumSalary(job.getMinimumSalary());
        jobDTO.setMaximumSalary(job.getMaximumSalary());
        jobDTO.setRequiredEducationLevel(job.getRequiredEducationLevel().toString());

        return jobDTO;
   }

    /**
     * Converts JobRequest DTO to Job object
     * @param jobRequest The DTO class to convert.
     * @return The business object.
     * @throws MalformedSalaryIntervalException
     */
   static Job convertJobRequestToJob (JobRequest jobRequest) throws MalformedSalaryIntervalException {
       Company company = new Company(jobRequest.getCompanyId(), null, null, 0);
       String description = jobRequest.getDescription();
       long minimumSalary = jobRequest.getMinimumSalary();
       long maximumSalary = jobRequest.getMaximumSalary();
       EducationLevel requiredEducationLevel = EducationLevel.valueOf(jobRequest.getRequiredEducationLevel());

       return new Job(0, company, description, minimumSalary, maximumSalary, requiredEducationLevel);
   }


   //TODO: application <-> DTO conversions
}

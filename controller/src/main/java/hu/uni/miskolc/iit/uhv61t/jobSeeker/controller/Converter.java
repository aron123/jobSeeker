package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.*;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model.*;

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

    /**
     * Converts Application object to Application DTO object.
     * @param application The Application to convert.
     * @return The DTO object.
     */
   static ApplicationType convertApplicationToDTO (Application application) {
       ApplicationType applicationDTO = new ApplicationType();

       applicationDTO.setApplicationId(application.getApplicationId());
       applicationDTO.setApplicant( convertApplicantToDTO(application.getApplicant()) );
       applicationDTO.setJob( convertJobToDTO(application.getJob()) );
       applicationDTO.setSalaryDemand(application.getSalaryDemand());
       applicationDTO.setMotivationLetter(application.getMotivationLetter());

       return applicationDTO;
   }

    /**
     * Converts Applicant object to Applicant DTO object.
     * @param applicant The Applicant to convert.
     * @return The DTO object.
     */
   private static ApplicantType convertApplicantToDTO (Applicant applicant) {
       ApplicantType applicantDTO = new ApplicantType();

       applicantDTO.setApplicantId(applicant.getApplicantId());
       applicantDTO.setUsername(applicant.getUsername());
       applicantDTO.setName(applicant.getName());
       applicantDTO.setEmail(applicant.getEmail());
       applicantDTO.setMobileNumber(applicant.getMobile());
       applicantDTO.setEducationLevel( convertEducationLevelToDTO(applicant.getEducationLevel()) );
       applicantDTO.setProfession(applicant.getProfession());

       return applicantDTO;
   }

    /**
     * Converts EducationLevel object to EducationLevel DTO object.
     * @param level The EducationLevel to convert.
     * @return The DTO object.
     */
   private static EducationLevelType convertEducationLevelToDTO (EducationLevel level) {
       return EducationLevelType.valueOf(level.toString());
   }
}

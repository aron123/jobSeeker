package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.InvalidSalaryDemandException;

/**
 * Represents an application for a job.
 */
public class Application {
    /**
     * ID of the application.
     */
    private int applicationId;

    /**
     * The applicant job seeker.
     */
    private Applicant applicant;

    /**
     * The job which the applicant applied for.
     */
    private Job job;

    /**
     * The salary demand of the applicant.
     */
    private Long salaryDemand;

    /**
     * Text of the applicant's motivation letter.
     */
    private String motivationLetter;

    public Application(int applicationId, Applicant applicant, Job job, Long salaryDemand, String motivationLetter)
                        throws InvalidSalaryDemandException {
        this.applicationId = applicationId;
        this.applicant = applicant;
        this.job = job;

        validateSalaryDemand(job, salaryDemand);
        this.salaryDemand = salaryDemand;

        this.motivationLetter = motivationLetter;
    }

    private void validateSalaryDemand (Job job, Long salaryDemand) throws InvalidSalaryDemandException {
        if (salaryDemand == null) { //salary demand is optional
            return;
        }

        long offeredMinimumSalary = job.getMinimumSalary();
        long offeredMaximumSalary = job.getMaximumSalary();

        if (salaryDemand < 0) {
            throw new InvalidSalaryDemandException("Salary demand can not below zero.");
        }

        if (salaryDemand < offeredMinimumSalary) {
            throw new InvalidSalaryDemandException("Salary demand can not below the offered minimum.");
        }

        if (salaryDemand > offeredMaximumSalary) {
            throw new InvalidSalaryDemandException("Salary demand can not above the offered maximum.");
        }
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getSalaryDemand() {
        return salaryDemand;
    }

    public void setSalaryDemand(Long salaryDemand) throws InvalidSalaryDemandException {
        validateSalaryDemand(job, salaryDemand);
        this.salaryDemand = salaryDemand;
    }

    public String getMotivationLetter() {
        return motivationLetter;
    }

    public void setMotivationLetter(String motivationLetter) {
        this.motivationLetter = motivationLetter;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", applicant=" + applicant +
                ", job=" + job +
                ", salaryDemand=" + salaryDemand +
                ", motivationLetter='" + motivationLetter + '\'' +
                '}';
    }
}

package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

import com.sun.istack.internal.NotNull;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedSalaryIntervalException;

/**
 * Represents an advertised job.
 */
public class Job {
    /**
     * ID of the job.
     */
    @NotNull
    private int jobId;

    /**
     * The company which advertised the job.
     */
    @NotNull
    private Company company;

    /**
     * Description of the advertised job.
     */
    private String description;

    /**
     * The offered minimum salary for the applicants.
     */
    private long minimumSalary;

    /**
     * The offered maximum salary for the applicants.
     */
    private long maximumSalary;

    /**
     * The required level of education to apply for the job.
     */
    private EducationLevel requiredEducationLevel;

    public Job(int jobId, Company company, String description, long minimumSalary, long maximumSalary,
               EducationLevel requiredEducationLevel) throws MalformedSalaryIntervalException {
        this.jobId = jobId;
        this.company = company;
        this.description = description;

        validateSalaryInterval(minimumSalary, maximumSalary);
        this.minimumSalary = minimumSalary;
        this.maximumSalary = maximumSalary;

        this.requiredEducationLevel = requiredEducationLevel;
    }

    private void validateSalaryInterval (long minSalary, long maxSalary) throws MalformedSalaryIntervalException {
        if (maxSalary < minSalary) {
            throw new MalformedSalaryIntervalException();
        }
        if (minSalary < 0 || maxSalary < 0) {
            throw new MalformedSalaryIntervalException();
        }
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(long minimumSalary) throws MalformedSalaryIntervalException {
        validateSalaryInterval(minimumSalary, this.maximumSalary);
        this.minimumSalary = minimumSalary;
    }

    public long getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(long maximumSalary) throws MalformedSalaryIntervalException {
        validateSalaryInterval(this.minimumSalary, maximumSalary);
        this.maximumSalary = maximumSalary;
    }

    public EducationLevel getRequiredEducationLevel() {
        return requiredEducationLevel;
    }

    public void setRequiredEducationLevel(EducationLevel requiredEducationLevel) {
        this.requiredEducationLevel = requiredEducationLevel;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", company=" + company +
                ", description='" + description + '\'' +
                ", minimumSalary=" + minimumSalary +
                ", maximumSalary=" + maximumSalary +
                ", requiredEducationLevel=" + requiredEducationLevel +
                '}';
    }
}

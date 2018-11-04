package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

/**
 * Represents a company that advertises jobs.
 */
public class Company {
    /**
     * ID of the company.
     */
    private int companyId;

    /**
     * Name of the company.
     */
    private String companyName;

    /**
     * The company's short introduction.
     */
    private String description;

    /**
     * Number of employees in the company.
     */
    private int employeeCount;

    public Company(int companyId, String companyName, String description, int employeeCount) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.description = description;
        this.employeeCount = employeeCount;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", employeeCount=" + employeeCount +
                '}';
    }
}

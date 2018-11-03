package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

import com.sun.istack.internal.NotNull;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedEmailAddressException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.MalformedMobileNumberException;

import java.util.Date;

/**
 * Represents a job seeker.
 */
public class Applicant {
    /**
     * ID of the applicant.
     */
    private int applicantId;

    /**
     * The applicant's username.
     */
    private String username;

    /**
     * The applicant's password.
     */
    private Password password;

    /**
     * The applicant's real name.
     */
    private String name;

    /**
     * The applicant's date of birth.
     */
    private Date birthDate;

    /**
     * The applicant's email address.
     */
    private String email;

    /**
     * The applicant's mobile number in the following format:
     * (06|+36)[-](20|30|31|70)[-](123)[-](4567)
     */
    private String mobile;

    /**
     * The applicant's level of education.
     */
    private EducationLevel educationLevel;

    /**
     * The applicant's profession.
     */
    private String profession;

    public Applicant(int applicantId, String username, Password password, String name, Date birthDate, String email,
                     String mobile, EducationLevel educationLevel, String profession)
                     throws MalformedEmailAddressException, MalformedMobileNumberException {
        this.applicantId = applicantId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;

        validateEmail(email);
        this.email = email;

        validateMobileNumber(mobile);
        this.mobile = mobile;

        this.educationLevel = educationLevel;
        this.profession = profession;
    }

    private void validateEmail (String email) throws MalformedEmailAddressException {
        //regex source: https://emailregex.com/
        if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08" +
                "\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
                "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c" +
                "\\x0e-\\x7f])+)\\])")) {
            throw new MalformedEmailAddressException();
        }
    }

    private void validateMobileNumber (String mobile) throws MalformedMobileNumberException {
        if (!mobile.matches("(\\+36|06)-{0,1}(20|30|31|70)-{0,1}[0-9]{3}-{0,1}[0-9]{4}")) {
            throw new MalformedMobileNumberException();
        }
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MalformedEmailAddressException {
        validateEmail(email);
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) throws MalformedMobileNumberException {
        validateMobileNumber(mobile);
        this.mobile = mobile;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId=" + applicantId +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", educationLevel=" + educationLevel +
                ", profession='" + profession + '\'' +
                '}';
    }
}

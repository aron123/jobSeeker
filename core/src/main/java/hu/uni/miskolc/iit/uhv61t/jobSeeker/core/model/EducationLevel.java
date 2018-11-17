package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

/**
 * Represents education level of an applicant.
 */
public enum EducationLevel {
    NO_PRIMARY_SCHOOL (10),
    PRIMARY_SCHOOL (20),
    SECONDARY_SCHOOL (30),
    BACHELORS_DEGREE (40),
    MASTERS_DEGREE (50),
    DOCTORAL_DEGREE (60);

    int level;

    EducationLevel(int level) {
        this.level = level;
    }

    public int getLevel () {
        return this.level;
    }
}

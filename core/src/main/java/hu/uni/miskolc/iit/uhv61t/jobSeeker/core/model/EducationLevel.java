package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

/**
 * Represents education level of an applicant.
 */
public enum EducationLevel {
    NO_PRIMARY_SCHOOL (0),
    PRIMARY_SCHOOL (10),
    SECONDARY_SCHOOL (20),
    BACHELORS_DEGREE (30),
    MASTERS_DEGREE (40),
    DOCTORAL_DEGREE (50);

    private final int level;

    EducationLevel (int level) {
        this.level = level;
    }

    public static EducationLevel getByNumber (int level) {
        for (EducationLevel i : EducationLevel.values()) {
            if (i.getLevel() == level) {
                return i;
            }
        }

        return null;
    }

    public int getLevel () {
        return level;
    }

    @Override
    public String toString() {
        return "EducationLevel{" +
                "level=" + level +
                '}';
    }
}

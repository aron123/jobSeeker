package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents the user's password.
 */
public final class Password {

    /**
     * Length of the password salt.
     */
    private final int saltLength = 7;

    /**
     * Hash of the plain text password.
     */
    private String passwordHash;

    public Password (String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(this.saltLength));
    }

    public String getPasswordHash () {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "Password{" +
                "saltLength=" + saltLength +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}

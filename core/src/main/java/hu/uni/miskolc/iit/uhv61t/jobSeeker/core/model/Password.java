package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents the user's password.
 */
public class Password {

    /**
     * Length of the password salt.
     */
    private int saltLength = 7;

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
}

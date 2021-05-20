package idatt2105.hamsterGroup.fullstackProject.Configuration.JWT;

import java.security.SecureRandom;

/**
 * Class that creates a new and unique JWT signing key when server starts up
 */
public class JwtSigningKey {
    private static final byte[] key = new byte[256];
    private static SecureRandom secureRandom = null;

    private JwtSigningKey() {
    }

    public static byte[] getInstance() {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
            secureRandom.nextBytes(key);
        }
        return key;
    }

}

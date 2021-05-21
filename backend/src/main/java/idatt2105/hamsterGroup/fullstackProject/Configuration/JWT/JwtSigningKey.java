package idatt2105.hamsterGroup.fullstackProject.Configuration.JWT;

import java.security.SecureRandom;

/**
 * Class for creating a new and unique JWT Signing Key on server startup.
 * Singleton Class
 */
public class JwtSigningKey {
    private static final byte[] jwtKey = new byte[256];
    private static SecureRandom secureRandom = null;


    private JwtSigningKey(){

    }

    public static byte[] getInstance(){
        if(secureRandom == null){
            secureRandom = new SecureRandom();
            secureRandom.nextBytes(jwtKey);
        }
        return jwtKey;
    }

}

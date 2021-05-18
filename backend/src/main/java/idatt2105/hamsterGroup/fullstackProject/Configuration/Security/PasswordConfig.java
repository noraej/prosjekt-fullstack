package idatt2105.hamsterGroup.fullstackProject.Configuration.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * A configuration for passwordEncoder to hash and salt a user's password,
 * checks if a password matches a hashed password
 */
@Configuration
public class PasswordConfig
{
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }
}


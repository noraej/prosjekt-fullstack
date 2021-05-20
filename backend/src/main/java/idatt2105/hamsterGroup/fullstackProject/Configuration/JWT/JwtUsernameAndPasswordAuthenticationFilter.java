package idatt2105.hamsterGroup.fullstackProject.Configuration.JWT;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Mapper file class for input when trying to log in
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String email;
    private String password;

    public JwtUsernameAndPasswordAuthenticationFilter() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

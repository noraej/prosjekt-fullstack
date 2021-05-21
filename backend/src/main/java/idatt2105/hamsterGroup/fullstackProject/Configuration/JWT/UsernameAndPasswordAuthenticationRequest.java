package idatt2105.hamsterGroup.fullstackProject.Configuration.JWT;

/**
 * Mapper file class for input when trying to log in
 */
public class UsernameAndPasswordAuthenticationRequest {
    private String email;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
        // Must be empty constructor
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

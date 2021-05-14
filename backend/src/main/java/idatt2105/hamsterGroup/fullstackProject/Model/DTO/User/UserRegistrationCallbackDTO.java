package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

/**
 * UserRegistrationCallbackDTO, DTO class to send back user and token
 * to client after creating a user
 */
public class UserRegistrationCallbackDTO {
    private String token;
    private UserDTO user;

    public UserRegistrationCallbackDTO(String token, long userId, UserAndPasswordDTO userAndPasswordDTO) {
        this.token = token;
        this.user = new UserDTO();
        this.user.setUserId(userId);
        this.user.setFirstname(userAndPasswordDTO.getFirstname());
        this.user.setLastname(userAndPasswordDTO.getLastname());
        this.user.setEmail(userAndPasswordDTO.getEmail());
        this.user.setPhoneNumber(userAndPasswordDTO.getPhoneNumber());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "RegistrationDTO{" +
                ", \ntoken='" + token + '\'' +
                ", \nuser='" + user.toString() + '\'' +
                '}';
    }
}





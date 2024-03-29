package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

/**
 * UserAndPasswordDTO, DTO class for registering a new user (User POST-request)
 */
public class UserAndPasswordDTO extends UserSuperDTO{
    private String password;


    public UserAndPasswordDTO(String firstname, String lastname, String email, String phoneNumber, String password,
                              boolean admin, boolean valid) {
        super(firstname, lastname, email, phoneNumber, admin, valid);
        this.password = password;
    }

    public UserAndPasswordDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return super.toString() + '\'' +
                "UserAndPasswordDTO {" +
                ", password='" + password + '\'' +
                '}';
    }
}

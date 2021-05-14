package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

/**
 * UserSuperDTO, Super DTO class to use in different user DTOs
 */

public class UserSuperDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;


    public UserSuperDTO(String firstname, String surname, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserSuperDTO() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "\nfirstname='" + firstname + '\'' +
                ", \nlastname='" + lastname + '\'' +
                ", \nemail='" + email + '\'' +
                ", \nphoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

import idatt2105.hamsterGroup.fullstackProject.Enum.UserRole;

/**
 * UserSuperDTO, Super DTO class to use in different user DTOs
 */

public class UserSuperDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private boolean valid;
    private boolean admin;
    private String role;


    public UserSuperDTO(String firstname, String surname, String email, String phoneNumber, boolean admin, boolean valid) {
        this.firstname = firstname;
        this.lastname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.admin = admin;
        this.valid = valid;
        if (!admin) {
            this.role = UserRole.ADMIN.name();
        } else {
            this.role = UserRole.NORMAL.name();
        }
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSuperDTO{" +
                "\nfirstname='" + firstname +
                ", \nlastname='" + lastname +
                ", \nemail='" + email +
                ", \nphoneNumber='" + phoneNumber +
                ", \nadmin='" + admin +
                ", \nvalid='" + valid +
                ", \nrole='" + role +
                '}';
    }
}

package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

import idatt2105.hamsterGroup.fullstackProject.Model.User;

/**
 * UserDTO, DTO class for sending user information
 */
public class UserDTO extends UserSuperDTO {
    private long userId;
    private boolean valid;
    private boolean admin;


    public UserDTO(long userId, String firstname, String lastname, String email, String phoneNumber,
                   boolean valid, boolean admin) {
        super(firstname, lastname, email, phoneNumber, admin, valid);
        this.userId = userId;
        this.valid = valid;
        this.admin = admin;
    }

    public UserDTO() {
        super();
    }

    public UserDTO(User user){
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.isAdmin(), user.isValid());
        this.userId = user.getUserId();
        this.valid = user.isValid();
        this.admin = user.isAdmin();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return super.toString() +
                "\nuserId=" + userId +
                ", \nvalid=" + valid +
                ", \nadmin=" + admin +
                '}';
    }
}

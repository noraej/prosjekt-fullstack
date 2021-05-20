package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

import idatt2105.hamsterGroup.fullstackProject.Model.User;

/**
 * UserDTO, DTO class for sending user information
 */
public class UserDTO extends UserSuperDTO {
    private long userId;


    public UserDTO(long userId, String firstname, String lastname, String email, String phoneNumber,
                   boolean valid, boolean admin) {
        super(firstname, lastname, email, phoneNumber, admin, valid);
        this.userId = userId;
    }

    public UserDTO() {
        super();
    }

    public UserDTO(User user){
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.isAdmin(), user.isValid());
        this.userId = user.getUserId();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() +
                "UserDTO {" +
                "\nuserId=" + userId +
                '}';
    }
}

package idatt2105.hamsterGroup.fullstackProject.Model.DTO.User;

/**
 * DTO class for sending information between backend and frontend
 * when editing user information
 */
public class UserEditDTO extends UserSuperDTO {
    private String newPassword;
    private String oldPassword;
    private boolean admin;
    private boolean valid;

    public UserEditDTO(String firstname, String lastname, String email, String phoneNumber, String newPassword, String oldPassword,
                        boolean admin, boolean valid) {
        super(firstname, lastname, email, phoneNumber, admin, valid);
        this.admin = admin;
        this.valid = valid;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public UserEditDTO(){
        super();
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}


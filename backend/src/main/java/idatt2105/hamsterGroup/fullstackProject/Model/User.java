package idatt2105.hamsterGroup.fullstackProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import idatt2105.hamsterGroup.fullstackProject.Enum.UserRole;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity class for storing information about
 * a user with userID as primary key
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String hash;
    private String salt;
    private boolean valid;
    private boolean admin;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Reservation.class)
    Set<Reservation> reservations;

    public User(String firstName, String lastName, String email, String phoneNumber, String hash, String salt,
                boolean valid, boolean admin, Set<Reservation> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hash = hash;
        this.salt = salt;
        this.valid = valid;
        this.admin = admin;
        this.reservations = reservations;
        if (admin) {
            this.role = UserRole.ROLE_ADMIN.name();
        } else {
          this.role = UserRole.ROLE_NORMAL.name();
        }

    }

    public User(String firstName, String lastName, String email, String phoneNumber, String hash, String salt,
                boolean valid, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hash = hash;
        this.salt = salt;
        this.valid = valid;
        this.admin = admin;
        if (admin) {
            this.role = UserRole.ROLE_ADMIN.name();
        } else {
            this.role = UserRole.ROLE_NORMAL.name();
        }
    }

    public User(){
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> activities) {
        this.reservations = activities;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", admin=" + admin + '\'' +
                ", valid='" + valid + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}

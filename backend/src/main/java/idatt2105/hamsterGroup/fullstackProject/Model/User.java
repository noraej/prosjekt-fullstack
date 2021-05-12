package idatt2105.hamsterGroup.fullstackProject.Model;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Reservation",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "reservationId")
    )
    Set<Reservation> reservations;

    @OneToMany(mappedBy = "userReserved")
    Set<Reservation> reservedSections;

    public User(String firstName, String lastName, String email, String phoneNumber, String hash, String salt,
                boolean valid, boolean admin, Set<Reservation> reservations, Set<Reservation> reservedSections) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hash = hash;
        this.salt = salt;
        this.valid = valid;
        this.admin = admin;
        this.reservations = reservations;
        this.reservedSections = reservedSections;
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
    }

    public User(){
    }


}

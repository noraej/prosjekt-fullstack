package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;
    private int numberOfUsers;
    private LocalDate reservationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    private Section section;

    public Reservation(long reservationId, int numberOfUsers, LocalDate reservationDateTime, User user, Section section) {
        this.reservationId = reservationId;
        this.numberOfUsers = numberOfUsers;
        this.reservationDateTime = reservationDateTime;
        this.user = user;
        this.section = section;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public LocalDate getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDate reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", numberOfUsers=" + numberOfUsers +
                ", reservationDateTime=" + reservationDateTime +
                ", user=" + user +
                ", section=" + section +
                '}';
    }
}




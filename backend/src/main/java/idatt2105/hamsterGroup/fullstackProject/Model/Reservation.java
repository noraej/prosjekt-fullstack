package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity class for storing information about
 * a reservation with reservationID as primary key
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;
    private int numberOfUsers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingId")
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToOne
    @JoinColumn(name = "sectionId", referencedColumnName = "sectionId")
    private Set<Section> sections;

    public Reservation(long reservationId, int numberOfUsers, LocalDateTime startTime, LocalDateTime endTime, String description,
                       User user, Section section, int duration, Room room, Building building, Set<Section> sections) {
        this.reservationId = reservationId;
        this.numberOfUsers = numberOfUsers;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.user = user;
        this.duration = duration;
        this.room = room;
        this.building = building;
        if (section != null) {
            this.section = section;
        } else {
            this.sections = sections;
        }
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "\nreservationId=" + reservationId +
                ", \nnumberOfUsers=" + numberOfUsers +
                ", \nstartTime=" + startTime +
                ", \nendTime=" + endTime +
                ", \ndescription=" + description +
                ", \nduration=" + duration +
                ", \nuser=" + user +
                ", \nsection=" + section +
                ", \nbuiding=" + building +
                '}';
    }
}




package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;

/**
 * Entity class for storing information about
 * a section with sectionID as primary key
 */
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sectionId;
    private String sectionName;
    private String description;
    private int seats;
    private double size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    Room room;

    public Section(long sectionId, String sectionName, String description, int seats, double size, Room room) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.seats = seats;
        this.size = size;
        this.room = room;
    }

    public Section() {
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", room='" + room.getRoomName() + '\'' +
                ", seats=" + seats +
                '}';
    }
}
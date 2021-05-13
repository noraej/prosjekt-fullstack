package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;
    private String roomName;
    private double size;

    @OneToMany(mappedBy = "room", targetEntity = Section.class)
    private Set<Section> sections;

    private int numberOfSections = sections.size();

    public Room(long roomId, String roomName, double size, Set<Section> sections, int numberOfSections) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.size = size;
        this.sections = sections;
        this.numberOfSections = numberOfSections;
    }

    public Room() {
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", size=" + size +
                ", sections=" + sections +
                ", numberOfSections=" + numberOfSections +
                '}';
    }
}
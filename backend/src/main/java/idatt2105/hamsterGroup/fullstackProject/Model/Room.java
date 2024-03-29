package idatt2105.hamsterGroup.fullstackProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity class for storing information about
 * a room with roomID as primary key
 */
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;
    private String roomName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildingId", referencedColumnName = "buildingId")
    Building building;

    @OneToMany(mappedBy = "room", targetEntity = Section.class)
    Set<Section> sections;

    private int numberOfSections;

    public Room(String roomName, Set<Section> sections) {
        this.roomName = roomName;
        this.sections = sections;
        this.numberOfSections = sections.size();
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
                ", sections=" + sections +
                ", numberOfSections=" + numberOfSections +
                '}';
    }
}
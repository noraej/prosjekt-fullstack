package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long buildingId;
    private String buildingName;

    @OneToMany(mappedBy = "building", targetEntity = Room.class)
    private Set<Room> rooms;

    private int numberOfRooms = rooms.size();

    public Building(long buildingId, String buildingName, Set<Room> rooms, int numberOfRooms) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.rooms = rooms;
        this.numberOfRooms = numberOfRooms;
    }

    public Building() {

    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", rooms=" + rooms +
                ", numberOfRooms=" + numberOfRooms +
                '}';
    }
}
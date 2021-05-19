package idatt2105.hamsterGroup.fullstackProject.Model.DTO;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;

/**
 * UserDTO, DTO class for sending user information
 */
public class BuildingDTO {
    private long buildingId;
    private String buildingName;
    private int numberOfRooms;


    public BuildingDTO(long buildingId, String buildingName, int numberOfRooms) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.numberOfRooms = numberOfRooms;
    }

    public BuildingDTO() {
    }

    public BuildingDTO(Building building){
        this.buildingId = building.getBuildingId();
        this.buildingName = building.getBuildingName();
        this.numberOfRooms = building.getNumberOfRooms();
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

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return "BuildingDTO{" +
                "buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                '}';
    }
}


package idatt2105.hamsterGroup.fullstackProject.Model.DTO;

import idatt2105.hamsterGroup.fullstackProject.Model.Room;

/**
 * RoomDTO, DTO class for sending room information
 */
public class RoomDTO {
    private long roomId;
    private String roomName;
    private int numberOfSections;


    public RoomDTO(long roomId, String roomName, int numberOfSections) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.numberOfSections = numberOfSections;
    }

    public RoomDTO() {
    }

    public RoomDTO(Room room){
        this.roomId = room.getRoomId();
        this.roomName = room.getRoomName();
        this.numberOfSections = room.getNumberOfSections();
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

    public int getNumberOfSections() {
        return numberOfSections;
    }

    public void setNumberOfSections(int numberOfSections) {
        this.numberOfSections = numberOfSections;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", numberOfSections=" + numberOfSections +
                '}';
    }
}

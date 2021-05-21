package idatt2105.hamsterGroup.fullstackProject.Model.DTO;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;

import java.time.LocalDateTime;

/**
 * FilterDTO, DTO class to filter
 */
public class FilterSortDTO {
    private long buildingId;
    private long roomId;
    private int minNumberOfSeats = -1;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FilterSortDTO() {
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getMinNumberOfSeats() {
        return minNumberOfSeats;
    }

    public void setMinNumberOfSeats(int minNumberOfSeats) {
        this.minNumberOfSeats = minNumberOfSeats;
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

    @Override
    public String toString() {
        return "FilterSortDTO{" +
                "building=" + buildingId +
                ", room=" + roomId +
                ", minNumberOfSeats=" + minNumberOfSeats +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

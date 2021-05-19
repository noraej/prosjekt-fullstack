package idatt2105.hamsterGroup.fullstackProject.Model.DTO;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;

import java.time.LocalDateTime;

/**
 * FilterDTO, DTO class to filter
 */
public class FilterSortDTO {
    private Building building;
    private int minNumberOfSeats = -1;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public FilterSortDTO() {
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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
}

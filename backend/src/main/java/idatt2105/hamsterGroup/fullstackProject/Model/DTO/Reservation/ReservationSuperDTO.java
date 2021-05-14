package idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ReservationSuperDTO, Super DTO class for different reservation DTOs
 */
public class ReservationSuperDTO {
    private String description;
    private Section section;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime endTime;
    private int durationMinutes;
    private int numberOfUsers;
    private Room room;
    private Building building;

    public ReservationSuperDTO(String description, Section section, LocalDateTime startTime,
                               LocalDateTime endTime, int durationMinutes, int numberOfUsers, Room room, Building building) {
        this.description = description;
        this.section = section;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.numberOfUsers = numberOfUsers;
        this.room = room;
        this.building = building;
    }

    public ReservationSuperDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        this.durationMinutes = (int) (duration.getSeconds() /60);
        System.out.println(this.durationMinutes);
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "\ndescription='" + description + '\'' +
                ", \nbuilding=" + building +
                ", \nroom=" + room +
                ", \nsection=" + section +
                ", \nstartTime=" + startTime +
                ", \nendTime=" + endTime +
                ", \ndurationMinutes=" + durationMinutes +
                ", \nnumberOfUsers=" + numberOfUsers +
                '}';
    }
}

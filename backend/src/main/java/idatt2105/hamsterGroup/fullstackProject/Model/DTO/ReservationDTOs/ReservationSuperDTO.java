package idatt2105.hamsterGroup.fullstackProject.Model.DTO.ReservationDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.SectionDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ReservationSuperDTO, Super DTO class for different reservation DTOs
 */
public class ReservationSuperDTO {
    private String description;
    private SectionDTO section;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime endTime;
    private int durationMinutes;
    private int numberOfUsers;

    public ReservationSuperDTO(String description, SectionDTO section, LocalDateTime startTime,
                               LocalDateTime endTime, int numberOfUsers) {
        this.description = description;
        this.section = section;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = calculateDuration();
        this.numberOfUsers = numberOfUsers;
    }

    public ReservationSuperDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SectionDTO getSection() {
        return section;
    }

    public void setSection(SectionDTO section) {
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

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public int calculateDuration() {
        Duration durationMinutes = Duration.between(startTime, endTime);
        return (int) (durationMinutes.getSeconds() /60);
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "\ndescription='" + description + '\'' +
                ", \nsection=" + section +
                ", \nstartTime=" + startTime +
                ", \nendTime=" + endTime +
                ", \ndurationMinutes=" + durationMinutes +
                ", \nnumberOfUsers=" + numberOfUsers +
                '}';
    }
}


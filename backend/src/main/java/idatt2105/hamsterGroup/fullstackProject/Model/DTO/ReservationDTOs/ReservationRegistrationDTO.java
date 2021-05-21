package idatt2105.hamsterGroup.fullstackProject.Model.DTO.ReservationDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

import java.time.LocalDateTime;

/**
 * ReservationRegistrationDTO, DTO class to transfer data when
 * creating a reservation
 */
public class ReservationRegistrationDTO {

    private String description;
    private long sectionId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime endTime;
    private int numberOfUsers;

    public ReservationRegistrationDTO(String description, long sectionId, LocalDateTime startTime, LocalDateTime endTime,
                                       int numberOfUsers) {
        this.description = description;
        this.sectionId = sectionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfUsers = numberOfUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
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

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public String toString()
    {
        return super.toString() + '\'' +
                '}';
    }
}

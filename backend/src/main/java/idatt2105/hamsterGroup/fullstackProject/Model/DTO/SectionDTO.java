package idatt2105.hamsterGroup.fullstackProject.Model.DTO;

import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

/**
 * RoomDTO, DTO class for sending room information
 */
public class SectionDTO {
    private long sectionId;
    private String sectionName;
    private String description;
    private int seats;
    private double size;


    public SectionDTO(long sectionId, String sectionName, String description, int seats, double size) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.seats = seats;
        this.size = size;
    }

    public SectionDTO() {
    }

    public SectionDTO(Section section){
        this.sectionId = section.getSectionId();
        this.sectionName = section.getSectionName();
        this.description = section.getDescription();
        this.seats = section.getSeats();
        this.size = section.getSize();
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                ", size=" + size +
                '}';
    }
}
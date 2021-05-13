package idatt2105.hamsterGroup.fullstackProject.Model;

import javax.persistence.*;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sectionId;
    private String sectionName;
    private String description;
    private int seats;

    public Section(long sectionId, String sectionName, String description, int seats) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.seats = seats;
    }

    public Section() {
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

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                '}';
    }
}
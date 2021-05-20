package idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

import java.time.LocalDateTime;

/**
 * ReservationRegistrationDTO, DTO class to transfer data when
 * creating a reservation
 */
public class ReservationRegistrationDTO extends ReservationSuperDTO{

    public ReservationRegistrationDTO(String description, Section section, LocalDateTime startTime, LocalDateTime endTime,
                                       int numberOfUsers, Room room, Building building) {
        super(description, section, startTime, endTime, numberOfUsers, room, building
        );
    }

    public ReservationRegistrationDTO(){
        super();
    }

    @Override
    public String toString()
    {
        return super.toString() + '\'' +
                '}';
    }
}

package idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation;

import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;

/**
 * ReservationDTO, DTO class to use when transferring reservation data
 */
public class ReservationDTO extends ReservationSuperDTO {
    private long reservationId;
    private long userId;
    private String userFirstName;
    private String userLastName;


    public ReservationDTO(Reservation reservation){
        super(reservation.getDescription(), reservation.getSection(), reservation.getStartTime(), reservation.getEndTime(),
                reservation.getDuration(), reservation.getNumberOfUsers(), reservation.getRoom(), reservation.getBuilding());
        this.reservationId = reservation.getReservationId();
        if(reservation.getUser() != null) {
            this.userId = reservation.getUser().getUserId();
            this.userFirstName = reservation.getUser().getFirstName();
            this.userLastName = reservation.getUser().getLastName();
        }
    }

    public ReservationDTO(){
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Override
    public String toString() {
        return super.toString() +
                "reservationId=" + reservationId +
                ", userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }
}

package idatt2105.hamsterGroup.fullstackProject.Repository;

import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     * Query that return a list of reservations with start time after NOW, sorted by start time
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.start_time > NOW()", nativeQuery = true)
    public List<Reservation> findAllReservationsFromNow();

    /**
     * Query that return a list of a user's reservations in the future
     */
    @Query(value = "SELECT reservation.* FROM reservation JOIN user_reservation ON " +
            "(reservation.reservation_id = user_reservation.reservation_id AND user_reservation.user_id=?1 " +
            "AND reservation.start_time > NOW()", nativeQuery = true)
    public List<Reservation> findFutureReservationsUser(Long userId);

    /**
     * Query that return a list of reservations sorted by earliest start time
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.start_time > NOW() ORDER BY " +
            "reservation.start_time", nativeQuery = true)
    public List<Reservation> sortReservationsTime();

    /**
     * Query that return a list of reservations with room filter
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.room_id=?1 AND reservation.start_time > NOW()",
            nativeQuery = true)
    public List<Reservation> findReservationByRoom(long roomId);

    /**
     * Query that return a list of reservations with room filter and sorts by time
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.room_id=?1 AND reservation.start_time > NOW() ORDER BY" +
            "reservation.start_time", nativeQuery = true)
    public List<Reservation> findReservationByRoomSorted(long roomId);

    /**
     * Query that return a list of reservations with building filter
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.building_id=?1 AND reservation.start_time > NOW()",
            nativeQuery = true)
    public List<Reservation> findReservationByBuilding(long buildingId);

    /**
     * Query that return a list of reservations with building filter and sorts by time
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.building_id=?1 AND reservation.start_time > NOW() ORDER BY" +
            "reservation.start_time", nativeQuery = true)
    public List<Reservation> findReservationByBuildingSorted(long buildingId);

    /**
     * Query that return a list of reservations with room and building filter
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.room_id=?1 AND reservation.building_id=?2 " +
            "AND reservation.start_time > NOW()", nativeQuery = true)
    public List<Reservation> findReservationByRoomAndBuilding(long roomId, long buildingId);

    /**
     * Query that return a list of reservations with room and building filter
     */
    @Query(value = "SELECT * FROM reservation WHERE reservation.room_id=?1 AND reservation.building_id=?2 " +
            "AND reservation.start_time > NOW() ORDER BY reservation.start_time", nativeQuery = true)
    public List<Reservation> findReservationByRoomAndBuildingSorted(long roomId, long buildingId);
}
package idatt2105.hamsterGroup.fullstackProject.Repository;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
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
    @Query(value = "SELECT * FROM reservation WHERE reservation.start_time=?1 AND reservation.end_time=?2 AND" +
            "reservation.seats>?3 AND reservation.building_id=?4", nativeQuery = true)
    public List<Reservation> sortReservationsWithFilters(LocalDateTime start, LocalDateTime end, int minSeats, Building building);
}
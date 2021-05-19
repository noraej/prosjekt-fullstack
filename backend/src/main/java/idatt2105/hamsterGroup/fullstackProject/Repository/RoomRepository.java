package idatt2105.hamsterGroup.fullstackProject.Repository;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Query that return a list of all rooms
     */
    @Query(value = "SELECT * FROM room)", nativeQuery = true)
    public List<Room> getRooms();

    /**
     * Query that return a list of rooms with given start time, where user is not set
     */
    @Query(value = "SELECT * FROM room, reservation WHERE room.start_time=?1 AND reservation.room_id <> room.room_id", nativeQuery = true)
    public List<Room> findAvailableRoomsByStartTime(LocalDateTime start);

    /**
     * Query that return a list of rooms sorted by filters
     */
    @Query(value = "SELECT * FROM room WHERE room.building_id=?1", nativeQuery = true)
    public List<Room> findRoomByBuilding(long buildingId);
}

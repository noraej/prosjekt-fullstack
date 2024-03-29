package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.RoomDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Creates an endpoint for room
 */

@Service
public class RoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Method to get a room given the room ID
     * @param roomId - id of room
     * @return RoomDTO
     */
    public RoomDTO getRoom(long roomId) {
        LOGGER.info("getRoom(long roomId) called with room ID: " + roomId);
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            return new RoomDTO(room.get());
        }
        return null;
    }

    /**
     * Method to return all rooms from database
     * @return List of rooms
     */
    public List<RoomDTO> getRooms() {
        LOGGER.info("getRooms() was called");
        return roomRepository.getRooms().stream().
                map(RoomDTO::new).collect(Collectors.toList());
    }

    /**
     * Method to filter room list
     * @param buildingId - building
     * @return List of room DTOs
     */
    public List<RoomDTO> getRoomsByBuilding(long buildingId) {
        LOGGER.info("getReservationsWithFilter(FilterDTO filter) was called with filter a filter");
        return roomRepository.getAllByBuildingBuildingId(buildingId).stream().map(RoomDTO::new).collect(Collectors.toList());
    }
}

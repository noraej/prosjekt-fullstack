package idatt2105.hamsterGroup.fullstackProject.Controller;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.RoomDTO;
import idatt2105.hamsterGroup.fullstackProject.Service.RoomService;
import idatt2105.hamsterGroup.fullstackProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the rooms, to connect from frontend
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController
{
    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @GetMapping("/{room_id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable("room_id") long roomId) {
        RoomDTO returnRoom = roomService.getRoom(roomId);
        if (returnRoom == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnRoom, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms() {
        List<RoomDTO> rooms = roomService.getRooms();
        if (rooms == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping("/alternatives")
    public ResponseEntity<List<RoomDTO>> getRoomWithFilter(@RequestBody FilterSortDTO filter){
        return new ResponseEntity<>(roomService.getRoomsByBuilding(filter),HttpStatus.OK);
    }

    /* //Admins should be able to create rooms in the app at one point, but down prioritized 
    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomCreationDTO room) {
    }
     */

    /* //Same here
    @DeleteMapping("/{room_id}")
    @PreAuthorize("...")
    public ResponseEntity<String> deleteRoom(@PathVariable("room_id") long roomId) {
    }
     */
}

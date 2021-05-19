package idatt2105.hamsterGroup.fullstackProject.Controller;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.BuildingDTO;
import idatt2105.hamsterGroup.fullstackProject.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the buildings, to connect from frontend
 */
@RestController
@RequestMapping("/api/buildings")
public class BuildingController
{
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/{building_id}")
    public ResponseEntity<BuildingDTO> getBuilding(@PathVariable("building_id") long buildingId) {
        BuildingDTO returnBuilding = buildingService.getBuilding(buildingId);
        if (returnBuilding == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnBuilding, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BuildingDTO>> getBuildings() {
        List<BuildingDTO> buildings = buildingService.getBuildings();
        if (buildings == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    /* //Admins should be able to create rooms in the app at one point, but down prioritized for now
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

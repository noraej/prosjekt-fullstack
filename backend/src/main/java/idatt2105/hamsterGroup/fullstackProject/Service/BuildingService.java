package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.BuildingDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.RoomDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Repository.BuildingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Creates an endpoint for building
 */

@Service
public class BuildingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * Method to get a building given the room ID
     * @param buildingId - id of building
     * @return BuildingDTO
     */
    public BuildingDTO getBuilding(long buildingId) {
        LOGGER.info("getBuilding(long buildingId) called with building ID: " + buildingId);
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isPresent()) {
            return new BuildingDTO(building.get());
        }
        return null;
    }

    /**
     * Method to return all buildings from database
     * @return List of buildings
     */
    public List<BuildingDTO> getBuildings() {
        LOGGER.info("getBuildings() was called");
        return buildingRepository.getBuildings().stream().
                map(BuildingDTO::new).collect(Collectors.toList());
    }
}
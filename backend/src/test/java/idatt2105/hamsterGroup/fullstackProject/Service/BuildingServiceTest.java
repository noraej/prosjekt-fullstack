package idatt2105.hamsterGroup.fullstackProject.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import idatt2105.hamsterGroup.fullstackProject.Model.*;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.BuildingDTO;
import idatt2105.hamsterGroup.fullstackProject.Repository.BuildingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class BuildingServiceTest {
    @InjectMocks
    private BuildingService buildingService;

    @Mock
    private BuildingRepository buildingRepository;

    @BeforeEach
    public void setup() {
        Section section = new Section("Section", "Description", 5, 20, null);
        Set<Section> sections = new HashSet<>();
        sections.add(section);

        Room room = new Room("Room", sections);
        Set<Room> rooms = new HashSet<>();
        rooms.add(room);

        Building building1 = new Building("Building1", rooms);
        Building building2 = new Building("Building2", rooms);

        building1.setBuildingId(0L);

        Mockito.lenient()
                .when(buildingRepository.findById(building1.getBuildingId()))
                .thenReturn(java.util.Optional.of(building1));
        Mockito.lenient()
                .when(buildingRepository.existsById(building1.getBuildingId()))
                .thenReturn(true);
        Mockito.lenient()
                .when(buildingRepository.existsById(building2.getBuildingId()))
                .thenReturn(false);

        List<Building> buildings = new ArrayList<>();
        buildings.add(building1);
        buildings.add(building2);
        Mockito.lenient()
                .when(buildingRepository.getBuildings())
                .thenReturn(buildings);
    }

    @Test
    public void getBuilding_IdExists_BuildingIsCorrect()
    {
        long buildingId = 0L;
        BuildingDTO buildingDTO = buildingService.getBuilding(buildingId);
        assertThat(buildingDTO.getBuildingName()).isEqualTo("Building1");
    }

    @Test
    public void getBuilding_IdDoesNotExist_ReturnNull() {
        long buildingId = -1L;
        BuildingDTO buildingDTO = buildingService.getBuilding(buildingId);
        assertNull(buildingDTO);
    }

    @Test
    public void getBuildings_ReturnListAndStatus_ListNotNull() {
        List<BuildingDTO> buildings = buildingService.getBuildings();
        assertNotNull(buildings);

        assertThat(buildings.get(0).getBuildingName()).isEqualTo("Building1");
    }
}
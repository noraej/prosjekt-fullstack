package idatt2105.hamsterGroup.fullstackProject.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import idatt2105.hamsterGroup.fullstackProject.Model.*;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.RoomDTO;
import idatt2105.hamsterGroup.fullstackProject.Repository.RoomRepository;
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
public class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setup() {
        Section section = new Section("Section", "Description", 5, 20, null);
        Set<Section> sections = new HashSet<>();
        sections.add(section);

        Room room1 = new Room("Room1", sections);
        Room room2 = new Room("Room2", sections);
        Room room3 = new Room("Room1", sections);

        room1.setRoomId(0L);
        room2.setRoomId(1L);

        Mockito.lenient()
                .when(roomRepository.findById(room1.getRoomId()))
                .thenReturn(java.util.Optional.of(room1));
        Mockito.lenient()
                .when(roomRepository.findById(room2.getRoomId()))
                .thenReturn(java.util.Optional.of(room2));

        Mockito.lenient()
                .when(roomRepository.existsById(room1.getRoomId()))
                .thenReturn(true);
        Mockito.lenient()
                .when(roomRepository.existsById(room2.getRoomId()))
                .thenReturn(true);
        Mockito.lenient()
                .when(roomRepository.existsById(room3.getRoomId()))
                .thenReturn(false);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        Mockito.lenient()
                .when(roomRepository.getRooms())
                .thenReturn(rooms);
    }

    @Test
    public void getRoom_IdExists_RoomIsCorrect()
    {
        long roomId = 0L;
        RoomDTO roomDTO = roomService.getRoom(roomId);
        assertThat(roomDTO.getRoomName()).isEqualTo("Room1");
    }

    @Test
    public void getRoom_IdDoesNotExist_ReturnNull() {
        long roomId = -1L;
        RoomDTO roomDTO = roomService.getRoom(roomId);
        assertNull(roomDTO);
    }

    @Test
    public void getRooms_ReturnListAndStatus_ListNotNull() {
        List<RoomDTO> rooms = roomService.getRooms();
        assertNotNull(rooms);

        assertThat(rooms.get(0).getRoomName()).isEqualTo("Room1");
    }
}


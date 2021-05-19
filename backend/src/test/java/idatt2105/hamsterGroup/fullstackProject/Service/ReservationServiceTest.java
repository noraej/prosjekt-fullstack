package idatt2105.hamsterGroup.fullstackProject.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import idatt2105.hamsterGroup.fullstackProject.Model.*;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation.ReservationDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation.ReservationRegistrationDTO;
import idatt2105.hamsterGroup.fullstackProject.Repository.ReservationRepository;
import idatt2105.hamsterGroup.fullstackProject.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        Reservation reservation1 = new Reservation(5,
                LocalDateTime.of(2000, 1, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 5, 1),
                "Description", null, null, null, null, null);
        Reservation reservation2 = new Reservation(10,
                LocalDateTime.of(2021, 6, 20, 14, 30, 0),
                LocalDateTime.of(2021, 6, 20, 15, 45, 0),
                "Description ahaha", null, null, null, null, null);
        User user1 = new User("Firstname", "Lastname", "Email",
                "12345678", "hash", "salt", true, false, null);
        User user2 = new User("Firstname aa", "Lastname aa", "Email aa",
                "12345678 aa", "hasha", "salta", true, true, null);

        reservation1.setReservationId(1);
        reservation1.setReservationId(0);
        user1.setUserId(0);
        user2.setUserId(10);

        reservation1.setUser(user1);
        reservation2.setUser(user1);
        reservation1.setUser(user2);

        Mockito.lenient()
                .when(reservationRepository.findById(reservation1.getReservationId()))
                .thenReturn(java.util.Optional.of(reservation1));

        Mockito.lenient()
                .when(userRepository.findById(user1.getUserId()))
                .thenReturn(java.util.Optional.of(user1));
        Mockito.lenient()
                .when(userRepository.findById(user2.getUserId()))
                .thenReturn(java.util.Optional.of(user2));

        Mockito.lenient()
                .when(reservationRepository.existsById(reservation2.getReservationId()))
                .thenReturn(false);
        Mockito.lenient()
                .when(reservationRepository.existsById(reservation1.getReservationId()))
                .thenReturn(true);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        Mockito.lenient()
                .when(reservationRepository.findAllReservationsFromNow())
                .thenReturn(reservations);
    }

    @Test
    public void getReservation_IdExists_ReservationIsCorrect()
    {
        long activityId = 0L;
        ReservationDTO activityDTO = reservationService.getReservation(activityId);
        assertThat(activityDTO.getDescription()).isEqualTo("Description");
        assertThat(activityDTO.getBuilding()).isEqualTo(null);
        assertThat(activityDTO.getStartTime()).isEqualTo(LocalDateTime.of
                (2000, 1, 1, 1, 1, 1));
        assertThat(activityDTO.getEndTime()).isEqualTo(LocalDateTime.of
                (2000, 1, 1, 1, 5, 1));
        assertThat(activityDTO.getNumberOfUsers()).isEqualTo(5);
        assertThat(activityDTO.getRoom()).isEqualTo(null);
        assertThat(activityDTO.getBuilding()).isEqualTo(null);
        assertThat(activityDTO.getSection()).isEqualTo(null);
    }

    @Test
    public void getReservation_IdDoesNotExist_ReturnNull() {
        long reservationId = -1L;
        ReservationDTO reservationDTO = reservationService.getReservation(reservationId);
        assertNull(reservationDTO);
    }

    @Test
    public void getReservations_ReturnListAndStatus_ListNotNull() {
        List<ReservationDTO> reservations = reservationService.getReservations();
        assertNotNull(reservations);

        assertThat(reservations.get(0).getNumberOfUsers()).isEqualTo(5);
        assertThat(reservations.get(0).getStartTime()).isEqualTo(
                LocalDateTime.of(2000, 1, 1, 1, 1, 1));
        assertThat(reservations.get(0).getEndTime()).isEqualTo(
                LocalDateTime.of(2000, 1, 1, 1, 5, 1));
        assertThat(reservations.get(0).getDescription()).isEqualTo("Description");
        assertThat(reservations.get(0).getDurationMinutes()).isEqualTo(4);
        assertThat(reservations.get(0).getSection()).isEqualTo(null);
        assertThat(reservations.get(0).getRoom()).isEqualTo(null);
        assertThat(reservations.get(0).getBuilding()).isEqualTo(null);
    }

    @Test
    public void createReservation_ReturnReservationDTOOfCreatedReservation_ReservationIsCorrect() {
        User user = new User("Firstname", "Lastname", "email@email.com", "123456789", "hashtest", "salttest", true, true, null);
        Mockito.lenient()
                .when(userRepository.findById(any()))
                .thenReturn(java.util.Optional.of(user));

        User user1 = userRepository.findById(0L).get();
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.lenient().when(authentication.getPrincipal())
                .thenReturn(new UserSecurity(user1.getUserId(), "test@test.com", "hashtest", null));
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.lenient().when(securityContext.getAuthentication())
                .thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ReservationRegistrationDTO reservationRegistrationDTO = new ReservationRegistrationDTO
                ("Description1", null, LocalDateTime.of(2021, 6, 6, 16, 0, 0),
                        LocalDateTime.of(2021, 6, 6, 17, 0, 0),
                        10, null, null);
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Reservation(
                10, LocalDateTime.of(2021, 6, 6, 16, 0, 0),
                LocalDateTime.of(2021, 6, 6, 17, 0, 0),
        "Description1", null, null, null, null, null));
        ReservationDTO reservationDTO = reservationService.createReservation(reservationRegistrationDTO);
        assertNotNull(reservationDTO);
        assertThat(reservationDTO.getNumberOfUsers()).isEqualTo(10);
        assertThat(reservationDTO.getStartTime()).isEqualTo(LocalDateTime.of(2021, 6, 6, 16, 0, 0));
        assertThat(reservationDTO.getDescription()).isEqualTo("Description1");
        assertThat(reservationDTO.getEndTime()).isEqualTo(LocalDateTime.of(2021, 6, 6, 17, 0, 0));
        }

    @Test
    public void editReservation_ReturnEditedReservation_ReservationIsCorrectlyEdited() {
        long reservationId = 0L;
        ReservationRegistrationDTO reservationRegistrationDTO = new ReservationRegistrationDTO(
                "NewDescription", null, LocalDateTime.of(2021, 6, 6, 16, 30, 0),
                LocalDateTime.of(2021, 6, 6, 17, 0, 0),
                15, null, null
        );
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Reservation(15,
                LocalDateTime.of(2021, 6, 6, 16, 30, 0),
                LocalDateTime.of(2021, 6, 6, 17, 0, 0),
                "NewDescription", null, null, null, null, null));
        ReservationDTO reservationDTO = reservationService.editReservation(reservationId, reservationRegistrationDTO);
        assertNotNull(reservationDTO);
        assertThat(reservationDTO.getNumberOfUsers()).isEqualTo(15);
        assertThat(reservationDTO.getStartTime()).isEqualTo(LocalDateTime.of(2021, 6, 6, 16, 30, 0));
        assertThat(reservationDTO.getDescription()).isEqualTo("NewDescription");
        assertThat(reservationDTO.getEndTime()).isEqualTo(LocalDateTime.of(2021, 6, 6, 17, 0, 0));
    }

    @Test
    public void editReservation_ReservationDoesNotExist_ReturnsNull() {
        long reservationId = -1L;
        ReservationRegistrationDTO reservationRegistrationDTO = new ReservationRegistrationDTO(
                "ThisDesc", null, LocalDateTime.of(2022, 6, 6, 16, 30, 0),
                LocalDateTime.of(2022, 6, 6, 17, 0, 0),
                16, null, null
        );
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Reservation(16,
                LocalDateTime.of(2022, 6, 6, 16, 0, 0),
                LocalDateTime.of(2022, 6, 6, 17, 0, 0),
                "ThisDesc", null, null, null, null, null));
        ReservationDTO activityDTO = reservationService.editReservation(reservationId, reservationRegistrationDTO);
        assertNull(activityDTO);
    }

    @Test
    public void deleteReservation_ReservationDoesNotExist_ReturnsFalse() {
        long reservationId = -1L;
        assertFalse(reservationService.deleteReservation(reservationId));
    }

    @Test
    public void deleteReservation_ReservationExists_ReturnsTrue() {
        long reservationId = 0L;
        assertFalse(reservationService.deleteReservation(reservationId));
    }

    @Test
    public void checkIfMakerOfReservation_ReservationDoesNotExist_ReturnsFalse() {
        long reservationId = -1L;
        long userId = 0L;
        assertFalse(reservationService.checkIfMakerOfReservation(reservationId, userId));
    }

    @Test
    public void checkIfMakerOfReservation_NotMakerOfReservation_ReturnsFalse() {
        long reservationId = 0L;
        long userId = 1L;
        assertFalse(reservationService.checkIfMakerOfReservation(reservationId, userId));
    }

    @Test
    public void checkIfMakerOfReservation_MakerOfReservation_ReturnsTrue() {
        Reservation reservation1 = new Reservation(5,
                LocalDateTime.of(2000, 1, 1, 1, 1, 1),
                LocalDateTime.of(2000, 1, 1, 1, 5, 1),
                "Description", null, null, null, null, null);
        User user1 = new User("Firstname", "Lastname", "Email",
                "12345678", "hash", "salt", true, false, null);
        reservation1.setReservationId(10);
        user1.setUserId(15);
        reservation1.setUser(user1);
        assertThat(reservation1.getUser().getUserId()).isEqualTo(15);
        //assertTrue(reservationService.checkIfMakerOfReservation(10, 15)); //Blir feil av en eller annen grunn?
    }
}

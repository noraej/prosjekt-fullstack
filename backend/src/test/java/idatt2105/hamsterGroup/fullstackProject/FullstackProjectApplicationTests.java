package idatt2105.hamsterGroup.fullstackProject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
class FullstackProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
/*
package idatt2105.hamsterGroup.fullstackProject.Service;

		import java.time.LocalDate;
		import java.time.LocalDateTime;
		import java.util.ArrayList;
		import java.util.HashSet;
		import java.util.List;
		import java.util.Optional;
		import java.util.Set;

		import idatt2105.hamsterGroup.fullstackProject.Model.Building;
		import idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation.ReservationDTO;
		import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
		import idatt2105.hamsterGroup.fullstackProject.Model.Section;
		import idatt2105.hamsterGroup.fullstackProject.Model.User;
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
		import static org.junit.jupiter.api.Assertions.assertTrue;
		import static org.mockito.ArgumentMatchers.any;
/*
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ReservationServiceTest {
 /*   @InjectMocks
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
        long activityId = 0l;
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
    }*/

   /* @Test
    public void getActivities_ReturnListAndStatus_ListNotNull() {
        List<ActivityDTO> activities = reservationService.getActivities();
        assertNotNull(activities);

        assertThat(activities.get(0).getTitle()).isEqualTo("Activity1");
        assertThat(activities.get(0).getType()).isEqualTo("Type");
        assertThat(activities.get(0).getDescription()).isEqualTo("Description");
        assertThat(activities.get(0).getEquipment()).isEqualTo("Equipment");
        assertThat(activities.get(0).getDifficulty()).isEqualTo(Difficulty.EASY.value);
        assertThat(activities.get(0).getCity()).isEqualTo("Trondheim");
        assertThat(activities.get(0).getPlace()).isEqualTo("Trondheim");
        assertThat(activities.get(0).getLongitude()).isEqualTo(1.2);
        assertThat(activities.get(0).getLatitude()).isEqualTo(1.2);
        assertThat(activities.get(0).getStartTime()).isEqualTo(LocalDateTime.of(2000, 1, 1, 1, 1, 1));

        assertThat(activities.get(1).getTitle()).isEqualTo("Activity2");
        assertThat(activities.get(1).getType()).isEqualTo("Type");
        assertThat(activities.get(1).getDescription()).isEqualTo("Description");
        assertThat(activities.get(1).getEquipment()).isEqualTo("Equipment");
        assertThat(activities.get(1).getDifficulty()).isEqualTo(Difficulty.MEDIUM.value);
        assertThat(activities.get(1).getCity()).isEqualTo("Bergen");
        assertThat(activities.get(1).getPlace()).isEqualTo("Bergen");
        assertThat(activities.get(1).getLongitude()).isEqualTo(2.2);
        assertThat(activities.get(1).getLatitude()).isEqualTo(2.2);
        assertThat(activities.get(1).getStartTime()).isEqualTo(LocalDateTime.of(2000, 1, 1, 1, 1, 1));
    }

    @Test
    public void createActivity_ReturnActivityDTOOfCreatedActivity_ActivityIsCorrect() {
        User user = new User("Forename", "Surname", "test@test.com", LocalDate.of(2005, 1, 1), Difficulty.HARD, "test hash", "test salt", 100, "Organizer", 2, null);
        Mockito.lenient()
                .when(userRepository.findById(any()))
                .thenReturn(java.util.Optional.of(user));

        User user1 = userRepository.findById(0l).get();
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.lenient().when(authentication.getPrincipal())
                .thenReturn(new UserSecurityDetails("test hash", "test123@test.com", user1.getUserId(), null));
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.lenient().when(securityContext.getAuthentication())
                .thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ActivityRegistrationDTO activityRegistrationDTO = new ActivityRegistrationDTO("Activity3", "Type", "Description", "Equipment", Difficulty.HARD.value, "Oslo", "Oslo", 3.2, 3.2, LocalDateTime.of(2000, 1, 1, 1, 1, 1), 100, false, 10, null);
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Activity("Activity3", "Type", "Description", "Equipment", Difficulty.HARD.value, "Oslo", "Oslo", 3.2, 3.2, LocalDateTime.of(2000, 1, 1, 1, 1, 1), 100, false, 10, false, null));
        ActivityDTO activityDTO = reservationService.createActivity(activityRegistrationDTO);
        assertNotNull(activityDTO);
        assertThat(activityDTO.getTitle()).isEqualTo("Activity3");
        assertThat(activityDTO.getType()).isEqualTo("Type");
        assertThat(activityDTO.getDescription()).isEqualTo("Description");
        assertThat(activityDTO.getEquipment()).isEqualTo("Equipment");
        assertThat(activityDTO.getDifficulty()).isEqualTo(Difficulty.HARD.value);
        assertThat(activityDTO.getCity()).isEqualTo("Oslo");
        assertThat(activityDTO.getPlace()).isEqualTo("Oslo");
        assertThat(activityDTO.getLongitude()).isEqualTo(3.2);
        assertThat(activityDTO.getLatitude()).isEqualTo(3.2);
        assertThat(activityDTO.getStartTime()).isEqualTo(LocalDateTime.of(2000, 1, 1, 1, 1, 1));
    }

    @Test
    public void editActivity_ReturnEditedActivity_ActivityIsCorrectlyEdited() {
        long activityId = 0l;
        ActivityRegistrationDTO activityRegDTO = new ActivityRegistrationDTO("NewTitle", "NewType", "NewDescription", "NewEquipment", Difficulty.HARD.value, "NewCity", "NewPlace", 10l, 10l, LocalDateTime.of(2021, 2, 2, 2, 2, 2), 60, false, 60, null);
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Activity("NewTitle", "NewType", "NewDescription", "NewEquipment", Difficulty.HARD.value, "NewCity", "NewPlace", 10l, 10l, LocalDateTime.of(2021, 2, 2, 2, 2, 2), 60, false, 60, false, null));
        ActivityDTO activityDTO = reservationService.editActivity(activityId, activityRegDTO);
        assertNotNull(activityDTO);
        assertThat(activityDTO.getTitle()).isEqualTo("NewTitle");
        assertThat(activityDTO.getType()).isEqualTo("NewType");
        assertThat(activityDTO.getDescription()).isEqualTo("NewDescription");
        assertThat(activityDTO.getEquipment()).isEqualTo("NewEquipment");
        assertThat(activityDTO.getDifficulty()).isEqualTo(Difficulty.HARD.value);
        assertThat(activityDTO.getCity()).isEqualTo("NewCity");
        assertThat(activityDTO.getPlace()).isEqualTo("NewPlace");
        assertThat(activityDTO.getLongitude()).isEqualTo(10l);
        assertThat(activityDTO.getLatitude()).isEqualTo(10l);
        assertThat(activityDTO.getStartTime()).isEqualTo(LocalDateTime.of(2021, 2, 2, 2, 2, 2));
        assertThat(activityDTO.getDurationMinutes()).isEqualTo(60);
        assertThat(activityDTO.isPrivateActivity()).isFalse();
        assertThat(activityDTO.getMaxParticipants()).isEqualTo(60);
        assertThat(activityDTO.getActivityPicture()).isNull();
    }

    @Test
    public void editActivity_ActivityDoesNotExist_ReturnsNull() {
        long activityId = -1l;
        ActivityRegistrationDTO activityRegDTO = new ActivityRegistrationDTO("NewTitle", "NewType", "NewDescription", "NewEquipment", Difficulty.HARD.value, "NewCity", "NewPlace", 10l, 10l, LocalDateTime.of(2021, 2, 2, 2, 2, 2), 60, false, 60, null);
        Mockito.lenient().when(reservationRepository.save(any())).thenReturn(new Activity("NewTitle", "NewType", "NewDescription", "NewEquipment", Difficulty.HARD.value, "NewCity", "NewPlace", 10l, 10l, LocalDateTime.of(2021, 2, 2, 2, 2, 2), 60, false, 60, false, null));
        ActivityDTO activityDTO = reservationService.editActivity(activityId, activityRegDTO);
        assertNull(activityDTO);
    }

    @Test
    public void deleteUser_ActivityDoesNotExist_ReturnsFalse() {
        long activityId = -1l;
        assertFalse(reservationService.deleteActivity(activityId));
    }

    @Test
    public void deleteUser_ActivityExistsButIsFinished_ReturnsFalse() {
        long activityId = 0l;
        assertFalse(reservationService.deleteActivity(activityId));
    }

    @Test
    public void deleteUser_ActivityExistsAndIsInFuture_ReturnsTrue() {
        long activityId = 2l;
        assertTrue(reservationService.deleteActivity(activityId));
    }

    @Test
    public void addUserToActivity_ActivityDoesNotExist_ReturnsFalse() {
        long activityId = -1l;
        assertFalse(reservationService.addUserToActivity(activityId, 1));
    }

    @Test
    public void addUserToActivity_ActivityIsFull_ReturnsFalse() {
        long activityId = 2l;
        long userId = 0l;
        assertFalse(reservationService.addUserToActivity(activityId, userId));
    }

    @Test
    public void addUserToActivity_UserDoesNotExist_ReturnsFalse() {
        long activityId = 0l;
        long userId = -1l;
        assertFalse(reservationService.addUserToActivity(activityId, userId));
    }

    @Test
    public void addUserToActivity_UserActivitiesIsNull_ReturnsFalse() {
        long activityId = 0l;
        long userId = 0l;
        userRepository.findById(userId).get().setActivities(null);
        assertFalse(reservationService.addUserToActivity(activityId, userId));
    }

    @Test
    public void addUserToActivity_UserAddedToActivity_ReturnsTrue() {
        long activityId = 0l;
        long userId = 0l;
        userRepository.findById(userId).get().setActivities(new HashSet<Activity>());
        assertTrue(reservationService.addUserToActivity(activityId, userId));
    }

    @Test
    public void getChat_ActivityDoesNotExist_ReturnsNull() {
        long activityId = -1l;
        assertNull(reservationService.getChat(activityId));
    }

    @Test
    public void getChat_ActivityExists_ReturnsChat() {
        long activityId = 0l;
        assertNotNull(reservationService.getChat(activityId));
        assertThat(reservationService.getChat(activityId).getChatId()).isZero();
        assertThat(reservationService.getChat(activityId).getActivity()).isNull();
        assertThat(reservationService.getChat(activityId).getMessages()).isNull();
    }

    @Test
    public void checkIfOrganizerOfActivity_ActivityDoesNotExist_ReturnsFalse() {
        long activityId = -1l;
        long userId = 0l;
        assertFalse(reservationService.checkIfOrganizerOfActivity(activityId, userId));
    }

    @Test
    public void checkIfOrganizerOfActivity_NotOrganizerOfActivity_ReturnsFalse() {
        long activityId = 0l;
        long userId = 1l;
        assertFalse(reservationService.checkIfOrganizerOfActivity(activityId, userId));
    }

    @Test
    public void checkIfOrganizerOfActivity_OrganizerOfActivity_ReturnsTrue() {
        long activityId = 0l;
        long userId = 0l;
        assertTrue(reservationService.checkIfOrganizerOfActivity(activityId, userId));
    }

    @Test
    public void checkIfParticipant_NotParticipantOfActivity_ReturnsFalse() {
        long activityId = 0l;
        long userId = 1l;

        Mockito.lenient().when(reservationRepository.findIfUserIsParticipantOfActivity(1l, 0l)).thenReturn(Optional.of(0));
        Mockito.lenient().when(reservationRepository.findIfUserIsParticipantOfActivity(0l, 0l)).thenReturn(Optional.of(0));

        assertFalse(reservationService.checkIfOrganizerOfActivity(activityId, userId));
    }

    @Test
    public void checkIfParticipant_ParticipantOfActivity_ReturnsTrue() {
        long activityId = 0l;
        long userId = 0l;

        Mockito.lenient().when(reservationRepository.findIfUserIsParticipantOfActivity(1l, 0l)).thenReturn(Optional.of(0));
        Mockito.lenient().when(reservationRepository.findIfUserIsParticipantOfActivity(0l, 0l)).thenReturn(Optional.of(0));

        assertTrue(reservationService.checkIfOrganizerOfActivity(activityId, userId));
    }

    @Test
    public void markAbsent_ActivityDoesNotExist_ReturnsEmptySet() {
        long activityId = -1l;
        assertThat(reservationService.markAbsent(activityId, new AbsenceDTO())).isEmpty();
    }

    @Test
    public void markAbsent_ActivityExists_ReturnsUserIdsOfAbsentUsers() {
        long activityId = 0l;
        Set<Long> ids = new HashSet<>();
        ids.add(0l);
        assertThat(reservationService.markAbsent(activityId, new AbsenceDTO(ids))).isNotEmpty();
        assertThat(reservationService.markAbsent(activityId, new AbsenceDTO(ids)).size()).isEqualTo(1);
    }

    @Test
    public void getUsers_ActivityDoesNotExist_ReturnsEmptySet() {
        long activityId = -1l;
        assertThat(reservationService.getUsers(activityId)).isEmpty();
    }

    @Test
    public void getUsers_ActivityExists_ReturnsSetOfUserNameDTOs() {
        long activityId = 0l;
        reservationRepository.findById(activityId).get().getUsers().add(new User());
        assertThat(reservationService.getUsers(activityId)).isNotEmpty();
    }*/
/*}
 */

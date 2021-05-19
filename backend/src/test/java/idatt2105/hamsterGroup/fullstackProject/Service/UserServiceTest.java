package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Model.*;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserAndPasswordDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserEditDTO;
import idatt2105.hamsterGroup.fullstackProject.Repository.ReservationRepository;
import idatt2105.hamsterGroup.fullstackProject.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.sql.rowset.serial.SerialException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest
{
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setup()
    {
        User user1 = new User(), user2 = new User(), user3;
        user1.setUserId(0L);
        user1.setFirstName("testFirstname");
        user1.setLastName("testLastname");
        user1.setEmail("testMail");
        user1.setPhoneNumber("12345678");
        user1.setValid(true);
        user1.setAdmin(false);
        user1.setHash("testHash");
        user1.setSalt("testSalt");

        user2.setUserId(1L);
        user3 = user2;
        user3.setUserId(user1.getUserId());

        List<User> users = new ArrayList<>();
        users.add(user1);

        Collection<Reservation> reservationCollection = new ArrayList<>();
        Set<Reservation> reservations = Set.copyOf(reservationCollection);
        user1.setReservations(reservations);

        Mockito.lenient()
                .when(userRepository.findById(user1.getUserId()))
                .thenReturn(java.util.Optional.of(user1));

        Mockito.lenient()
                .when(userRepository.existsById(user1.getUserId()))
                .thenReturn(true);
        Mockito.lenient()
                .when(passwordEncoder.encode(anyString()))
                .thenReturn("testHashed");
    }

    @Test
    public void getUser_IdExists_UserIsCorrect()
    {
        long userId = 0L;
        UserDTO user = userService.getUser(userId);
        assertThat(user.getFirstname()).isEqualTo("testFirstname");
        assertThat(user.getLastname()).isEqualTo("testLastname");
        assertThat(user.getEmail()).isEqualTo("testMail");
        assertThat(user.getPhoneNumber()).isEqualTo("12345678");
        assertThat(user.isValid()).isEqualTo(true);
        assertThat(user.isAdmin()).isEqualTo(false);
    }

    @Test
    public void getUser_IdDoesNotExists_ReturnsNull()
    {
        long userId = -1L;
        UserDTO user = userService.getUser(userId);
        assertThat(user).isNull();
    }

    @Test
    public void createUser_userGetsAdded_ReturnsTrue()
    {
        UserAndPasswordDTO user = new UserAndPasswordDTO
                ("testFirstname", "testLastname", "testMail",
                        "123456789", "hash", true, true);
        User returnUser = new User("test","test", "mail", "98765432", "hash","salt", true, false);
        returnUser.setUserId(1);
        Mockito.lenient()
                .when(userRepository.save(any()))
                .thenReturn(returnUser);
        assertThat(userService.createUser(user)).isNotNull();
    }

    @Test
    public void editUser_updatesUser_ReturnsUpdatedUser() throws SerialException, SQLException
    {
        UserEditDTO userEditDTO = new UserEditDTO("Forename", "surname", "email", "87654321","newHash", "oldHash", true, true);
        User tempUser = new User();
        tempUser.setFirstName(userEditDTO.getFirstname());
        tempUser.setLastName(userEditDTO.getLastname());
        tempUser.setEmail(userEditDTO.getEmail());
        tempUser.setPhoneNumber(userEditDTO.getPhoneNumber());
        tempUser.setHash(userEditDTO.getOldPassword());
        tempUser.setValid(userEditDTO.isValid());
        tempUser.setAdmin(userEditDTO.isAdmin());

        Mockito.lenient()
                .when(userRepository.save(any()))
                .thenReturn(tempUser);

        UserDTO user = userService.editUser(0L, userEditDTO);

        assertThat(user).isNotNull();
        assertThat(user.getFirstname()).isEqualTo(tempUser.getFirstName());
        assertThat(user.getLastname()).isEqualTo(tempUser.getLastName());
        assertThat(user.getEmail()).isEqualTo(tempUser.getEmail());
        assertThat(user.getPhoneNumber()).isEqualTo(tempUser.getPhoneNumber());
        assertThat(user.isValid()).isEqualTo(tempUser.isValid());
        assertThat(user.isAdmin()).isEqualTo(tempUser.isAdmin());
    }

    @Test
    public void deleteUser_UserExists_ReturnsTrue()
    {
        Mockito.lenient()
                .when(userRepository.existsById(any(Long.class)))
                .thenReturn(false);

        assertThat(userService.deleteUser(0L)).isTrue();
    }

    @Test
    public void deleteUser_UserDoesNotExists_ReturnsFalse()
    {
        Mockito.lenient()
                .when(userRepository.existsById(any(Long.class)))
                .thenReturn(true);
        assertThat(userService.deleteUser(0l)).isFalse();
    }

    @Test
    public void getUserReservations_UserHasNoReservation_EmptySet()
    {
        List<Reservation> reservations = userService.findReservationMadeByUser(0L);
        assertThat(reservations).isEmpty();
    }

    @Test
    public void getUserReservations_UserHasReservation_ListOfReservations()
    {
        long userId = 0L;
        userService.getUser(userId).getUserId();
        Set<Reservation> reservations = new HashSet<>();
        reservations.add(new Reservation(10, LocalDateTime.of(2021, 5, 19, 14, 0, 0),
                LocalDateTime.of(2021, 5, 19, 15, 30, 0), "Description",
                userRepository.findById(0L).get(), null, null, null, null));
        userRepository.findById(0L).get().setReservations(reservations);
        List<Reservation> reservationsFound = userService.findReservationMadeByUser(0L);
        assertThat(reservationsFound).isNotEmpty();
    }
}

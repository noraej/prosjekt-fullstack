package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Component.EmailComponent;
import idatt2105.hamsterGroup.fullstackProject.Configuration.JWT.JwtSigningKey;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserAndPasswordDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserEditDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserRegistrationCallbackDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Model.User;
import idatt2105.hamsterGroup.fullstackProject.Repository.ReservationRepository;
import idatt2105.hamsterGroup.fullstackProject.Repository.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Creates an endpoint for user
 */

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired(required = false)
    private EmailComponent emailSender;


    /**
     * Method to get a user given the user ID
     * @param userId - id of user
     * @return UserDTO
     */
    public UserDTO getUser(long userId) {
        LOGGER.info("getUser(long userId) called with userID: " + userId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserDTO(user.get());
        }
        return null;
    }

    /**
     * Creates User object from an UserWithPasswordDTO and stores it in database
     * @param user
     * @return UserDTO object with a JWT token
     */
    public UserRegistrationCallbackDTO createUser(UserAndPasswordDTO user)
    {
        LOGGER.info("createUser(UserPasswordDTO user) called with email " + user.getEmail());
        User createdUser = new User();
        createdUser.setFirstName(user.getFirstname());
        createdUser.setLastName(user.getLastname());
        createdUser.setEmail(user.getEmail());
        createdUser.setPhoneNumber(user.getPhoneNumber());
        createdUser.setAdmin(user.isAdmin());
        createdUser.setValid(user.isValid());
        createdUser.setRole(user.getRole());
        createdUser.setHash(passwordEncoder.encode(user.getPassword()));
        createdUser = userRepository.save(createdUser);
        LOGGER.info(user.toString() + " " + createdUser.toString());
        String token = createJWTToken(createdUser);
      //  emailSender.createdUserMail("hei"); //Sends email
        return new UserRegistrationCallbackDTO(token, createdUser.getUserId(), user);
    }

    /**
     * Edits a user
     * @param userId - id of user
     * @param userDTO - userDTO object
     * @return UserDTO object
     */
    public UserDTO editUser(long userId, UserEditDTO userDTO)
    {
        LOGGER.info("editUser(long userId, UserDTO userDTO) called with user ID " + userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(userDTO.getPhoneNumber() != null) user.setPhoneNumber(userDTO.getPhoneNumber());
            if(userDTO.getEmail() != null)user.setEmail(userDTO.getEmail());
            user.setAdmin(userDTO.isAdmin());
            user.setValid(userDTO.isValid());
            if(userDTO.getNewPassword() != null && userDTO.getOldPassword() != null) {
                user.setHash(passwordEncoder.encode(userDTO.getNewPassword()));
            }
            return new UserDTO(userRepository.save(user));
        }
        LOGGER.warn("Could not find user with user ID " + userId +
                " when calling editUser(long userId, UserDTO userDTO). Return null");
        return null;
    }

    /**
     * Deletes a user from the database, and checks if it exists after
     * @param userId - id of user
     * @return if it exists or not
     */
    public boolean deleteUser(long userId)
    {
        LOGGER.info("deleteUser(long userId) called with user ID " + userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.getReservations().stream().forEach(reservation ->
                    reservationService.deleteReservation(reservation.getReservationId()));
            userRepository.delete(user);
            return !userRepository.existsById(userId);
        }
        return false;
    }

    /**
     * Method to check if the email already exists in the database
     * @param email - user's email
     * @return true (email exists) or false (email does not exist)
     */
    public boolean doesEmailExist(String email){
        boolean existsEmail = userRepository.findUserByEmail(email).isPresent();
        if(existsEmail) LOGGER.info("Email: {} already exists", email);
        return existsEmail;
    }

    /**
     * Method to find user from id and and compares user's hashed password with old password
     * @param userId - id of user
     * @param oldPassword - user's old password
     * @return true (passwords are equal) or false (password are not equal)
     */
    public boolean isOldPasswordCorrect(long userId, String oldPassword){
        Optional<User> optionalUsers = userRepository.findById(userId);
        if(optionalUsers.isPresent()){
            return passwordEncoder.matches(oldPassword, optionalUsers.get().getHash());
        }
        return false;
    }

    /**
     * Finds all reservations a user has made
     * @param userId - id of user
     * @return List of activities
     */
    public List<Reservation> getUserReservations(long userId){
        LOGGER.info("findReservationMadeByUser(long userId) called with user ID " + userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            return new ArrayList<>(userOptional.get().getReservations());
        }
        return new ArrayList<>();
    }

    /**
     * Creates a JWT token for registration of user
     * @return JWT token
     */
    private String createJWTToken(User user){
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        return Jwts.builder().setSubject(user.getEmail()).claim("authorities", grantedAuthorities)
                .claim("userId", user.getUserId())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(Keys.hmacShaKeyFor(JwtSigningKey.getInstance())).compact();
    }

}

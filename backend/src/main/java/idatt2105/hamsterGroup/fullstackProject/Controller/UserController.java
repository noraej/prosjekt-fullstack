package idatt2105.hamsterGroup.fullstackProject.Controller;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserAndPasswordDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserEditDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserRegistrationCallbackDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") long userId) {
        UserDTO returnUser = userService.getUser(userId);
        if (returnUser == null)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRegistrationCallbackDTO> createUser(@RequestBody UserAndPasswordDTO user) {
        if(userService.doesEmailExist(user.getEmail())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        UserRegistrationCallbackDTO createdUser = userService.createUser(user);
        if (createdUser != null)
        {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/{user_id}")
    @PreAuthorize("#userId == principal.userId or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> editUser(@PathVariable("user_id") long userId, @RequestBody UserEditDTO userEditDTO) {
        if(userEditDTO.getOldPassword() != null && userEditDTO.getNewPassword() != null
                && !userService.isOldPasswordCorrect(userId, userEditDTO.getOldPassword())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        UserDTO returnUser = userService.editUser(userId, userEditDTO);
        if (returnUser == null)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    @PreAuthorize("#userId == principal.userId or hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") long userId) {
        if (userService.deleteUser(userId))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{user_id}/reservations")
    @PreAuthorize("#userId == principal.userId or hasRole('ADMIN')")
    public ResponseEntity<Set<Reservation>> getUserReservations(@PathVariable("user_id") long userId) {
        Set<Reservation> reservations = (Set<Reservation>) userService.getUserReservations(userId);
        if (reservations == null)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}

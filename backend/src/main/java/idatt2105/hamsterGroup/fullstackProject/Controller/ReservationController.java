package idatt2105.hamsterGroup.fullstackProject.Controller;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation.ReservationDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.Reservation.ReservationRegistrationDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.User.UserDTO;
import idatt2105.hamsterGroup.fullstackProject.Service.ReservationService;
import idatt2105.hamsterGroup.fullstackProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Controller for the reservations, to connect from frontend
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @GetMapping("/{reservation_id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("reservation_id") long reservationId) {
        ReservationDTO returnReservation = reservationService.getReservation(reservationId);
        if (returnReservation == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnReservation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> reservations = reservationService.getReservations();
        if (reservations == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/alternatives")
    public ResponseEntity<List<ReservationDTO>> getReservationsWithFilter(@RequestBody FilterSortDTO filter){
        return new ResponseEntity<>(reservationService.getReservationsWithFilterAndSort(filter),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationRegistrationDTO reservation) {
        ReservationDTO returnReservation = reservationService.createReservation(reservation);
        if (returnReservation != null) {
            return new ResponseEntity<>(returnReservation, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{reservation_id}")
    @PreAuthorize("@reservationService.checkIfMakerOfReservation(#reservationId, principal.userId)")
    public ResponseEntity<ReservationDTO> editReservation(@PathVariable("reservation_id") long reservationId,
                                                          @RequestBody ReservationRegistrationDTO reservationRegistrationDTO) {
        ReservationDTO returnReservation = reservationService.editReservation(reservationId, reservationRegistrationDTO);

        if (returnReservation == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnReservation,HttpStatus.OK);
    }

    @DeleteMapping("/{reservation_id}")
    @PreAuthorize("@reservationService.checkIfMakerOfReservation(#reservationId, principal.userId)")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservation_id") long reservationId) {
        if (reservationService.deleteReservation(reservationId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{reservation_id}/user/{user_id}")
    public ResponseEntity<Boolean> isMakerOfReservation(@PathVariable("reservation_id") long reservationId,
                                                        @PathVariable("user_id") long userId) {
        ReservationDTO reservationDTO = reservationService.getReservation(reservationId);
        UserDTO userDTO = userService.getUser(userId);

        if(reservationDTO == null || userDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (reservationDTO.getUserId() == userDTO.getUserId()) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}

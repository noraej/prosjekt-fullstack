package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Component.EmailComponent;
import idatt2105.hamsterGroup.fullstackProject.Enum.UserRole;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.ReservationDTOs.ReservationDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.ReservationDTOs.ReservationRegistrationDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Reservation;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;
import idatt2105.hamsterGroup.fullstackProject.Model.User;
import idatt2105.hamsterGroup.fullstackProject.Model.UserSecurityDetails;
import idatt2105.hamsterGroup.fullstackProject.Repository.ReservationRepository;
import idatt2105.hamsterGroup.fullstackProject.Repository.SectionRepository;
import idatt2105.hamsterGroup.fullstackProject.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private EmailComponent emailSender;

    /**
     * Method to return reservation given reservationID from database
     * @param reservationId - id of reservation
     * @return reservationDTO-object
     */
    public ReservationDTO getReservation(long reservationId) {
        LOGGER.info("getReservation(long reservationId) was called with reservationID: " + reservationId);
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        return reservation.map(ReservationDTO::new).orElse(null);
    }

    /**
     * Method to return all reservations from database
     * @return List of reservations
     */
    public List<ReservationDTO> getReservations() {
        LOGGER.info("getReservations() was called");
        return reservationRepository.findAllReservationsFromNow().stream().
                map(ReservationDTO::new).collect(Collectors.toList());
    }

    /**
     * Method to filter and sort reservation list
     * @param filter - to filter out reservations
     * @return List of reservationDTOs
     */
    public List<ReservationDTO> getReservationsWithFilterAndSort(FilterSortDTO filter) {
        LOGGER.info("getReservationsWithFilter(FilterDTO filter) was called with filter a filter");
        List<Reservation> reservationList = filterAndSort(filter);
        return reservationList.stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    /**
     * Finds the user who created the reservation,
     * then saves the reservation object in the database
     * @param reservation - reservation object
     * @return ReservationDTO object
     */
    public ReservationDTO createReservation(ReservationRegistrationDTO reservation)
    {
        LOGGER.info("createReservation(Reservation reservation) on new reservation");
        UserSecurityDetails creatorUser = (UserSecurityDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LOGGER.debug("UserId: " + creatorUser.getUserId());

        Optional<User> optionalUser = userRepository.findById(creatorUser.getUserId());
        Optional<Section> optionalSection = sectionRepository.findById(reservation.getSectionId());
        if(optionalUser.isEmpty() || optionalSection.isEmpty()) {
            return null;
        }
        Reservation createdReservation = new Reservation(reservation, optionalSection.get(), optionalUser.get());
        createdReservation = reservationRepository.save(createdReservation);
        return new ReservationDTO(createdReservation);
    }

    /**
     * Finds the reservation based on id and changes all info based reservationRegistrationDTO
     * @param reservationId - id of reservation
     * @param reservationRegistrationDTO - reservationRegistrationDTO object
     * @return reservationDTO
     */
    public ReservationDTO editReservation(long reservationId, ReservationRegistrationDTO reservationRegistrationDTO)
    {
        LOGGER.info("editReservation(long reservationId, Reservation reservation) called with reservation ID: "
                    + reservationId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservation.setDescription(reservationRegistrationDTO.getDescription());
            reservation.setStartTime(reservationRegistrationDTO.getStartTime());
            reservation.setEndTime(reservationRegistrationDTO.getEndTime());
            reservation.calculateDuration();
            reservation.setNumberOfUsers(reservationRegistrationDTO.getNumberOfUsers());
          //  if (reservationRegistrationDTO.getSection() != null) {
            reservation.setSection(optionalReservation.get().getSection());
           /* } else {
                reservation.setSections(reservationRegistrationDTO.getRoom().getSections());
            }*/
            return new ReservationDTO(reservationRepository.save(reservation));
        }
        return null;
    }

    /**
     * Finds reservation based on id, removes it, sends cancellation emails
     * to all participants, and deletes the reservation from the database
     * @param reservationId - id of reservation
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteReservation(long reservationId)
    {
        LOGGER.info("deleteReservation(long reservationId) called with reservation ID " + reservationId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservationRepository.save(reservation);
            reservationRepository.deleteById(reservationId);
           // emailSender. tbd
            return !reservationRepository.existsById(reservationId);
        }
        return false;
    }

    /**
     * Checks if a user is the maker of the reservation
     * @param reservationId - id of reservation
     * @param userId - id of user
     * @return true (is maker) or false (is not maker)
     */
    public boolean checkIfMakerOfReservationOrAdmin(long reservationId, long userId){
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        boolean admin = userRepository.findById(userId).get().getRole().equals(UserRole.ROLE_ADMIN.name());
        return optionalReservation.filter(reservation -> reservation.getUser().getUserId() == userId).isPresent() || admin;
    }

    /**
     * Method to filter and sort reservations
     * @param filter - filter to use to filter out objects
     * @return List of reservations
     */
    private List<Reservation> filterAndSort(FilterSortDTO filter) {
        if (filter.getStartTime() != null && filter.getEndTime() != null && filter.getMinNumberOfSeats() != -1
                && filter.getBuildingId() <= 0) {
            return reservationRepository.sortReservationsWithFilters(filter.getStartTime(), filter.getEndTime(),
                    filter.getMinNumberOfSeats(), filter.getBuildingId());
        }
        return reservationRepository.findAll();
    }
}

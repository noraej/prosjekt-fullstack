package idatt2105.hamsterGroup.fullstackProject.Service;

import idatt2105.hamsterGroup.fullstackProject.Model.DTO.FilterSortDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.RoomDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.DTO.SectionDTO;
import idatt2105.hamsterGroup.fullstackProject.Model.Room;
import idatt2105.hamsterGroup.fullstackProject.Model.Section;
import idatt2105.hamsterGroup.fullstackProject.Repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Creates an endpoint for section
 */

@Service
public class SectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionService.class);

    @Autowired
    private SectionRepository sectionRepository;

    /**
     * Method to get a section given the section ID
     * @param sectionId - id of section
     * @return SectionDTO
     */
    public SectionDTO getSection(long sectionId) {
        LOGGER.info("getSection(long sectionId) called with section ID: " + sectionId);
        Optional<Section> section = sectionRepository.findById(sectionId);
        if (section.isPresent()) {
            return new SectionDTO(section.get());
        }
        return null;
    }

    /**
     * Method to return all sections from database
     * @return List of sections
     */
    public List<SectionDTO> getSections() {
        LOGGER.info("getRooms() was called");
        return sectionRepository.getSections().stream().
                map(SectionDTO::new).collect(Collectors.toList());
    }

    /**
     * Method to filter section list by room
     * @param filter - to filter out rooms
     * @return List of room DTOs
     */
    public List<SectionDTO> getSectionsByRoom(FilterSortDTO filter) {
        LOGGER.info("getReservationsWithFilter(FilterDTO filter) was called with filter a filter");
        List<Section> sections = filterByRoom(filter);
        return sections.stream().map(SectionDTO::new).collect(Collectors.toList());
    }

    /**
     * Method to filter sections based on room
     * @param filter - filter to use to filter out objects
     * @return List of sections
     */
    private List<Section> filterByRoom(FilterSortDTO filter) {
        if (filter.getBuildingId() <= 0) {
            return sectionRepository.findSectionByRoom(filter.getRoomId());
        }
        return sectionRepository.findAll();
    }
}
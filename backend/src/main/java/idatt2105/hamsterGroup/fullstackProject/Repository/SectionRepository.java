package idatt2105.hamsterGroup.fullstackProject.Repository;

import idatt2105.hamsterGroup.fullstackProject.Model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    /**
     * Query that return a list of all sections
     */
    @Query(value = "SELECT * FROM section)", nativeQuery = true)
    public List<Section> getSections();

    /**
     * Query that return a list of sections with when given room that is not occupied
     */
    @Query(value = "SELECT * FROM section, reservation WHERE section.room_id=?1 AND section.section_id <> reservation.section_id", nativeQuery = true)
    public List<Section> findAvailableSectionByRoom(long roomId);

    /**
     * Query that return a list of sections sorted when choosing room
     */
    @Query(value = "SELECT * FROM section WHERE section.room_id=?1", nativeQuery = true)
    public List<Section> findSectionByRoom(long roomId);
}

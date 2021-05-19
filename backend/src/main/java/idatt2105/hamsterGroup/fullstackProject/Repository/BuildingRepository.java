package idatt2105.hamsterGroup.fullstackProject.Repository;

import idatt2105.hamsterGroup.fullstackProject.Model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    //Optional<Room> findRoomById(long roomId);

    /**
     * Query that return a list of all buildings
     */
    @Query(value = "SELECT * FROM building)", nativeQuery = true)
    public List<Building> getBuildings();
}

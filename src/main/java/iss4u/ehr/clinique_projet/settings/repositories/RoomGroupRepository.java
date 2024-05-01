package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.RoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomGroupRepository extends JpaRepository<RoomGroup, Long> {
    @Query("SELECT rg FROM RoomGroup rg WHERE rg.RoomGroup_Nm = ?1")
    RoomGroup findRoomGroupNm(String name);

    @Query("SELECT rg FROM RoomGroup rg WHERE rg.RoomGroup_Ky = ?1")
    RoomGroup findRoomGroupByRoomGroup_Ky(long RoomGroup_Ky);
}

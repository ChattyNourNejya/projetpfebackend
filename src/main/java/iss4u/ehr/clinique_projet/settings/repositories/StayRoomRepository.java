package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StayRoomRepository extends JpaRepository<StayRoom, Long> {

    @Query("SELECT sr FROM StayRoom sr WHERE sr.stayRoomNm = :stayRoomName")
    StayRoom findByName(@Param("stayRoomName") String stayRoomName);

    @Query("SELECT sr FROM StayRoom sr WHERE sr.leService.service_ky = :serviceKy")
    List<StayRoom> findByService(@Param("serviceKy") long serviceKy);
}

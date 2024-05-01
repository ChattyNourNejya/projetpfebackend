package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.Room_Nm = ?1 AND r.Room_PrntKy = ?2")
    Room findByRoom_NmAndRoom_PrntKy(String libelle, String type);
    @Query("SELECT r FROM Room r WHERE r.Room_Ky = :Room_Ky")
    Room getRoomById(long Room_Ky);
}



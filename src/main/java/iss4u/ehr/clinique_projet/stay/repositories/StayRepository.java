package iss4u.ehr.clinique_projet.stay.repositories;

import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.settings.entities.Site;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Stay s WHERE s.stayKy = :stayKy")
    void deleteStayById(@Param("stayKy") int stayKy);


    @Query("SELECT s FROM Stay s WHERE s.stayPrntPatient = :patient")
    List<Stay> findByPatient(Patient patient);

    @Query("SELECT s FROM Stay s WHERE s.stayKy = ?1")
    Stay findBystayKy(String stayKy);
}

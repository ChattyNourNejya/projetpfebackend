package iss4u.ehr.clinique_projet.patient.repositories;

import iss4u.ehr.clinique_projet.patient.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {
    @Modifying
    @Query("DELETE FROM Phone p WHERE p.phoneKy = :phoneKy AND p.patient.patientKy = :patientKy")
    void deletePhoneByPatientKy(@Param("phoneKy") int phoneKy, @Param("patientKy") int patientKy);

    @Query("SELECT p FROM Phone p WHERE p.patient.patientKy = :patientKy AND p.phoneKy = :phoneKy")
    Phone findByPhonePatientId(@Param("phoneKy") int phoneKy, @Param("patientKy") int patientKy);
}


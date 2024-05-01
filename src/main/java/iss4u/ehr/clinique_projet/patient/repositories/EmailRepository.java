package iss4u.ehr.clinique_projet.patient.repositories;


import iss4u.ehr.clinique_projet.patient.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,Integer> {
    @Modifying
    @Query("DELETE FROM Email e WHERE e.emailKy = :emailId AND e.patient.patientKy = :patientKy")
    int deleteEmailByPatientKy(@Param("emailId") int emailId, @Param("patientKy") int patientKy);

    @Query("SELECT e FROM Email e WHERE e.patient.patientKy = :patientKy AND e.emailKy = :emailId")
    Email findByEmailPatientId(@Param("emailId") int emailId, @Param("patientKy") int patientKy);


}

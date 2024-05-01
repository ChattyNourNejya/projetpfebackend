package iss4u.ehr.clinique_projet.patient.repositories;

import iss4u.ehr.clinique_projet.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    @Query("SELECT p FROM Patient p WHERE p.patientFirstName = :firstName AND p.patientLastName = :lastName AND p.patientBirthDate = :dob")
    Optional<Patient> findByNameDOB(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dob") LocalDate dob);

    @Query("SELECT p.patientKy FROM Patient p WHERE p.patientFirstName = :firstName AND p.patientLastName = :lastName AND p.patientBirthDate = :dob")
    Long findIdByNameDOB(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dob") LocalDate dob);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.addresses LEFT JOIN  p.emails LEFT JOIN  p.phones WHERE p.patientKy = :patientKy")
    Patient findByPatientKy(@Param("patientKy") int patientKy);
    @Query("SELECT p FROM Patient p LEFT JOIN  p.addresses LEFT JOIN  p.emails LEFT JOIN  p.phones")
    List<Patient> findAllWithAddressesEmailsPhones();
}
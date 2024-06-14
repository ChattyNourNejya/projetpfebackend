package iss4u.ehr.clinique_projet.appointment.repositories;


import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedVisitSchdldRepository extends JpaRepository<MedVisitSchdld, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM MedVisitSchdld m WHERE m.MedVisitSchdld_Ky = :medVisitSchdldKy")
    void deleteMedVisitSchdldById(@Param("medVisitSchdldKy") int medVisitSchdldKy);

    @Query("SELECT m FROM MedVisitSchdld m WHERE m.MedVisitSchdld_PatientKy = :patient")
    List<MedVisitSchdld> findByPatient(Patient patient);

    @Query("SELECT m FROM MedVisitSchdld m WHERE m.MedVisitSchdld_Ky = :medVisitSchdldKy")
    MedVisitSchdld findByMedVisitSchdldKy(@Param("medVisitSchdldKy") int medVisitSchdldKy);
}

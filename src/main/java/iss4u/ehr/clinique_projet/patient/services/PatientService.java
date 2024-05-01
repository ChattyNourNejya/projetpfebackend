package iss4u.ehr.clinique_projet.patient.services;


import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.entities.PatientRequest;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PatientService {
    Patient createPatient(Patient patient);

    List<Patient> retrievePatients();

    Optional<Patient> getPatientByKy(int patientKy);

    Optional<Patient> getPatientByNameDOB(String firstName, String lastName, LocalDate dob);
    Long getPatientIdByNameDOB(String firstName, String lastName, LocalDate dob);

    Patient update(int patientKy, Patient updatedPatient);
    Patient update2(int patientKy, PatientRequest updatedPatient);

    void delete(int patientKy);
    void deletePatient(int patientKy);

    Patient disable(int patientKy);
    Patient enable(int patientKy);
    List<Stay> getStaysByPatient(int patientKy);
    Stay addStayToPatient(int patientKy, Stay stay);

    Patient getPatientById(Long Id);
}
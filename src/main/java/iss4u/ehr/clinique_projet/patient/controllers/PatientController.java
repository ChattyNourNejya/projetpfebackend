package iss4u.ehr.clinique_projet.patient.controllers;


import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.entities.PatientRequest;
import iss4u.ehr.clinique_projet.patient.responses.Message;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/createPatient")
    public Message createPatient(@RequestBody PatientRequest request) {
        Patient patient = request.getPatient();
        if (patient != null) {
            Patient createdPatient = patientService.createPatient(patient);
            if (createdPatient != null) {
                return new Message("Patient Created");
            } else {
                return new Message("Patient Not Created");
            }
        } else {
            return new Message("Patient is null");
        }
    }

    @GetMapping("/getPatient")
    public ResponseEntity<List<Patient>> retrievePatients() {
        List<Patient> patients = patientService.retrievePatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("getPatient/{patientKy}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientKy) {
        Optional<Patient> patient = patientService.getPatientByKy(patientKy);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("getPatient/{firstName}/{lastName}/{dob}")
    public ResponseEntity<Patient> getPatientByNameDOB(@PathVariable String firstName, @PathVariable String lastName, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob) {
        Optional<Patient> patient = patientService.getPatientByNameDOB(firstName, lastName, dob);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("disablePatient/{patientKy}")
    public Message disable(@PathVariable int patientKy) {
        Patient disabled = patientService.disable(patientKy);

        if (disabled != null) {
            return new Message("Patient disabled");
        } else {
            return new Message("Patient Not disabled");
        }
    }

    @PutMapping("enablePatient/{patientKy}")
    public Message enable(@PathVariable int patientKy) {
        Patient enabled = patientService.enable(patientKy);

        if (enabled != null) {
            return new Message("Patient enabled");
        } else {
            return new Message("Patient Not enabled");
        }
    }
    @PostMapping("/{patientKy}/addStay")
    public ResponseEntity<Stay> addStayPatient(@PathVariable int patientKy, @RequestBody Stay newStay) {
        try {
            Stay aStayAdded = patientService.addStayToPatient(patientKy, newStay);
            return new ResponseEntity<>(aStayAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while adding the stay to the patient", e);
        }
    }

    @DeleteMapping("deletePatient/{patientKy}")
    public Message deletePatient(@PathVariable int patientKy) {
        Optional<Patient> patient = patientService.getPatientByKy(patientKy);
        if (patient.isPresent()) {
            patientService.deletePatient(patientKy);
            if (patientService.getPatientByKy(patientKy).isEmpty()) {
                return new Message("Patient Deleted");
            } else {
                return new Message("Failed to delete patient");
            }
        } else {
            return new Message("Patient Not Found");
        }
    }
}
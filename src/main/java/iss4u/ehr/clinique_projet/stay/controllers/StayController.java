package iss4u.ehr.clinique_projet.stay.controllers;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.insurance.services.InsuranceService;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.responses.Message;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import iss4u.ehr.clinique_projet.settings.services.ServiceService;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import iss4u.ehr.clinique_projet.stay.services.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stays")
public class StayController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private StayService stayService;

    @Autowired
    private ServiceService serviceService;


    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private Insurance insurance;

    @Autowired
    private Servicee service;

    @PostMapping("/{iStayId}/addService")
    public ResponseEntity<Servicee> addServiceToStay(@PathVariable int iStayId, @RequestBody Servicee newService) {
        try {
            Servicee aServiceAdded = stayService.addServiceToStay(iStayId, newService);
            return new ResponseEntity<>(aServiceAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un service dans le stay", e);
        }
    }
    @PostMapping("/saveStay/{patientKy}")
    public ResponseEntity<String> saveStay(@PathVariable int patientKy, @RequestBody Stay stay) {
        System.out.println("Received request to save a new stay");

        Optional<Patient> patientOptional = patientService.getPatientByKy(patientKy);
        if (patientOptional.isEmpty()) {
            System.out.println("Patient not found");
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }

        Patient patient = patientOptional.get();
        stay.setStayPrntPatient(patient);

        List<Insurance> insurances = new ArrayList<>();
        for (Insurance insurance : stay.getInsurances()) {
            List<Insurance> foundInsurances = insuranceService.getInsuranceByCompanyName(insurance.getInsNm());
            if (!foundInsurances.isEmpty()) {
                insurances.addAll(foundInsurances);
            } else {
                System.out.println("Insurance not found: " + insurance.getInsNm());
                return new ResponseEntity<>("Insurance not found: " + insurance.getInsNm(), HttpStatus.NOT_FOUND);
            }
        }
        stay.setInsurances(insurances);

        List<Servicee> aServicesAdded = new ArrayList<>();
        for (Servicee service : stay.getStayPertinentService()) {

            List <Servicee>foundServices = (List<Servicee>) serviceService.getServiceByName(service.getService_Nm());
            if (!foundServices.isEmpty()) {
                aServicesAdded.addAll(foundServices);
                service.setStay(stay);
            } else {
                System.out.println("Service not found: " + service.getService_Nm());
                return new ResponseEntity<>("Service not found: " + service.getService_Nm(), HttpStatus.NOT_FOUND);
            }
        }

        System.out.println("Services set to stay: " + aServicesAdded);

        Stay savedStay = stayService.saveStay(stay);
        if (savedStay != null) {
            System.out.println("Stay saved successfully");
            return new ResponseEntity<>("Stay Saved", HttpStatus.CREATED);
        } else {
            System.out.println("Stay not saved");
            return new ResponseEntity<>("Stay Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping
    public ResponseEntity<List<Stay>> getAllStays() {
        List<Stay> stays = stayService.getAllStays();
        return new ResponseEntity<>(stays, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientKy}")
    public ResponseEntity<List<Stay>> getStayByPatient(@PathVariable int patientKy) {
        List<Stay> stays = stayService.getStayByPatient(patientKy);
        return new ResponseEntity<>(stays, HttpStatus.OK);
    }
    @DeleteMapping("/{stayId}")
    public ResponseEntity<Void> deleteStayById(@PathVariable int stayId) {
        stayService.deleteStayById(stayId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
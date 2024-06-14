package iss4u.ehr.clinique_projet.stay.controllers;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.insurance.services.InsuranceService;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import iss4u.ehr.clinique_projet.settings.services.LeServiceService;
import iss4u.ehr.clinique_projet.settings.services.StayRoomService;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import iss4u.ehr.clinique_projet.stay.services.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin()

@RequestMapping("/stays")
public class StayController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private StayService stayService;

    @Autowired
    private LeServiceService LeserviceService;


    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private  StayRoomService stayRoomService;

    @Autowired
    private Insurance insurance;

    @Autowired
    private LeService Leservice;

    @PostMapping("/{iStayId}/addService")
    public ResponseEntity<LeService> addServiceToStay(@PathVariable int iStayId, @RequestBody LeService newService) {
        try {
            LeService aServiceAdded = stayService.addServiceToStay(iStayId, newService);
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
        System.out.println("Insurances set to stay: " + insurances);

        List<LeService> services = new ArrayList<>();
        for (LeService leService : stay.getStayPertinentService()) {
            System.out.println("services: " + leService);
            List<LeService> foundServices = LeserviceService.getServiceByName(leService.getService_Nm());
            if (!foundServices.isEmpty()) {
                for (LeService foundService : foundServices) {
                    services.add(foundService);
                    foundService.setStay(stay);
                    for (StayRoom stayRoom : foundServices.get(0).getStayRooms()) {
                        System.out.println("stayRooms: " + stayRoom.getStayRoom_Nm());
                        StayRoom foundStayRoom = stayRoomService.findStayRoomByName(stayRoom.getStayRoom_Nm());
                        System.out.println("le found service"+foundStayRoom);

                        if (foundStayRoom.getStayRoom_Nm().equals(stayRoom.getStayRoom_Nm())) {
                            leService.addStayRoom(foundStayRoom);
                            foundStayRoom.setLeService(leService);
                            System.out.println("foundStayRoom: " + foundStayRoom);
                        } else {
                            System.out.println("Stay room not found: " + stayRoom.getStayRoom_Nm());
                            return new ResponseEntity<>("Stay room not found: " + stayRoom.getStayRoom_Nm(), HttpStatus.NOT_FOUND);
                        }
                    }
                }
            }  else {
                System.out.println("Service not found: " + leService.getService_Nm());
                return new ResponseEntity<>("Service not found: " + leService.getService_Nm(), HttpStatus.NOT_FOUND);
            }
        }
        stay.setStayPertinentService(services);
        System.out.println("Services set to stay: " + services);

        Stay savedStay = stayService.saveStay(stay);
        if (savedStay != null) {
            System.out.println("Stay saved successfully");
            return new ResponseEntity<>("Stay Saved", HttpStatus.OK);
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
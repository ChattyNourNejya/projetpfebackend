package iss4u.ehr.clinique_projet.appointment.controllers;

import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import iss4u.ehr.clinique_projet.appointment.services.MedVisiTScdldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medvisitschdlds")
public class MedVisitSchdldController {

    @Autowired
    private MedVisiTScdldService medVisiTScdldService;

    @GetMapping
    public List<MedVisitSchdld> getAllMedVisitSchdlds() {
        return medVisiTScdldService.getAllMedVisitSchdlds();
    }

    @GetMapping("/")
    public ResponseEntity<MedVisitSchdld> getMedVisitSchdldById(@PathVariable int id) {
        MedVisitSchdld medVisitSchdld = medVisiTScdldService.getMedVisitSchdldById(id);
        return medVisitSchdld != null ? ResponseEntity.ok(medVisitSchdld) : ResponseEntity.notFound().build();
    }

    @GetMapping("/patient/{patientKy}")
    public List<MedVisitSchdld> getMedVisitSchdldByPatient(@PathVariable int patientKy) {
        return medVisiTScdldService.getMedVisitSchdldByPatient(patientKy);
    }

    @PostMapping("/add")
    public MedVisitSchdld createMedVisitSchdld(@RequestBody MedVisitSchdld medVisitSchdld) {
        System.out.println("herree");
        return medVisiTScdldService.saveMedVisitSchdld(medVisitSchdld);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedVisitSchdld> updateMedVisitSchdld(@PathVariable int id, @RequestBody MedVisitSchdld medVisitSchdldDetails) {
        MedVisitSchdld updatedMedVisitSchdld = medVisiTScdldService.getMedVisitSchdldById(id);
        if (updatedMedVisitSchdld == null) {
            return ResponseEntity.notFound().build();
        }
        medVisitSchdldDetails.setMedVisitSchdld_Ky(id); // Ensure the ID remains unchanged
        updatedMedVisitSchdld = medVisiTScdldService.saveMedVisitSchdld(medVisitSchdldDetails);
        return ResponseEntity.ok(updatedMedVisitSchdld);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedVisitSchdld(@PathVariable int id) {
        medVisiTScdldService.deleteMedVisitSchdldById(id);
        return ResponseEntity.noContent().build();
    }
}

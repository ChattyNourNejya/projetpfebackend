package iss4u.ehr.clinique_projet.insurance.controllers;

import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.insurance.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/healthInsurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    public InsuranceController() {
    }
    @PostMapping("add")
    public ResponseEntity<?> createInsurance(@RequestBody Insurance insurance) {
        try {
            this.insuranceService.create(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body("L'assurance a été ajoutée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'assurance : " + e.getMessage());
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getAllInsurances() {
        List<Insurance> insurances = insuranceService.retrieveInsurances();

        if (insurances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune assurance disponible.");
        } else {
            return ResponseEntity.ok(insurances);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getInsuranceById(@PathVariable("id") Long insKy) {
        Optional<Insurance> insuranceOptional = this.insuranceService.getInsuranceByKy(insKy);

        if (insuranceOptional.isPresent()) {
            Insurance insurance = insuranceOptional.get();
            return ResponseEntity.ok(insurance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'assurance avec l'ID " + insKy + " est introuvable.");
        }
    }




    @PutMapping("update/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable("id") Long insKy, @RequestBody Insurance insurance) {
        Optional<Insurance> existingInsurance = insuranceService.getInsuranceByKy(insKy);
        if (existingInsurance.isPresent()) {
            insurance.setInsKy(insKy);
            this.insuranceService.update(insurance);
            return new ResponseEntity<>(insurance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get/{companyName}")
    public ResponseEntity<List<Insurance>> getInsuranceByCompanyName(@PathVariable("companyName") String companyName) {
        List<Insurance> insurances = insuranceService.getInsuranceByCompanyName(companyName);
        if (!insurances.isEmpty()) {
            return new ResponseEntity<>(insurances, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable("id") Long insKy) {
        Optional<Insurance> existingInsurance = this.insuranceService.getInsuranceByKy(insKy);
        if (existingInsurance.isPresent()) {
            this.insuranceService.delete(insKy);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Suppression réussie.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assurance non trouvée.");
        }
    }

}

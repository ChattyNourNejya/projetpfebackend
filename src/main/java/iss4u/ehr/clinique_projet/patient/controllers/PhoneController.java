package iss4u.ehr.clinique_projet.patient.controllers;



import iss4u.ehr.clinique_projet.patient.responses.Message;
import iss4u.ehr.patient.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;
    @DeleteMapping("deletePhone/{phoneId}/{patientKy}")
    public Message deletePhone(@PathVariable int phoneId, @PathVariable int patientKy) {
        phoneService.deletePhone(phoneId, patientKy);
        return new Message("deleted");
    }

}

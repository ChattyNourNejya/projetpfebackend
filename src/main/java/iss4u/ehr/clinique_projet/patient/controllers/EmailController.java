package iss4u.ehr.clinique_projet.patient.controllers;



import iss4u.ehr.clinique_projet.patient.responses.Message;
import iss4u.ehr.clinique_projet.patient.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @DeleteMapping("deleteEmail/{emailKy}/{patientKy}")
    public Message deletePatient(@PathVariable int emailKy, @PathVariable int patientKy) {
            emailService.deleteEmail(emailKy,patientKy);
           return new Message("deleted");
}
}

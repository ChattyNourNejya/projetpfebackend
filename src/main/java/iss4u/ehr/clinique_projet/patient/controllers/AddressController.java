package iss4u.ehr.clinique_projet.patient.controllers;



import iss4u.ehr.clinique_projet.patient.responses.Message;
import iss4u.ehr.clinique_projet.patient.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @DeleteMapping("deleteAddress/{addressKy}/{patientKy}")
    public Message deleteAddress(@PathVariable int addressKy, @PathVariable int patientKy) {
        addressService.deleteAddress(addressKy, patientKy);
        return new Message("deleted");
    }


}

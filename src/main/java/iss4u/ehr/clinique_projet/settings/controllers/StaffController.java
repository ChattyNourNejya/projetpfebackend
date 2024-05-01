package iss4u.ehr.clinique_projet.settings.controllers;


import iss4u.ehr.clinique_projet.exception.UserNotFoundException;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import iss4u.ehr.clinique_projet.settings.services.StaffManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Staff")
@CrossOrigin(origins=("*"))
public class StaffController {
    
    private StaffManagement service;
    @Autowired
    public StaffController(StaffManagement iservice) {
        super();
        this.service = iservice;
    }
    
    @PostMapping("/newStaff")
    public Staff newStaff(@RequestBody Staff astaff) {
        return service.addStaff(astaff);
    }
    
    @GetMapping
    public ResponseEntity<List<Staff>> all() {
        try {
            List<Staff> staff = service.findallStaff();
            if (staff.isEmpty()) {
                throw new Exception("No content found");
            } else {
                return ResponseEntity.ok(staff);
            }
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }


    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int id) {
        Optional<Staff> staffOptional = service.getStaffById(id);
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("L'ID du Staffdoit être positif.");
            }
            return ResponseEntity.ok(staffOptional.orElseThrow(() -> new UserNotFoundException("Staff not found")));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


   /*@GetMapping("/byname/{staffName}") // Unique path to avoid conflicts
    public ResponseEntity<Staff> getStaffByName(@PathVariable String Staff_Nm) {
        Staff staff = service.findStaffByName(Staff_Nm);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/


    @PutMapping("/{Staff_Ky}")
    public Staff updateStaff(@RequestBody Staff astaff, @PathVariable int iStaff_Ky) {
        try {
            if (iStaff_Ky <= 0) {
                throw new IllegalArgumentException("L'ID du StaffGroup doit être positif.");
            }
            astaff.setStaff_Ky(iStaff_Ky);
        }catch (Exception e){
            e.printStackTrace();
        }
        return service.updateStaff(astaff);
    }
    
    @DeleteMapping("/{Staff_Ky}")
    void deleteStaff(@PathVariable int iStaff_Ky) {
        try{
        if (iStaff_Ky <= 0) {
            throw new IllegalArgumentException("L'ID du StaffGroup doit être positif.");
        }
    	service.deleteStaff(iStaff_Ky);}
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
	//L'administrateur sélectionne le personnel qu'il souhaite activé
    @PostMapping("/enableStaff")
    public Staff enableStaff(@PathVariable int iStaff_Ky ) {
        try {
            if (iStaff_Ky <= 0) {
                throw new IllegalArgumentException("L'ID du Staff doit être positif.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return service.enableStaff(iStaff_Ky);


    }
    
    //L'administrateur sélectionne le personnel qu'il souhaite désactiver.
    @PostMapping("/disableStaff")
    public Staff disableStaff(@PathVariable int iStaff_Ky ) {
        try {
            if (iStaff_Ky <= 0) {
                throw new IllegalArgumentException("L'ID du Staff doit être positif.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return service.disableStaff(iStaff_Ky);

    }
    


    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}

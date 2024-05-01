package iss4u.ehr.clinique_projet.settings.controllers;


import iss4u.ehr.clinique_projet.settings.entities.StaffGroup;
import iss4u.ehr.clinique_projet.settings.services.StaffGroupManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("staffGroup")
@CrossOrigin(origins=("*"))
public class StaffGroupController {
    
    private StaffGroupManagement service;
    @Autowired
    public StaffGroupController(StaffGroupManagement service) {
        super();
        this.service = service;
    }
    
    @PostMapping
    public StaffGroup newStaffGroup(@RequestBody StaffGroup astaffGroup) {
        return service.addStaffGroup(astaffGroup);
    }
    
    @GetMapping
    public ResponseEntity<List<StaffGroup>> all() {
        try {
            List<StaffGroup> astaffGroup = service.findallStaffGroup();
            return ResponseEntity.ok(astaffGroup);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.noContent().build();
        }
   }
    
    @PutMapping("/{StaffGroup_Ky}")
    public StaffGroup updateStaffGroup(@RequestBody StaffGroup astaffGroup, @PathVariable int iStaffGroup_Ky) {
        try {
            if (iStaffGroup_Ky <= 0) {
                throw new IllegalArgumentException("L'ID du staffGroup doit être positif.");
            }
            astaffGroup.setStaffGroup_Ky(iStaffGroup_Ky);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return service.updateStaffGroup(astaffGroup);

    }
    @DeleteMapping("/{StaffGroup_Ky}")
    void deleteStaffGroup(@PathVariable int iStaffGroup_Ky) {
        try {
            if (iStaffGroup_Ky <= 0) {
                throw new IllegalArgumentException("L'ID du StaffGroup doit être positif.");
            }
            service.deleteStaffGroup(iStaffGroup_Ky);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}

package iss4u.ehr.clinique_projet.settings.controllers;

import iss4u.ehr.clinique_projet.settings.entities.*;
import iss4u.ehr.clinique_projet.settings.services.LeServiceService;
import iss4u.ehr.clinique_projet.settings.services.ServicePlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Leservice")
public class LeServiceContoller {

    private final LeServiceService leServiceService;
    private final ServicePlannerService servicePlannerService;

    @Autowired
    public LeServiceContoller(LeServiceService leServiceService, ServicePlannerService servicePlannerService) {
        this.leServiceService = leServiceService;
        this.servicePlannerService = servicePlannerService;
    }

    @GetMapping()
    public List<LeService> getAllServices() {
        try {
            return leServiceService.findAllService();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des services.", e);
        }
    }

    @GetMapping("/name/{iServiceNm}")
    public ResponseEntity<LeService> findServiceByName(@PathVariable String iServiceNm) {
        try {
            LeService leService = leServiceService.findServiceByName(iServiceNm);
            return ResponseEntity.ok(leService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération par nom des services", e);
        }
    }

    @PostMapping("/{iServiceId}/addServiceZone")
    public ResponseEntity<ServiceZone> addServiceZoneToService(@PathVariable int iServiceId, @RequestBody ServiceZone newServiceZone) {
        try {
            ServiceZone addedServiceZone = servicePlannerService.addServiceZoneToService(iServiceId, newServiceZone);
            return new ResponseEntity<>(addedServiceZone, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un serviceZone dans un service", e);
        }
    }

    @PostMapping("/{iServiceId}/addStaff")
    public ResponseEntity<Staff> addStaffToService(@PathVariable int iServiceId, @RequestBody Staff newStaff) {
        try {
            Staff addedStaff = servicePlannerService.addStaffToService(iServiceId, newStaff);
            return new ResponseEntity<>(addedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un Staff dans un service", e);
        }
    }

    @PostMapping("/{iServiceId}/addStayRoom")
    public ResponseEntity<StayRoom> addStayRoomToService(@PathVariable int iServiceId, @RequestBody StayRoom newStayRoom) {
        try {
            StayRoom addedStayRoom = servicePlannerService.addStayRoomToService(iServiceId, newStayRoom);
            return new ResponseEntity<>(addedStayRoom, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un StayRoom dans un service", e);
        }
    }

    @PostMapping()
    public LeService newService(@RequestBody LeService service) {
        try {
            return leServiceService.addService(service);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout du service", e);
        }
    }



    @PutMapping("/{iService_ky}")
    public LeService updateService(@RequestBody LeService service, @PathVariable int iService_ky) {
        try {
            service.setService_ky(iService_ky);
            return leServiceService.updateService(service);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la modification du service", e);
        }
    }

    @DeleteMapping("/{iserviceId}")
    public void deleteService(@PathVariable int iserviceId) {
        try {
            leServiceService.deleteService(iserviceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la suppression du service", e);
        }
    }
}

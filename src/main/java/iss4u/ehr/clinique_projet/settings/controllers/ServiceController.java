package iss4u.ehr.clinique_projet.settings.controllers;


import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import iss4u.ehr.clinique_projet.settings.entities.StaffGroup;
import iss4u.ehr.clinique_projet.settings.services.ServicePlannerService;
import iss4u.ehr.clinique_projet.settings.services.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






@RestController
@RequestMapping("service")
public class ServiceController {

	private SiteService siteService;

	@Autowired
	public ServiceController(SiteService iSiteService) {
		this.siteService = iSiteService;
	}
	@Autowired
	private ServicePlannerService servicesService;
	
	public ServiceController(ServicePlannerService iService) {
        this.servicesService = iService;
    }
	
	@GetMapping()
	public List<Servicee> getAllServices() {
		try {
			return servicesService.findAllService();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération des services.", e);
		}
	}
	
	@GetMapping("/name/{iServiceNm}")
	public ResponseEntity<Servicee> findServiceByName(@PathVariable String iServiceNm) {
		try {
			Servicee service = servicesService.findServiceByName(iServiceNm);
			return ResponseEntity.ok(service);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération par nom des services", e);		}
	}


	//routage pour ajouter un serviceZone dans un service
	@PostMapping("/{iServiceId}/addServiceZone")
	public ResponseEntity<ServiceZone> addServiceZoneToService(@PathVariable int iServiceId, @RequestBody ServiceZone newServiceZone) {
		try {
			ServiceZone aServiceZoneAdded = servicesService.addServiceZoneToService(iServiceId, newServiceZone);
			return new ResponseEntity<>(aServiceZoneAdded, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un serviceZone dans un service", e);
		}
	}

	@PostMapping("/{siteId}/addStaffGroup")
	public ResponseEntity<StaffGroup> addStaffGroupToService(@PathVariable int siteId, @RequestBody StaffGroup newStaffGroup){
		StaffGroup addedStaffGroup= servicesService.addStaffGroupToService(siteId,newStaffGroup);
		return new ResponseEntity<>(addedStaffGroup, HttpStatus.CREATED);
	}

	@PostMapping()
	 Servicee newService(@RequestBody Servicee Service) {
		try{
			return servicesService.addService(Service);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout du service", e);
		}
	}
	
	   @GetMapping("/{iService_ky}")
	    public ResponseEntity<Servicee> findServiceById(@PathVariable int iService_ky) {
	        try{
				Optional<Servicee> aServiceOptional = servicesService.findServiceById(iService_ky);
				if (aServiceOptional.isPresent()) {
					return ResponseEntity.ok(aServiceOptional.get());
				} else {
					return ResponseEntity.notFound().build();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Une erreur s'est produite lors de la récupération par id du service", e);
			}
	    }
	
	@PutMapping("/{iService_ky}")
	Servicee updateService(@RequestBody Servicee service,@PathVariable int iService_ky) {
		try{
			service.setService_ky(iService_ky);
			return servicesService.updateService(service);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la modification du service", e);
		}
	}
	
	@DeleteMapping("/{iService_ky}")
	void deleteService(@PathVariable int iService_ky) {
		try{
			servicesService.deleteService(iService_ky);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la suppression du service", e);
		}
	}
	 @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

	//routage pour récuperer les services d'un site
	@GetMapping("/service-by-site/{iSiteId}")
	public ResponseEntity<List<Servicee>> getServiceBySite(@PathVariable int iSiteId) {
		try{
			if (iSiteId <= 0) { throw new IllegalArgumentException("Site ID must be positive."); }
			List<Servicee> aServiceList = siteService.getServiceBySite(iSiteId);

			return new ResponseEntity<>(aServiceList, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de le récupération des service dans un site", e);
		}
	}

	
}


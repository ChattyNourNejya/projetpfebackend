package iss4u.ehr.clinique_projet.settings.controllers;

import java.util.List;
import java.util.Optional;

import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.services.ServicePlannerService;
import iss4u.ehr.clinique_projet.settings.services.ServiceZoneService;
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
@RequestMapping("serviceZone")
public class ServiceZoneController {

	@Autowired
	private ServiceZoneService serviceZoneService;

	@Autowired
	private ServicePlannerService serviceService;

	public ServiceZoneController(ServicePlannerService iServicesService) {
		this.serviceService = iServicesService;
	}
	public ServiceZoneController() {

	}


	public ServiceZoneController(ServiceZoneService iService) {
        this.serviceZoneService = iService;
    }
	
	@GetMapping()
	List <ServiceZone> all(){
		try {
			return serviceZoneService.findAllServiceZone();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération des service Zones", e);
		}
	}
	
	@GetMapping("/name/{iServiceZoneNm}")
    public ServiceZone findServiceZoneByName(@PathVariable String iServiceZoneNm) {
		try {
			return serviceZoneService.findServiceZoneByName(iServiceZoneNm);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération par nom des service Zones", e);
		}
        
    }
	
	   @GetMapping("/{iServiceZone_ky}")
	    public ResponseEntity<ServiceZone> findServiceById(@PathVariable int iServiceZone_ky) {
		   try {
			   Optional<ServiceZone> aServiceOptional = serviceZoneService.findServiceZoneById(iServiceZone_ky);
			   if (aServiceOptional.isPresent()) {
				   return ResponseEntity.ok(aServiceOptional.get());
			   } else {
				   return ResponseEntity.notFound().build();
			   }
		   }
		   catch (Exception e) {
			   e.printStackTrace();
			   throw new RuntimeException("Une erreur s'est produite lors de la récupération par id des service Zones", e);
		   }
	    }
	
	@PostMapping()
	 ServiceZone newServiceZone(@RequestBody ServiceZone ServiceZone) {
		try {
			return serviceZoneService.addServiceZone(ServiceZone);

		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout du service Zone", e);
		}
	}
	
	
	@PutMapping("/{iServiceZone_ky}")
	ServiceZone updateServiceZone(@RequestBody ServiceZone ServiceZone,@PathVariable int iServiceZone_ky) {
		try {
			ServiceZone.setServiceZone_ky(iServiceZone_ky);
			return serviceZoneService.updateServiceZone(ServiceZone);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la modification du service Zone", e);
		}

	}
	
	@DeleteMapping("/{iServiceZone_ky}")
	void deleteServiceZone(@PathVariable int iServiceZone_ky) {
		try {
			serviceZoneService.deleteServiceZone(iServiceZone_ky);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la supression du service Zone", e);
		}
	}
	 @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

	//routage pour récuperer les serviceZone d'un service
		@GetMapping("/serviceZone-by-service/{iServiceId}")
	public ResponseEntity<List<ServiceZone>> getServiceZoneByService(@PathVariable int iServiceId) {
		try {
			if (iServiceId<= 0) { throw new IllegalArgumentException("Service ID must be positive."); }
			List<ServiceZone> aServiceZoneList = serviceService.getServiceZoneByService(iServiceId);

			return new ResponseEntity<>(aServiceZoneList, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite de la récupération des services zone dans un service", e);
		}

	}

}


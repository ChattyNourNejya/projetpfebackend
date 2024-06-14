package iss4u.ehr.clinique_projet.settings.controllers;


import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.settings.entities.Site;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import iss4u.ehr.clinique_projet.settings.services.SiteGroupService;
import iss4u.ehr.clinique_projet.settings.services.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/site")
public class SiteController {
	
	private SiteService siteService;

	@Autowired
	private SiteGroupService siteGroupService;

	@Autowired
	public SiteController(SiteService iService) {
        this.siteService = iService;
    }
	

		@GetMapping()
		public ResponseEntity<List<Site> >all() {
			try {
				List<Site> aSiteList = siteService.findAllSite();
				if (aSiteList.isEmpty()) {
					return ResponseEntity.noContent().build();
				} else {
					return ResponseEntity.ok(aSiteList);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Une erreur s'est produite de la récupération des sites ", e);
			}
		}
	
	
	@GetMapping("/name/{iSiteNm}")
    public Site findSiteByName(@PathVariable String iSiteNm) {
		try {
			return siteService.findSiteByName(iSiteNm);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite de la récupération par nom des sites", e);
		}
        
    }
	@GetMapping("/country/{iSiteCountry}")
    public Site findSiteByCountry(@PathVariable String iSiteCountry) {
		try {
			return siteService.findSiteByCountry(iSiteCountry);		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite de la récupération par country des sites", e);
		}
    }

	//routage pour l'ajout d un service dans un site
	@PostMapping("/{iSiteId}/addService")
	public ResponseEntity<LeService> addServiceToSite(@PathVariable int iSiteId, @RequestBody LeService newService) {
		try {
			LeService aServiceAdded = siteService.addServiceToSite(iSiteId, newService);
			return new ResponseEntity<>(aServiceAdded, HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout d'un service dans le site", e);
		}

	}


	@PostMapping("/{siteId}/addStaff")
	public ResponseEntity<Staff> addStaffToSite(@PathVariable int siteId, @RequestBody Staff newStaff){
		Staff addedStaff= siteService.addStaffToService(siteId,newStaff);
		return new ResponseEntity<>(addedStaff, HttpStatus.CREATED);
	}



	@GetMapping("/{id}")
	public ResponseEntity<Site> findServiceById(@PathVariable int id) {
		Optional<Site> serviceOptional = siteService.findSiteById(id);
		if (serviceOptional.isPresent()) {
			return ResponseEntity.ok(serviceOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	
	@PostMapping()
	 Site newSite(@RequestBody Site Site) {
		try {
			return siteService.addSite(Site);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout du site", e);
		}
	}
	
	
	@PutMapping("/{iSite_ky}")
	Site updateSite(@RequestBody Site site,@PathVariable int iSite_ky) {
		try {
			site.setSite_ky(iSite_ky);
			return siteService.updateSite(site);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la modification du site", e);
		}
	}
	
	@DeleteMapping("/{iSite_ky}")
	void deleteSite(@PathVariable int iSite_ky) {
		try {
			siteService.deleteSite(iSite_ky);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la supression du site", e);
		}
	}
	 @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }



	// routage pour récuperer les sites dans un siteGroup
	@GetMapping("/sites-by-group/{iGroupId}")
	public ResponseEntity<List<Site>> getSitesByGroup(@PathVariable int iGroupId) {
		try {
			if (iGroupId<= 0) { throw new IllegalArgumentException("SiteGroup ID must be positive."); }
			List<Site> aSiteList = siteGroupService.getSitesBySiteGroup(iGroupId);

			return new ResponseEntity<>(aSiteList, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération des sites dans un siteGroup ", e);
		}
	}

}


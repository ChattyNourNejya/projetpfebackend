package iss4u.ehr.clinique_projet.settings.controllers;

import java.util.List;
import java.util.Optional;

import iss4u.ehr.clinique_projet.settings.entities.Site;
import iss4u.ehr.clinique_projet.settings.entities.SiteGroup;
import iss4u.ehr.clinique_projet.settings.services.SiteGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/siteGroup")
@CrossOrigin(origins=("*"))

public class SiteGroupController {

	@Autowired
private SiteGroupService siteGroupService;
	
	public SiteGroupController(SiteGroupService iService) {
			this.siteGroupService = iService;
    }
	
	@GetMapping()
	List <SiteGroup> all(){
		try {
			return siteGroupService.findAllSiteGroup();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération des siteGroup", e);
		}

	}
	
	@GetMapping("/name/{iSiteGroupNm}")
    public SiteGroup findSiteByName(@PathVariable String iSiteGroupNm) {
		try {
			return siteGroupService.findSiteByName(iSiteGroupNm);

		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la récupération par nom des siteGroup", e);
		}
    }

	//routage pour l'ajout d un site dans un siteGroup
	@PostMapping("/{iSiteGroupId}/addSite")
	public ResponseEntity<Site> addSiteToGroup(@PathVariable int iSiteGroupId, @RequestBody Site newSite) {
		try {
			Site aSiteAdded = siteGroupService.addSiteToGroup(iSiteGroupId, newSite);
			return new ResponseEntity<>(aSiteAdded, HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout du site dans un siteGroup", e);
		}
	}
	
	@PostMapping()
	 SiteGroup newSiteGroup(@RequestBody SiteGroup SiteGroup) {
		try {
			return siteGroupService.addSiteGroup(SiteGroup);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de l'ajout du siteGroup", e);
		}
	}
	
	   @GetMapping("/{iSiteGroup_ky}")
	    public ResponseEntity<SiteGroup> findServiceById(@PathVariable int iSiteGroup_ky) {
	        try {
				Optional<SiteGroup> aServiceOptional = siteGroupService.findSiteGroupById(iSiteGroup_ky);
				if (aServiceOptional.isPresent()) {
					return ResponseEntity.ok(aServiceOptional.get());
				} else {
					return ResponseEntity.notFound().build();
				}
			}
			catch (Exception e) {
			   e.printStackTrace();
			   throw new RuntimeException("Une erreur s'est produite lors de la récupération par id des siteGroup", e);
		   }
	    }

	@PutMapping("/{iSiteGroup_ky}")
	SiteGroup updateSiteGroup(@RequestBody SiteGroup SiteGroup, @PathVariable int iSiteGroup_ky) {
		try {
			SiteGroup.setSiteGroup_ky(iSiteGroup_ky);
			System.out.println("Received updated entity: " + SiteGroup);
			return siteGroupService.updateSiteGroup(SiteGroup);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la modification du siteGroup", e);
		}
	}


	@DeleteMapping("/{iSiteGroup_ky}")
	void deleteSiteGroup(@PathVariable int iSiteGroup_ky) {
		try {
			siteGroupService.deleteSiteGroup(iSiteGroup_ky);

		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Une erreur s'est produite lors de la supression du siteGroup", e);
		}
	}

	 @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Log the exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}

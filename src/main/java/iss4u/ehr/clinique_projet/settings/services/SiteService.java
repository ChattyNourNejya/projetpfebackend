package iss4u.ehr.clinique_projet.settings.services;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.settings.entities.Site;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import iss4u.ehr.clinique_projet.settings.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;


@Service
public class SiteService {

	@Autowired
	SiteRepository siteRepository;



	public Site addSite(Site s) {
		return siteRepository.save(s);
	}

	public List<Site> findAllSite(){
		return siteRepository.findAll();
	}

	public Optional<Site> findSiteById(int iSite_ky) {
		 return siteRepository.findById(iSite_ky);
		}

	public Site updateSite(Site s) {
		return siteRepository.save(s);
	}

	public void deleteSite(int iSite_ky) {
		siteRepository.deleteById(iSite_ky);
	}

	public Site findSiteByName(String iSiteNm) {
		 return siteRepository.findByName(iSiteNm);
	}

	public Site findSiteByCountry(String iSiteCountry) {
		 return siteRepository.findSiteByCountry(iSiteCountry);
	}


	//fonction pour ajouter un service dans un site
	public LeService addServiceToSite(int iServiceId, LeService newService) {
		Optional<Site> aSiteOptional = siteRepository.findById(iServiceId);

		if (aSiteOptional.isPresent()) {
			Site aSite = aSiteOptional.get();
			aSite.addService(newService);
			siteRepository.save(aSite);

			return newService;
		} else {
			throw new EntityNotFoundException("SiteGroup not found with id: " + iServiceId);
		}
	}



	public Staff addStaffToService(int serviceId, Staff newStaff) {
		Optional<Site> optionalSite = siteRepository.findById(serviceId);
		if (optionalSite.isPresent()) {
			Site site = optionalSite.get();
			site.addStaff(newStaff);
			siteRepository.save(site);

			return newStaff;
		} else {
			throw new EntityNotFoundException("Staff not found with id: " + serviceId);
		}
	}



	//fonction pour récupérer les services ajouté par un site
	public List<LeService> getServiceBySite(int iSiteId) {
		if (iSiteId <= 0) { throw new IllegalArgumentException("Site ID must be positive."); }
		Optional<Site> aSiteOptional = siteRepository.findById(iSiteId);

		if (aSiteOptional.isPresent()) {
			Site aSite = aSiteOptional.get();
			return aSite.getService();
		}

		return Collections.emptyList();
	}

}


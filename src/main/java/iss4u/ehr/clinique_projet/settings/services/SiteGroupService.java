package iss4u.ehr.clinique_projet.settings.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import
		iss4u.ehr.clinique_projet.settings.entities.Site;

import iss4u.ehr.clinique_projet.settings.entities.SiteGroup;
import iss4u.ehr.clinique_projet.settings.repositories.SiteGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;


@Service
public class SiteGroupService {
	
	@Autowired
	SiteGroupRepository siteGroupRepository;
	
	public SiteGroup addSiteGroup(SiteGroup s) {
		return siteGroupRepository.save(s);
	}
	
	public List<SiteGroup> findAllSiteGroup(){
		return siteGroupRepository.findAll();
	}
	
	public Optional<SiteGroup> findSiteGroupById(int iSiteGroup_ky) {
		 return siteGroupRepository.findById(iSiteGroup_ky);
		}

	
	public SiteGroup updateSiteGroup(SiteGroup s) {
		return siteGroupRepository.save(s);
	}
	
	public void deleteSiteGroup(int iSiteGroup_ky) {
		siteGroupRepository.deleteById(iSiteGroup_ky);
	}
	
	public SiteGroup findSiteByName(String iSiteGroupNm) {
		 return siteGroupRepository.findSiteGroupByName(iSiteGroupNm);
	}

	// fonction pour l'ajout d'un site dans un siteGroup
	public Site addSiteToGroup(int iSiteGroupId, Site newSite) {
		Optional<SiteGroup> aSiteGroupOptional = siteGroupRepository.findById(iSiteGroupId);

		if (aSiteGroupOptional.isPresent()) {
			SiteGroup aSiteGroup = aSiteGroupOptional.get();
			aSiteGroup.addSite(newSite);
			siteGroupRepository.save(aSiteGroup);

			return newSite;
		} else {
			throw new EntityNotFoundException("SiteGroup not found with id: " + iSiteGroupId);
		}
	}

	// fonction pour recuperer les site ajouté par un siteGroup
	public List<Site> getSitesBySiteGroup(int iSiteGroupId) {
		if (iSiteGroupId <= 0) { throw new IllegalArgumentException("SiteGroup ID must be positive."); }
		Optional<SiteGroup> aSiteGroupOptional = siteGroupRepository.findById(iSiteGroupId);

		if (aSiteGroupOptional.isPresent()) {
			SiteGroup siteGroup = aSiteGroupOptional.get();
			return siteGroup.getSite();
		}

		return Collections.emptyList();
	}
}

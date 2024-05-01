package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.SiteGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SiteGroupRepository extends JpaRepository<SiteGroup, Integer>{

	@Query("SELECT sg FROM SiteGroup sg WHERE sg.SiteGroup_Nm = ?1")
	SiteGroup findSiteGroupByName(String siteGroupNm);

}

package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Integer>{


	@Query("SELECT s FROM Site s WHERE s.Site_Nm = ?1")
	Site findByName(String site_Nm);

	@Query("SELECT s FROM Site s WHERE s.Site_Country = ?1")
	Site findSiteByCountry(String siteCountry);






}

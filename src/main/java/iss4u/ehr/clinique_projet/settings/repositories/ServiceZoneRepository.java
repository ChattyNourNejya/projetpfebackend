package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ServiceZoneRepository extends JpaRepository<ServiceZone, Integer>{

	@Query("SELECT sz FROM ServiceZone sz WHERE sz.ServiceZone_Nm = ?1")
	ServiceZone findByName(String serviceZone_Nm);

}

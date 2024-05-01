package iss4u.ehr.clinique_projet.settings.repositories;
import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Servicee, Integer>{

	@Query("SELECT s FROM Servicee s WHERE s.Service_Nm = ?1")
	Servicee findByName(String serviceNm);


	@Query("SELECT s FROM Servicee s WHERE s.Service_Nm = ?1")
	List<Servicee> findByserviceNm(String serviceNm);


}

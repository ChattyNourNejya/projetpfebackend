package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.settings.entities.LeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeServiceRepository extends JpaRepository<LeService, Integer> {
    @Query("SELECT s FROM LeService s WHERE s.service_Nm = ?1")
    LeService findByName(String serviceNm);


    @Query("SELECT s FROM LeService s WHERE s.service_Nm = ?1")
    List<LeService>  getLeServiceByserviceNm(String serviceNm);




}

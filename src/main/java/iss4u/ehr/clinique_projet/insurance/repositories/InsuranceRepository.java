package iss4u.ehr.clinique_projet.insurance.repositories;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByInsNm(String insNm);
    Optional<Insurance> findById(Long insKy);

    List<Insurance> getInsuranceByinsNm(String insNm);

}
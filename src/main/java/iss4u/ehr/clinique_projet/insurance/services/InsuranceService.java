package iss4u.ehr.clinique_projet.insurance.services;

import iss4u.ehr.clinique_projet.insurance.entities.Insurance;

import java.util.List;
import java.util.Optional;

public interface InsuranceService {

    void create(Insurance insurance);

    List<Insurance> retrieveInsurances();


    Optional<Insurance> getInsuranceByKy(Long insKy);


    List<Insurance> getInsuranceByCompanyName(String insNm);

    void update(Insurance insurance);

    void delete(Long insuranceKy);


    Insurance getInsuranceByKey(Long insKy);


}








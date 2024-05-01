package iss4u.ehr.clinique_projet.insurance.services.implementations;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.insurance.repositories.InsuranceRepository;
import iss4u.ehr.clinique_projet.insurance.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    @Override
    public void create(Insurance insurance) {
        insuranceRepository.save(insurance);

    }

    @Override
    public List<Insurance> retrieveInsurances() {

        return insuranceRepository.findAll();
    }
    @Override
    public Optional<Insurance> getInsuranceByKy(Long insKy) {
        return insuranceRepository.findById(insKy);
    }
    @Override
    public List<Insurance> getInsuranceByCompanyName(String insNm) {
        return insuranceRepository.getInsuranceByinsNm(insNm);
    }

    @Override
    public void update(Insurance insurance) {
        insuranceRepository.save(insurance);

    }

    @Override
    public void delete(Long insuranceKy) {
        insuranceRepository.deleteById(insuranceKy);

    }



    @Override
    public Insurance getInsuranceByKey(Long insKy) {
        // Implement the logic to fetch the Insurance object using the insKy
        // For example, you can use a database query or a cache to retrieve the Insurance object
        return insuranceRepository.findById(insKy).orElseThrow();
    }
}

package iss4u.ehr.clinique_projet.settings.services;



import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import iss4u.ehr.clinique_projet.settings.repositories.FunctionalUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionalUnitServiceImpl implements FunctionalUnitService {

    @Autowired
    private FunctionalUnitRepository functionalUnitRepository;

    @Override
    public List<FunctionalUnit> findAllFunctionalUnit(){
        return functionalUnitRepository.findAll();
    }

    @Override
    public FunctionalUnit saveFunctionalUnit(FunctionalUnit functionalUnit) {
        return functionalUnitRepository.save(functionalUnit);
    }


    @Override
    public List<String> findAllFunctionalUnitNames() {
        return functionalUnitRepository.findFunctionalUnitNames();
    }

    @Override
    public FunctionalUnit findFunctionalUnitNameByNm(String name) {
        return functionalUnitRepository.findFunctionalUnitNameByNm(name);
    }



    @Override
    public FunctionalUnit updateFunctionalUnit(FunctionalUnit functionalUnit, long FunctionalUnit_Ky) {
        FunctionalUnit aExistingFunctionalUnit = new FunctionalUnit();
        aExistingFunctionalUnit.setFunctionalUnit_Ky(FunctionalUnit_Ky);

        return functionalUnitRepository.save(aExistingFunctionalUnit);
    }

    @Override
    public void deleteFunctionalUnit(long FunctionalUnit_Ky) {
        functionalUnitRepository.deleteById(FunctionalUnit_Ky);
    }
    @Override
    public FunctionalUnit findFunctionalUnitById(long FunctionalUnit_Ky) {
        return functionalUnitRepository.findByFunctionalUnit_Ky(FunctionalUnit_Ky);
    }
}

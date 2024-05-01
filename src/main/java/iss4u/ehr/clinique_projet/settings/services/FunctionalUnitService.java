package iss4u.ehr.clinique_projet.settings.services;


import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;

import java.util.List;
import java.util.Optional;

public interface FunctionalUnitService {

    List<FunctionalUnit> findAllFunctionalUnit();
    List<String> findAllFunctionalUnitNames();
    FunctionalUnit saveFunctionalUnit(FunctionalUnit functionalUnit);


    FunctionalUnit updateFunctionalUnit(FunctionalUnit functionalUnit, long FunctionalUnit_Ky);
    void deleteFunctionalUnit(long FunctionalUnit_Ky);

    FunctionalUnit findFunctionalUnitNameByNm(String name);

    FunctionalUnit findFunctionalUnitById(long FunctionalUnit_Ky);

}

package iss4u.ehr.clinique_projet.settings.repositories;

import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FunctionalUnitRepository extends JpaRepository<FunctionalUnit,Long> {
    @Query("SELECT fu.FunctionalUnit_Nm FROM FunctionalUnit fu")
    List<String> findFunctionalUnitNames();

    @Query("SELECT fu FROM FunctionalUnit fu WHERE fu.FunctionalUnit_Nm = ?1")
    FunctionalUnit findFunctionalUnitNameByNm(String name);
    @Query("SELECT fu FROM FunctionalUnit fu WHERE fu.FunctionalUnit_Ky = ?1")
    FunctionalUnit findByFunctionalUnit_Ky(long functionalUnitKey);

}




package iss4u.ehr.clinique_projet.patient.repositories;
import iss4u.ehr.clinique_projet.patient.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {


    @Modifying
    @Query("DELETE FROM Address a WHERE a.addressKy = :addressId AND a.patient.patientKy = :patientKy")
    void deleteAddressByPatientKy(@Param("addressId") int addressId, @Param("patientKy") int patientKy);

    @Query("SELECT a FROM Address a WHERE a.patient.patientKy = :patientKy AND a.addressKy = :addressId")
    Address findByAddressPatientId(@Param("addressId") int addressId, @Param("patientKy") int patientKy);


}

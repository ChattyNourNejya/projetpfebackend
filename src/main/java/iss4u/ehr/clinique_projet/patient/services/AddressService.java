package iss4u.ehr.clinique_projet.patient.services;



import iss4u.ehr.clinique_projet.patient.entities.Address;
import iss4u.ehr.clinique_projet.patient.entities.Patient;

import java.util.List;

public interface AddressService {
    public void deleteAddress(int addressId, int patientKy);
    public Address UpdateAddress(int patientKy, Address updatedAddress);
    public Address GetAddress(int addressId, int patientKy);
    public Address AddAddress(Address address);

    void updatePatientAddresses(Patient updatedPatient, List<Address> addresses);
}

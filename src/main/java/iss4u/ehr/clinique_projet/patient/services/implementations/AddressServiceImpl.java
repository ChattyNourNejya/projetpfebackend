package iss4u.ehr.clinique_projet.patient.services.implementations;



import iss4u.ehr.clinique_projet.patient.entities.Address;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.repositories.AddressRepository;
import iss4u.ehr.clinique_projet.patient.repositories.PatientRepository;
import iss4u.ehr.clinique_projet.patient.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private PatientRepository patientRepository;
    @Transactional
    @Override
    public void deleteAddress(int addressId, int patientKy) {
        Patient patient = patientRepository.findByPatientKy(patientKy);

        // Vérifier si le patient existe
        if (patient == null) {
            throw new EntityNotFoundException("Patient non trouvé");
        } else {
            addressRepository.deleteAddressByPatientKy(addressId, patientKy);
        }
    }

    @Override
    public Address UpdateAddress(int patientKy, Address updatedAddress) {
        // Récupérer le patient correspondant à l'identifiant patientId
        Patient patient = patientRepository.findByPatientKy(patientKy);
        if (patient != null) {

            // Trouver l'adresse à mettre à jour dans la liste des adresses du patient
            for (Address address : patient.getAddresses()) {
                if (address.getAddressKy() == updatedAddress.getAddressKy()) {
                    // Mettre à jour les informations de l'adresse
                    address.setAddressType(updatedAddress.getAddressType());
                    address.setAddressCity(updatedAddress.getAddressCity());
                    address.setAddressCountry(updatedAddress.getAddressCountry());
                    address.setAddressDetails(updatedAddress.getAddressDetails());
                    address.setAddressPrimary(updatedAddress.isAddressPrimary());
                    address.setAddressApartmentNumber(updatedAddress.getAddressApartmentNumber());
                    address.setAddressStreetNumber(updatedAddress.getAddressStreetNumber());
                    address.setAddressBuildingLabel(updatedAddress.getAddressBuildingLabel());
                    address.setAddressAvenueLabel(updatedAddress.getAddressAvenueLabel());
                    address.setAddressResidenceLabel(updatedAddress.getAddressResidenceLabel());
                    // Enregistrer les modifications dans la base de données
                    return addressRepository.save(address);
                }
            }
        }

        // Si l'adresse n'est pas trouvée, renvoyer null ou gérer l'erreur selon les besoins
        return null;
    }

    @Override
    public Address GetAddress(int addressId, int patientKy) {
        return addressRepository.findByAddressPatientId(addressId, patientKy);
    }

    @Override
    public Address AddAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void updatePatientAddresses(Patient updatedPatient, List<Address> newAddresses) {
        List<Address> existingAddresses = new ArrayList<>(updatedPatient.getAddresses());

        // Update existing addresses
        for (Iterator<Address> iterator = existingAddresses.iterator(); iterator.hasNext();) {
            Address existingAddress = iterator.next();
            for (Address newAddress : newAddresses) {
                if (existingAddress.getAddressKy() == newAddress.getAddressKy()) {
                    existingAddress.setAddressStreetNumber(newAddress.getAddressStreetNumber());
                    existingAddress.setAddressStreetLabel(newAddress.getAddressStreetLabel());
                    existingAddress.setAddressCity(newAddress.getAddressCity());
                    existingAddress.setAddressCountry(newAddress.getAddressCountry());
                    existingAddress.setAddressPrimary(newAddress.isAddressPrimary());
                    existingAddress.setAddressResidenceLabel(newAddress.getAddressResidenceLabel());
                    existingAddress.setAddressBuildingLabel(newAddress.getAddressBuildingLabel());
                    existingAddress.setAddressAvenueLabel(newAddress.getAddressAvenueLabel());
                    existingAddress.setAddressDetails(newAddress.getAddressDetails());
                    existingAddress.setAddressApartmentNumber(newAddress.getAddressApartmentNumber());
                    existingAddress.setAddressType(newAddress.getAddressType());
                    existingAddress.setAddressPostalCode(newAddress.getAddressPostalCode());
                    existingAddress.setAddressValid(newAddress.getAddressValid());
                    addressRepository.save(existingAddress);
                    iterator.remove(); // Remove the updated address from the temporary list
                    break;
                }
            }
        }

        // Add new addresses if they don't already exist
        for (Address newAddress : newAddresses) {
            boolean addressExists = existingAddresses.stream()
                    .anyMatch(existingAddress -> existingAddress.getAddressKy() == newAddress.getAddressKy());
            if (!addressExists) {
                newAddress.setPatient(updatedPatient);
                addressRepository.save(newAddress);
            }
        }
    }

}

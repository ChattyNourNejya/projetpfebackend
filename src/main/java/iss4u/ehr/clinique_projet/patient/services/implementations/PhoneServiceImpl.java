package iss4u.ehr.clinique_projet.patient.services.implementations;



import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.entities.Phone;
import iss4u.ehr.clinique_projet.patient.repositories.PatientRepository;
import iss4u.ehr.clinique_projet.patient.repositories.PhoneRepository;
import iss4u.ehr.patient.services.PhoneService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class PhoneServiceImpl  implements PhoneService {
    @Autowired
   private PhoneRepository phoneRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Transactional
    @Override
    public void deletePhone(int phoneId, int patientKy) {
        Patient patient = patientRepository.findByPatientKy(patientKy);

        // Vérifier si le patient existe
        if (patient == null) {
            throw new EntityNotFoundException("Patient non trouvé");
        }
        else {
            phoneRepository.deletePhoneByPatientKy(phoneId, patientKy);
        }
    }

    @Override
    public Phone UpdatePhone(int patientKy, Phone updatedPhone) {
        // Récupérer le patient correspondant à l'identifiant patientId
        Patient patient = patientRepository.findByPatientKy(patientKy);
        if (patient!=null)
        {

        // Trouver le téléphone à mettre à jour dans la liste des téléphones du patient
        for (Phone phone : patient.getPhones()) {
            if (phone.getPhoneKy() == updatedPhone.getPhoneKy()) {
                // Mettre à jour les informations du téléphone
                phone.setPhoneNumber(updatedPhone.getPhoneNumber());
                phone.setPhoneType(updatedPhone.getPhoneType());
                phone.setPhoneCountry(updatedPhone.getPhoneCountry());
                phone.setPhonePrimary(updatedPhone.isPhonePrimary());
                phone.setPhoneProfessional(updatedPhone.isPhoneProfessional());
                phone.setPhoneReceiveSMS(updatedPhone.isPhoneReceiveSMS());
                phone.setPhonePrntType(updatedPhone.getPhonePrntType());
                // Enregistrer les modifications dans la base de données
                 phoneRepository.save(phone);
                return phone;
            }
        }
         }

        // Si le téléphone n'est pas trouvé, renvoyer null ou gérer l'erreur selon les besoins
        return null;
    }

    @Override
    public Phone GetPhone(int phoneKy,int patientKy) {
 return phoneRepository.findByPhonePatientId(phoneKy,patientKy);

    }

    @Override
    public Phone AddPhone(Phone phone) {
        phoneRepository.save(phone);
        return  phone;
    }

    @Override
    public void updatePatientPhones(Patient updatedPatient, List<Phone> newPhones) {
        List<Phone> existingPhones = new ArrayList<>(updatedPatient.getPhones());

        // Update existing phone numbers
        Iterator<Phone> iterator = existingPhones.iterator();
        while (iterator.hasNext()) {
            Phone existingPhone = iterator.next();
            for (Phone newPhone : newPhones) {
                if (existingPhone.getPhoneKy() == newPhone.getPhoneKy()) {
                    existingPhone.setPhoneNumber(newPhone.getPhoneNumber());
                    existingPhone.setPhoneType(newPhone.getPhoneType());
                    existingPhone.setPhoneProfessional(newPhone.isPhoneProfessional());
                    existingPhone.setPhonePrimary(newPhone.isPhonePrimary());
                    existingPhone.setPhoneCountry(newPhone.getPhoneCountry());
                    existingPhone.setPhoneReceiveSMS(newPhone.isPhoneReceiveSMS());
                    phoneRepository.save(existingPhone);
                    iterator.remove(); // Remove the item from the temporary list
                    break; // Exit the inner loop once the phone number is updated
                }
            }
        }


        // Add new phone numbers if they don't already exist
        for (Phone newPhone : newPhones) {
            boolean phoneExists = existingPhones.stream()
                    .anyMatch(existingPhone -> existingPhone.getPhoneKy() == newPhone.getPhoneKy());
            if (!phoneExists) {
                newPhone.setPatient(updatedPatient);
                phoneRepository.save(newPhone);
            }
        }
    }


}

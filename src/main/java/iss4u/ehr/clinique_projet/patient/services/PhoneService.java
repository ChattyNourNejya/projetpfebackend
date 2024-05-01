package iss4u.ehr.patient.services;





import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.entities.Phone;

import java.util.List;

public interface PhoneService {
    void deletePhone(int phoneId, int patientKy);
    Phone UpdatePhone(int patientKy, Phone updatedPhone);
    Phone GetPhone(int phoneKy,int patientKy);
    Phone AddPhone(Phone phone);


    void updatePatientPhones(Patient updatedPatient, List<Phone> phones);
}

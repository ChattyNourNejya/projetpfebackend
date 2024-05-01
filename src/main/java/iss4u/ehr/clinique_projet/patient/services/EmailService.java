package iss4u.ehr.clinique_projet.patient.services;





import iss4u.ehr.clinique_projet.patient.entities.Email;
import iss4u.ehr.clinique_projet.patient.entities.Patient;

import java.util.List;

public interface EmailService {
    public Email GetEmail(int emailId, int patientKy);
    public Email AddEmail(Email email);
    public Email UpdateEmail(int emailId,int patientKy, Email updatedEmail);
    public void deleteEmail(int emailId, int patientKy);

    void updatePatientEmails(Patient updatedPatient, List<Email> emails);
}

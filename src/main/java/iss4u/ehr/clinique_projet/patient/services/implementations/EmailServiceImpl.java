package iss4u.ehr.clinique_projet.patient.services.implementations;


import iss4u.ehr.clinique_projet.patient.entities.Email;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.repositories.EmailRepository;
import iss4u.ehr.clinique_projet.patient.repositories.PatientRepository;
import iss4u.ehr.clinique_projet.patient.services.EmailService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PatientRepository patientRepository;
    @Transactional
    @Override
    public void deleteEmail(int emailId, int patientKy) {
        Patient patient = patientRepository.findByPatientKy(patientKy);

        // Check if the patient exists
        if (patient == null) {
            throw new EntityNotFoundException("Patient non trouvé");
        } else {
            emailRepository.deleteEmailByPatientKy(emailId, patientKy);
        }
    }

    @Override
    public void updatePatientEmails(Patient updatedPatient, List<Email> newEmails) {
        List<Email> existingEmails = new ArrayList<>(updatedPatient.getEmails());

        // Update existing emails
        for (Iterator<Email> iterator = existingEmails.iterator(); iterator.hasNext();) {
            Email existingEmail = iterator.next();
            for (Email newEmail : newEmails) {
                if (existingEmail.getEmailKy() == newEmail.getEmailKy()) {
                    existingEmail.setEmailValue(newEmail.getEmailValue());
                    existingEmail.setEmailPrimary(newEmail.isEmailPrimary());

                    emailRepository.save(existingEmail);
                    iterator.remove(); // Remove the updated email from the temporary list
                    break;
                }
            }
        }

        // Add new emails if they don't already exist
        for (Email newEmail : newEmails) {
            boolean emailExists = existingEmails.stream()
                    .anyMatch(existingEmail -> existingEmail.getEmailKy() == newEmail.getEmailKy());
            if (!emailExists) {
                newEmail.setPatient(updatedPatient);
                emailRepository.save(newEmail);
            }
        }
    }



    @Override
    public Email UpdateEmail(int emailId,int patientKy, Email updatedEmail) {
        Email email=emailRepository.findByEmailPatientId(emailId, patientKy);
        if (email!=null){
                    email.setEmailValue(updatedEmail.getEmailValue());
            // Save the modifications to the database
                    return emailRepository.save(email);

        }

        // If the email is not found, return null or handle the error as needed
        return null;
    }

    @Override
    public Email GetEmail(int emailId, int patientKy) {
        return emailRepository.findByEmailPatientId(emailId, patientKy);
    }

    @Override
    public Email AddEmail(Email email) {

        return emailRepository.save(email);
    }
}


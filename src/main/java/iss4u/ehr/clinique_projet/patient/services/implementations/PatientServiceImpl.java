package iss4u.ehr.clinique_projet.patient.services.implementations;

import iss4u.ehr.clinique_projet.stay.entities.Stay;
import jakarta.persistence.EntityNotFoundException;

import iss4u.ehr.clinique_projet.patient.entities.*;
import iss4u.ehr.clinique_projet.patient.repositories.AddressRepository;
import iss4u.ehr.clinique_projet.patient.repositories.EmailRepository;
import iss4u.ehr.clinique_projet.patient.repositories.PatientRepository;
import iss4u.ehr.clinique_projet.patient.repositories.PhoneRepository;
import iss4u.ehr.clinique_projet.patient.services.AddressService;
import iss4u.ehr.clinique_projet.patient.services.EmailService;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.patient.services.PhoneService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PhoneService phoneService;

    @Override
    public Patient createPatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public List<Patient> retrievePatients() {
        return patientRepository.findAllWithAddressesEmailsPhones();
    }

    @Override
    public Optional<Patient> getPatientByKy(int patientKy) {
        return patientRepository.findById(patientKy);
    }

    @Override
    public Optional<Patient> getPatientByNameDOB(String firstName, String lastName, LocalDate dob) {
        return patientRepository.findByNameDOB(firstName,lastName, dob);
    }

    @Override
    public Long getPatientIdByNameDOB(String firstName, String lastName, LocalDate dob) {
        return patientRepository.findIdByNameDOB(firstName,lastName, dob);
    }

    @Override
    @Transactional
    public Patient update(int patientKy, Patient updatedPatient) {
        patientRepository.deleteById(patientKy);
        return patientRepository.save(updatedPatient);
    }
    @Override
    public Stay addStayToPatient(int patientKy, Stay newStay) {
        Optional<Patient> patientOptional = patientRepository.findById(patientKy);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            patient.addStay(newStay);
            patientRepository.save(patient);
            return newStay;
        } else {
            throw new EntityNotFoundException("Patient not found with id: " + patientKy);
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(Math.toIntExact(id)).orElse(null);
    }
    @Override
    public List<Stay> getStaysByPatient(int patientKy) {
        if (patientKy <= 0) {
            throw new IllegalArgumentException("Patient ID must be positive.");
        }

        Optional<Patient> patientOptional = patientRepository.findById(patientKy);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return patient.getStay();
        }

        return Collections.emptyList();
    }

    @Override
    public Patient update2(int patientKy, PatientRequest request) {
        Patient patient = request.getPatient();
        List<Address> addresses = request.getPatient().getAddresses();
        List<Email> emails = request.getPatient().getEmails();
        List<Phone> phones = request.getPatient().getPhones();

        // Update patient
        Patient updatedPatient = patientRepository.findById(patientKy)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé avec la clé : " + patientKy));
        updatedPatient.setPatientFirstName(patient.getPatientFirstName());
        updatedPatient.setPatientLastName(patient.getPatientLastName());
        updatedPatient.setPatientMiddleName(patient.getPatientMiddleName());
        updatedPatient.setPatientIdentityNumber(patient.getPatientIdentityNumber());
        updatedPatient.setPatientIdentityType(patient.getPatientIdentityType());
        updatedPatient.setPatientBirthDate(patient.getPatientBirthDate());
        updatedPatient.setPatientGender(patient.getPatientGender());
        updatedPatient.setPatientMaritalStatus(patient.getPatientMaritalStatus());
        updatedPatient.setPatientNationality(patient.getPatientNationality());
        updatedPatient.setPatientDeathDate(patient.getPatientDeathDate());
        updatedPatient.setPatientDeathRemarks(patient.getPatientDeathRemarks());
        updatedPatient.setPatientSize(patient.getPatientSize());
        updatedPatient.setPatientWeight(patient.getPatientWeight());
        updatedPatient.setPatientRemarks(patient.getPatientRemarks());
        updatedPatient.setPatientStatus(patient.getPatientStatus());

        // Update adresses
        addressService.updatePatientAddresses(updatedPatient, addresses);

        // Update e-mails
        emailService.updatePatientEmails(updatedPatient, emails);

        // Update phones
        phoneService.updatePatientPhones(updatedPatient, phones);

        // save updated patient
        return patientRepository.save(updatedPatient);
    }

    @Override
    public void delete(int patientKy) {
        patientRepository.deleteById(patientKy);
    }

    @Transactional
    public void deletePatient(int patientKy) {
        // Retrieve the patient by ID
        Patient patient = patientRepository.findById(patientKy)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientKy));

        // Delete associated emails
        emailRepository.deleteAll(patient.getEmails());

        // Delete associated phones
        phoneRepository.deleteAll(patient.getPhones());

        // Delete associated addresses
        addressRepository.deleteAll(patient.getAddresses());

        // Finally, delete the patient
        patientRepository.delete(patient);
    }

    @Override
    public Patient disable(int patientKy) {
        Patient patient = patientRepository.findByPatientKy(patientKy);
        patient.setPatientStatus(PatientStatus.Provisional);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient enable(int patientKy) {
        Patient patient = patientRepository.findByPatientKy(patientKy);
        patient.setPatientStatus(PatientStatus.Valid);
        patientRepository.save(patient);
        return patient;
    }


}
package iss4u.ehr.clinique_projet.appointment.services.implimentations;

import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import iss4u.ehr.clinique_projet.appointment.repositories.MedVisitSchdldRepository;

import iss4u.ehr.clinique_projet.appointment.services.MedVisiTScdldService;
import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class MedVisitSchdldServiceImpl implements MedVisiTScdldService {
    @Autowired
    private MedVisitSchdldRepository medVisitSchdldRepository;

    @Autowired
    private PatientService patientService;


    public MedVisitSchdldServiceImpl(MedVisitSchdldRepository medVisitSchdldRepository) {
        this.medVisitSchdldRepository = medVisitSchdldRepository;
    }

    @Override
    public MedVisitSchdld saveMedVisitSchdld(MedVisitSchdld medVisitSchdld) {
        return medVisitSchdldRepository.save(medVisitSchdld);
    }

    @Override
    public MedVisitSchdld getMedVisitSchdldById(int medVisitSchdldId) {
        return medVisitSchdldRepository.findById(medVisitSchdldId).orElse(null);
    }

    @Override
    public List<MedVisitSchdld> getMedVisitSchdldByPatient(int patientKy) {
        Optional<Patient> patientOptional = patientService.getPatientByKy(patientKy);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return medVisitSchdldRepository.findByPatient(patient);
        } else {
            throw new RuntimeException("Patient not found");
        }
    }

    @Override
    public List<MedVisitSchdld> getAllMedVisitSchdlds() {
        return medVisitSchdldRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteMedVisitSchdldById(int medVisitSchdldId) {
        MedVisitSchdld medVisitSchdld = medVisitSchdldRepository.findById(medVisitSchdldId).orElse(null);
        if (medVisitSchdld != null) {
            medVisitSchdldRepository.delete(medVisitSchdld);
        } else {
            throw new EntityNotFoundException("MedVisitSchdld not found with id: " + medVisitSchdldId);
        }
    }


}

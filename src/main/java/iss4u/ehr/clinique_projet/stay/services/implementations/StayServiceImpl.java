package iss4u.ehr.clinique_projet.stay.services.implementations;



import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.patient.services.PatientService;
import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import iss4u.ehr.clinique_projet.stay.repositories.StayRepository;
import iss4u.ehr.clinique_projet.stay.services.StayService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StayServiceImpl implements StayService {

    @Autowired
    private StayRepository stayRepository;


    @Autowired
    private PatientService patientService;

    public StayServiceImpl(StayRepository stayRepository) {

        this.stayRepository=stayRepository;
    }

    @Override
    public Stay saveStay(Stay stay) {
        return stayRepository.save(stay);
    }

    @Override
    public Stay getStayById(int stayId) {
        return stayRepository.findById(stayId).orElse(null);
    }

    @Override
    public List<Stay> getStayByPatient(int patientKy) {
        Optional<Patient> patientOptional = patientService.getPatientByKy(patientKy);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return stayRepository.findByPatient(patient);
        } else {
            throw new RuntimeException("Patient not found");
        }
    }

    @Override
    public Servicee addServiceToStay(int iServiceId, Servicee newService) {
        Optional<Stay> aStayOptional = stayRepository.findById(iServiceId);

        if (aStayOptional.isPresent()) {
            Stay aStay = aStayOptional.get();
            aStay.addStayPertinentService(newService);
            stayRepository.save(aStay);

            return newService;
        } else {
            throw new EntityNotFoundException("Stay not found with id: " + iServiceId);
        }
    }


    @Override
    public List<Stay> getAllStays() {
        return stayRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteStayById(int stayId) {
        Stay stay = stayRepository.findById(stayId).orElse(null);




            stayRepository.delete(stay);

    }

}
package iss4u.ehr.clinique_projet.appointment.services;

import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;

import java.util.List;

public interface MedVisiTScdldService {

    MedVisitSchdld saveMedVisitSchdld(MedVisitSchdld medVisitSchdld);
    MedVisitSchdld getMedVisitSchdldById(int medVisitSchdldId);
    List<MedVisitSchdld> getMedVisitSchdldByPatient(int patientKy);
    List<MedVisitSchdld> getAllMedVisitSchdlds();
    void deleteMedVisitSchdldById(int medVisitSchdldId);
}

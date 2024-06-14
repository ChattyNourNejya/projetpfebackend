package iss4u.ehr.clinique_projet.stay.services;


import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.stay.entities.Stay;

import java.util.List;

public interface StayService {
    Stay saveStay(Stay stay);

    Stay getStayById(int stayId);

    List<Stay> getAllStays();

    public void deleteStayById(int stayId);

    public List<Stay> getStayByPatient(int Patient_Id);

    public LeService addServiceToStay(int iServiceId, LeService newService);
}
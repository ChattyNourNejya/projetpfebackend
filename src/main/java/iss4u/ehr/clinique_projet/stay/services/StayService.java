package iss4u.ehr.clinique_projet.stay.services;


import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import iss4u.ehr.clinique_projet.stay.entities.Stay;

import java.util.List;

public interface StayService {
    Stay saveStay(Stay stay);

    Stay getStayById(int stayId);

    List<Stay> getAllStays();

    public void deleteStayById(int stayId);

    public List<Stay> getStayByPatient(int Patient_Id);

    public Servicee addServiceToStay(int iServiceId, Servicee newService);
}
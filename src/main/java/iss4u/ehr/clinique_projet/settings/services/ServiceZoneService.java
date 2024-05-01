package iss4u.ehr.clinique_projet.settings.services;

import java.util.List;
import java.util.Optional;

import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.repositories.ServiceZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ServiceZoneService {

	@Autowired
	ServiceZoneRepository serviceZoneRepository;
	
	public ServiceZone addServiceZone(ServiceZone s) {
		return serviceZoneRepository.save(s);
	}
	
	public List<ServiceZone> findAllServiceZone(){
		return serviceZoneRepository.findAll();
	}
	
	public ServiceZone findServiceZoneByName(String iServiceZone_Nm) {
        return serviceZoneRepository.findByName(iServiceZone_Nm);
    }
	
	public Optional<ServiceZone> findServiceZoneById(int iServiceZone_ky) {
		 return serviceZoneRepository.findById(iServiceZone_ky);
		}

	
	public ServiceZone updateServiceZone(ServiceZone s) {
		return serviceZoneRepository.save(s);
	}
	
	public void deleteServiceZone(int iServiceZone_ky) {
		serviceZoneRepository.deleteById(iServiceZone_ky);
	}
}


package iss4u.ehr.clinique_projet.settings.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.entities.StaffGroup;
import iss4u.ehr.clinique_projet.settings.repositories.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class ServicePlannerService {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	public Servicee addService(Servicee s) {
		return serviceRepository.save(s);
	}
	
	public List<Servicee> findAllService(){
		return serviceRepository.findAll();
	}
	
	public Optional<Servicee> findServiceById(int id) {
		 return serviceRepository.findById(id);
		}

	public Servicee findServiceByName(String Service_Nm) {
	    return serviceRepository.findByName(Service_Nm);

	}
	
	public Servicee updateService(Servicee s) {
		return serviceRepository.save(s);
	}
	
	public void deleteService(int id) {
		serviceRepository.deleteById(id);
	}


	//fonction pour ajouter un serviceZone dans un service
	public ServiceZone addServiceZoneToService(int iServiceZoneId, ServiceZone newServiceZone) {
		Optional<Servicee> aServiceOptional = serviceRepository.findById(iServiceZoneId);

		if (aServiceOptional.isPresent()) {
			Servicee aService = aServiceOptional.get();
			aService.addServiceZone(newServiceZone);
			serviceRepository.save(aService);

			return newServiceZone;
		} else {
			throw new EntityNotFoundException("SiteGroup not found with id: " + iServiceZoneId);
		}
	}
	public StaffGroup addStaffGroupToService(int serviceId, StaffGroup newStaffGroup) {
		Optional<Servicee> optionalService = serviceRepository.findById(serviceId);
		if (optionalService.isPresent()) {
			Servicee service = optionalService.get();
			service.addStaffGroup(newStaffGroup);
			serviceRepository.save(service);

			return newStaffGroup;
		} else {
			throw new EntityNotFoundException("StaffGroup not found with id: " + serviceId);
		}
	}
	//fonction pour récuperer les serviceZones ajouté par un service
	public List<ServiceZone> getServiceZoneByService(int iServiceId) {
		if (iServiceId <= 0) { throw new IllegalArgumentException("Service ID must be positive."); }
		Optional<Servicee> aServiceOptional = serviceRepository.findById(iServiceId);

		if (aServiceOptional.isPresent()) {
			Servicee aService = aServiceOptional.get();
			return aService.getServicezone();
		}
		return Collections.emptyList();
	}

}


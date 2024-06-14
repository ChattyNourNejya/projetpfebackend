package iss4u.ehr.clinique_projet.settings.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import iss4u.ehr.clinique_projet.settings.entities.*;
import iss4u.ehr.clinique_projet.settings.repositories.LeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;



@org.springframework.stereotype.Service
public class ServicePlannerService {

	@Autowired
	LeServiceRepository LeserviceRepository;

	@Autowired
	public ServicePlannerService(LeServiceRepository LeserviceRepository) {
		this.LeserviceRepository = LeserviceRepository;
	}


	public LeService addService(LeService s) {
		return LeserviceRepository.save(s);
	}

	public List<LeService> findAllService(){
		return LeserviceRepository.findAll();
	}

	public Optional<LeService> findServiceById(int id) {
		 return LeserviceRepository.findById(id);
		}

	public LeService findServiceByName(String Service_Nm) {
	    return LeserviceRepository.findByName(Service_Nm);

	}

	public LeService updateService(LeService s) {
		return LeserviceRepository.save(s);
	}

	public void deleteService(int id) {
		LeserviceRepository.deleteById(id);
	}


	//fonction pour ajouter un serviceZone dans un service
	public ServiceZone addServiceZoneToService(int iServiceId, ServiceZone newServiceZone) {
		Optional<LeService> aServiceOptional = LeserviceRepository.findById(iServiceId);

		if (aServiceOptional.isPresent()) {
			LeService aService = aServiceOptional.get();
			aService.addServiceZone(newServiceZone);
			LeserviceRepository.save(aService);

			return newServiceZone;
		} else {
			throw new EntityNotFoundException("ServiceZone not found with id: " + iServiceId);
		}
	}
	public Staff addStaffToService(int serviceId, Staff newStaff) {

		Optional<LeService> optionalService = LeserviceRepository.findById(serviceId);

		if (optionalService.isPresent()) {
			LeService LeService = optionalService.get();


			LeserviceRepository.save(LeService);
			newStaff.setLeService(LeService);

			return newStaff;
		} else {
			throw new EntityNotFoundException("Staff not found with id: " + serviceId);
		}
	}

	// Method to add a StayRoom to a service
	public StayRoom addStayRoomToService(int iServiceId, StayRoom newStayRoom) {
		Optional<LeService> aServiceOptional = LeserviceRepository.findById(iServiceId);

		if (aServiceOptional.isPresent()) {
			LeService aService = aServiceOptional.get();
			aService.addStayRoom(newStayRoom); // Assuming addStayRoom method exists in LeService
			LeserviceRepository.save(aService);

			return newStayRoom;
		} else {
			throw new EntityNotFoundException("Service not found with id: " + iServiceId);
		}
	}

	// Method to retrieve StayRooms associated with a service
	public List<StayRoom> getStayRoomsByService(int iServiceId) {
		if (iServiceId <= 0) {
			throw new IllegalArgumentException("Service ID must be positive.");
		}

		Optional<LeService> aServiceOptional = LeserviceRepository
				.findById(iServiceId);

		if (aServiceOptional.isPresent()) {
			LeService leService = aServiceOptional.get();
			return leService.getStayRooms();
		}

		return Collections.emptyList();
	}


	//fonction pour récuperer les serviceZones ajouté par un service
	public List<ServiceZone> getServiceZoneByService(int iServiceId) {
		if (iServiceId <= 0) { throw new IllegalArgumentException("Service ID must be positive."); }
		Optional<LeService> aServiceOptional = LeserviceRepository.findById(iServiceId);

		if (aServiceOptional.isPresent()) {
			LeService LeService = aServiceOptional.get();
			return LeService.getServicezone();
		}
		return Collections.emptyList();
	}

}


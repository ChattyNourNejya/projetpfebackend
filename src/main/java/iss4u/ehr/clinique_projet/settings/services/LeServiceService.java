package iss4u.ehr.clinique_projet.settings.services;

import iss4u.ehr.clinique_projet.settings.entities.LeService;
import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import iss4u.ehr.clinique_projet.settings.repositories.LeServiceRepository;
import iss4u.ehr.clinique_projet.settings.repositories.StayRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeServiceService {

    private final LeServiceRepository leServiceRepository;

    private final StayRoomRepository stayRoomRepository ;

    @Autowired
    public LeServiceService(LeServiceRepository leServiceRepository, StayRoomRepository stayRoomRepository) {
        this.leServiceRepository = leServiceRepository;
        this.stayRoomRepository = stayRoomRepository;
    }

    public List<LeService> findAllService() {
        return leServiceRepository.findAll();
    }

    public LeService findServiceByName(String serviceName) {
        return leServiceRepository.findByName(serviceName);
    }

    public List<LeService> getServiceByName(String service_Nm) {
        return leServiceRepository.getLeServiceByserviceNm(service_Nm);
    }

    public LeService addService(LeService service) {
        return leServiceRepository.save(service);
    }

    public Optional<LeService> findServiceById(int serviceId) {
        return leServiceRepository.findById(serviceId);
    }

    public LeService updateService(LeService service) {
        return leServiceRepository.save(service);
    }

    public void deleteService(int id) {
        leServiceRepository.deleteById(id);
    }


}

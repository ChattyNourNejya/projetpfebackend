package iss4u.ehr.clinique_projet.settings.services;

import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import iss4u.ehr.clinique_projet.settings.repositories.StayRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StayRoomServiceImpl implements StayRoomService {

    @Autowired
    private StayRoomRepository stayRoomRepository;

    @Override
    public StayRoom addStayRoom(StayRoom stayRoom) {
        return stayRoomRepository.save(stayRoom);
    }

    @Override
    public List<StayRoom> findAllStayRooms() {
        return stayRoomRepository.findAll();
    }

    @Override
    public StayRoom findStayRoomByName(String StayRoom_Nm) {
        return stayRoomRepository.findByName(StayRoom_Nm);
    }

    @Override
    public Optional<StayRoom> findStayRoomById(long stayRoomId) {
        return stayRoomRepository.findById(stayRoomId);
    }

    @Override
    public StayRoom updateStayRoom(StayRoom stayRoom) {
        return stayRoomRepository.save(stayRoom);
    }

    @Override
    public void deleteStayRoom(long stayRoomId) {
        stayRoomRepository.deleteById(stayRoomId);
    }

    @Override
    public List<StayRoom> findStayRoomsByService(int service_ky) {
        return stayRoomRepository.findByService(service_ky);
    }



}

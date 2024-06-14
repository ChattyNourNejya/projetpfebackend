package iss4u.ehr.clinique_projet.settings.services;

import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import java.util.List;
import java.util.Optional;

public interface StayRoomService {

    StayRoom addStayRoom(StayRoom stayRoom);

    List<StayRoom> findAllStayRooms();

    StayRoom findStayRoomByName(String StayRoom_Nm);

    Optional<StayRoom> findStayRoomById(long stayRoomId);

    StayRoom updateStayRoom(StayRoom stayRoom);

    void deleteStayRoom(long stayRoomId);

    List<StayRoom> findStayRoomsByService(int service_ky);




}

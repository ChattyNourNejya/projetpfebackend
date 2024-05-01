package iss4u.ehr.clinique_projet.settings.services;


import iss4u.ehr.clinique_projet.settings.entities.Room;

public interface RoomService {
    Room saveRoom(Room room);

    Room getAllRoomGroupByLibelleAndType(String libelle, String type);

    Room updateRoom(Room room, long Room_Ky);

    void deleteRoom(long Room_Ky);

    Room findRoomById(long Room_Ky);

}

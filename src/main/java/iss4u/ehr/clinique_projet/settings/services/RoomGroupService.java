package iss4u.ehr.clinique_projet.settings.services;

import iss4u.ehr.clinique_projet.settings.entities.RoomGroup;

public interface RoomGroupService {
    RoomGroup saveRoomGroup(RoomGroup roomGroup);
    RoomGroup findRoomGroupByName(String name);
    RoomGroup updateRoomGroup(RoomGroup roomGroup, long RoomGroup_Ky);
    void deleteRoomGroup(long RoomGroup_Ky);
    RoomGroup findRoomGroupById(long RoomGroup_Ky);
}

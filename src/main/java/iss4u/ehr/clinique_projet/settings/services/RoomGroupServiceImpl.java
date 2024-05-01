package iss4u.ehr.clinique_projet.settings.services;

import iss4u.ehr.clinique_projet.exception.RoomGroupException;
import iss4u.ehr.clinique_projet.settings.entities.RoomGroup;
import iss4u.ehr.clinique_projet.settings.repositories.RoomGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomGroupServiceImpl implements RoomGroupService {

    @Autowired
    private RoomGroupRepository roomGroupRepository;

    @Override
    public RoomGroup saveRoomGroup(RoomGroup roomGroup) {
        return roomGroupRepository.save(roomGroup);
    }

    @Override
    public RoomGroup findRoomGroupByName(String name) {
        return roomGroupRepository.findRoomGroupNm(name);
    }

    @Override
    public RoomGroup updateRoomGroup(RoomGroup roomGroup, long RoomGroup_Ky) {
        RoomGroup existingRoomGroup = roomGroupRepository.findById(RoomGroup_Ky)
                .orElseThrow(() -> new RoomGroupException("RoomGroup not found with id: " + RoomGroup_Ky));

        existingRoomGroup.setRoomGroup_PrntKy(roomGroup.getRoomGroup_PrntKy());
        existingRoomGroup.setRoomGroup_Nm(roomGroup.getRoomGroup_Nm());

        return roomGroupRepository.save(existingRoomGroup);
    }

    @Override
    public void deleteRoomGroup(long roomGroupKy) {
        roomGroupRepository.findById(roomGroupKy).ifPresentOrElse(
                roomGroup -> roomGroupRepository.deleteById(roomGroupKy),
                () -> {
                    throw new RoomGroupException("RoomGroup not found with id: " + roomGroupKy);
                }
        );
    }

    @Override
    public RoomGroup findRoomGroupById(long RoomGroup_Ky) {
        return roomGroupRepository.findById(RoomGroup_Ky)
                .orElseThrow(() -> new RoomGroupException("RoomGroup not found with id: " + RoomGroup_Ky));
    }
}

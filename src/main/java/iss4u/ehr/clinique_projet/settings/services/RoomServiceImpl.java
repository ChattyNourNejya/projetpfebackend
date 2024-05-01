package iss4u.ehr.clinique_projet.settings.services;



import iss4u.ehr.clinique_projet.exception.RoomException;
import iss4u.ehr.clinique_projet.settings.entities.Room;
import iss4u.ehr.clinique_projet.settings.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }


    @Override
    public Room getAllRoomGroupByLibelleAndType(String libelle, String type) {
        return roomRepository.findByRoom_NmAndRoom_PrntKy(libelle, type);
    }


    @Override
    public Room updateRoom(Room room, long Room_Ky) {
        Room aUpdatedRoom = new Room();
        aUpdatedRoom.setRoom_Ky(Room_Ky);
        aUpdatedRoom.setRoom_PrntKy(room.getRoom_PrntKy());
        aUpdatedRoom.setRoom_Nm(room.getRoom_Nm());
        return roomRepository.save(aUpdatedRoom);
    }

        @Override
        public void deleteRoom(long Room_Ky) {
            roomRepository.findById(Room_Ky)
                    .orElseThrow(() -> new RoomException("Room not found with id: " + Room_Ky));
            roomRepository.deleteById(Room_Ky);
        }
    @Override
    public Room findRoomById(long Room_Ky) {
        return roomRepository.findById(Room_Ky)
                .orElseThrow(() -> new RoomException("Room", "Room_Ky", Room_Ky));
    }

}



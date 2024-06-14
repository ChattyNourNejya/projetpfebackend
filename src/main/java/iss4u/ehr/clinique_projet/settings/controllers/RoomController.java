package iss4u.ehr.clinique_projet.settings.controllers;

import iss4u.ehr.clinique_projet.exception.FunctionalUnitException;
import iss4u.ehr.clinique_projet.settings.entities.Room;
import iss4u.ehr.clinique_projet.settings.entities.RoomGroup;
import iss4u.ehr.clinique_projet.settings.services.RoomGroupServiceImpl;
import iss4u.ehr.clinique_projet.settings.services.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private RoomGroupServiceImpl roomGroupService;

    @PostMapping("{add-Room}")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        try {
            return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        // Ajouter une nouvelle salle à un groupe de salles existant
    @PostMapping("/add-room-to-group-of-rooms/{roomGroupId}")
    public ResponseEntity<Room> saveRoomToGroup(@RequestBody Room room, @PathVariable("roomGroupId") long iRoomGroupId) {
        //  Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (iRoomGroupId<= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Trouver le groupe de salles correspondant à l'ID fourni
        RoomGroup aRoomGroup = roomGroupService.findRoomGroupById(iRoomGroupId);

        // Définir le groupe de salles pour la nouvelle salle
        room.setRoomGroup(aRoomGroup);

        // Enregistrer la nouvelle salle et retourner une réponse
        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
    }

    //Méthode pour récupérer les salles associées à un groupe de salles enregistrées dans la base de donnéesde filtrer les salles par libellé et type.
    @GetMapping("/roomGroups/{roomGroupId}")
    public ResponseEntity<List<Room>> getRoomsByRoomGroup(
            @PathVariable("roomGroupId") long iRoomGroup_Ky)
          {

        // Récupérer les salles associées à un groupe de salles par son ID
        List<Room> aRooms = roomGroupService.findRoomGroupById(iRoomGroup_Ky).getRooms();
        return new ResponseEntity<>(aRooms, HttpStatus.OK);
    }

    @GetMapping("{retreive-Room}")

    public ResponseEntity<Room> getRoomByLibelleAndType(@RequestParam("libelle") String libelle, @RequestParam("type") String type) {
        try {
            Room aRoom = roomService.getAllRoomGroupByLibelleAndType(libelle, type);
            return new ResponseEntity<>(aRoom, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{Room_Ky}")
    public ResponseEntity<Room> getRoomById(@PathVariable("Room_Ky") long Room_Ky) {
        // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (Room_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Room aRoom;
        try {
            // Recherche de la chambre par son ID
            aRoom = roomService.findRoomById(Room_Ky);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(aRoom, HttpStatus.OK);
    }

    @PutMapping("/{Room_Ky}")
    public ResponseEntity<Room> updateRoom(@PathVariable("Room_Ky") long Room_Ky, @RequestBody Room room) {
        try {
            // Vérification de la clé de la salle : si elle est inférieure ou égale à zéro, la requête est incorrecte
            if (Room_Ky <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Recherche de la chambre par son ID
            Room aRoom = roomService.findRoomById(Room_Ky);

            return new ResponseEntity<>(aRoom, HttpStatus.OK);
        } catch (Exception e) {
            // Gérer les exceptions générales
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") long Room_Ky) {
        // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (Room_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            roomService.deleteRoom(Room_Ky);
            return ResponseEntity.ok("FunctionalUnit deleted successfully.");
        } catch (FunctionalUnitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete FunctionalUnit: " + e.getmessage());

        }
    }


}

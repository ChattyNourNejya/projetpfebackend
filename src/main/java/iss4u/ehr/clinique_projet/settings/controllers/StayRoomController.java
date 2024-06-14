package iss4u.ehr.clinique_projet.settings.controllers;

import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.entities.StayRoom;
import iss4u.ehr.clinique_projet.settings.services.StayRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stayRoom")
public class StayRoomController {

    @Autowired
    private StayRoomService stayRoomService;

    public StayRoomController() {
    }

    public StayRoomController(StayRoomService stayRoomService) {
        this.stayRoomService = stayRoomService;
    }

    @GetMapping("/service/{service_ky}")
    public List<StayRoom> getStayRoomsForService(@PathVariable int service_ky) {
        try {
            return stayRoomService.findStayRoomsByService(service_ky);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des stay rooms pour le service " + service_ky, e);
        }
    }


    @GetMapping()
    public List<StayRoom> all() {
        try {
            return stayRoomService.findAllStayRooms();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des stay rooms", e);
        }
    }

    @GetMapping("/name/{stayRoomName}")
    public StayRoom findStayRoomByName(@PathVariable String stayRoomName) {
        try {
            return stayRoomService.findStayRoomByName(stayRoomName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération par nom des stay rooms", e);
        }
    }


    @GetMapping("/{stayRoomId}")
    public ResponseEntity<StayRoom> findStayRoomById(@PathVariable long stayRoomId) {
        try {
            Optional<StayRoom> stayRoomOptional = stayRoomService.findStayRoomById(stayRoomId);
            if (stayRoomOptional.isPresent()) {
                return ResponseEntity.ok(stayRoomOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération par id des stay rooms", e);
        }
    }

    @PostMapping()
    public StayRoom newStayRoom(@RequestBody StayRoom stayRoom) {
        try {
            return stayRoomService.addStayRoom(stayRoom);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de l'ajout du stay room", e);
        }
    }


    @PutMapping("/{stayRoomId}")
    public StayRoom updateStayRoom(@RequestBody StayRoom stayRoom, @PathVariable long stayRoomId) {
        try {
            stayRoom.setStayRoom_Ky(stayRoomId);
            return stayRoomService.updateStayRoom(stayRoom);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la modification du stay room", e);
        }
    }

    @DeleteMapping("/{stayRoomId}")
    public void deleteStayRoom(@PathVariable long stayRoomId) {
        try {
            stayRoomService.deleteStayRoom(stayRoomId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la suppression du stay room", e);
        }
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}

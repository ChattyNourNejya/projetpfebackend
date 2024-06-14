package iss4u.ehr.clinique_projet.settings.controllers;


import iss4u.ehr.clinique_projet.exception.FunctionalUnitException;
import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import iss4u.ehr.clinique_projet.settings.entities.RoomGroup;
import iss4u.ehr.clinique_projet.settings.services.FunctionalUnitServiceImpl;
import iss4u.ehr.clinique_projet.settings.services.RoomGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/RoomGroup")
@CrossOrigin(origins = "*")
public class RoomGroupController {
    @Autowired
    private RoomGroupServiceImpl roomGroupService;

    @Autowired
    private FunctionalUnitServiceImpl functionalUnitService;

    @PostMapping("/{add-RoomGroup}")
    public ResponseEntity<RoomGroup> saveRoomGroup(@RequestBody RoomGroup roomGroup) {
        try {
            return new ResponseEntity<>(roomGroupService.saveRoomGroup(roomGroup), HttpStatus.CREATED);
        } catch (Exception e) {
            // Gérer les exceptions en retournant une réponse avec un code d'erreur interne du serveur
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @GetMapping("/{name}")
    public RoomGroup getRoomGroupNameByNm(@PathVariable String name) {
        try {
            return roomGroupService.findRoomGroupByName(name);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomGroup> getRoomGroupById(@PathVariable("id") long RoomGroup_Ky) {
        // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (RoomGroup_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            RoomGroup aRoomGroup = roomGroupService.findRoomGroupById(RoomGroup_Ky);
            if (aRoomGroup != null) {
                return new ResponseEntity<>(aRoomGroup, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Gérer les exceptions en retournant une réponse avec un code d'erreur interne du serveur
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}


        // Ajouter d’un nouveau groupe de salles à une unité d’exploitation existante.
    @PostMapping("/add-RoomGroup/{idFunctionalUnit}")
    public ResponseEntity<RoomGroup> saveRoomGroup(@RequestBody RoomGroup roomGroup, @PathVariable("idFunctionalUnit") long iFunctionalUnit_Ky) {
        try {
        // Vérification de l'ID de l'unité fonctionnelle : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (iFunctionalUnit_Ky<= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Trouver l'unité fonctionnelle correspondant à l'ID fourni
        FunctionalUnit functionalUnit = functionalUnitService.findFunctionalUnitById(iFunctionalUnit_Ky);

        // Vérifier si l'unité fonctionnelle existe
        if (functionalUnit == null) {
            // Gérer le cas où l'unité fonctionnelle n'est pas trouvée
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Ajouter le groupe de salles à l'unité fonctionnelle
        roomGroup.setFunctionalUnit(functionalUnit);

        // Enregistrer le groupe de salles et retourner la réponse
        RoomGroup aSavedRoomGroup = roomGroupService.saveRoomGroup(roomGroup);
        return new ResponseEntity<>(aSavedRoomGroup, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        // Gérer les autres exceptions en retournant une réponse avec un code d'erreur interne du serveur
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    // Méthode pour obtenir  les groupes de salles associés par le nom d'une unité fonctionnelle.
    @GetMapping("/{functionalUnitName}/roomGroups")
    public List<RoomGroup> getRoomGroupsByFunctionalUnitName(@PathVariable("functionalUnitName") String iFunctionalUnit_Nm) {
        try {
            // Trouver l'unité fonctionnelle correspondant au nom fourni
            FunctionalUnit aFunctionalUnit = functionalUnitService.findFunctionalUnitNameByNm(iFunctionalUnit_Nm);

            // Récupérer les groupes de salles associés à cette unité fonctionnelle
            return aFunctionalUnit.getRoomList();
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite lors de la recherche des groupes de salles par nom d'unité fonctionnelle : " + e.getMessage());
            return Collections.emptyList(); // Retourner une liste vide en cas d'erreur
        }
    }
/*
    @GetMapping("/functionalUnits-by-serviceZone/{serviceZoneKey}")
    public List<FunctionalUnit> getFunctionalUnitsByServiceZone(@PathVariable int serviceZoneKey) {
        try {
            if (serviceZoneKey <= 0) {
                throw new IllegalArgumentException("Service Zone Key must be positive.");
            }

            ServiceZone aServiceZone = serviceZoneService.findServiceZoneById(serviceZoneKey).orElse(null);

            return aServiceZone.getFunctionalUnits();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des unités fonctionnelles pour la zone de service.", e);
        }
    }
 */

    @GetMapping("/roomGroups/{functionalUnitKey}")
    public List<RoomGroup> getRoomGroupsByFunctionalUnit(@PathVariable("functionalUnitKey") int ifunctionalUnitKey) {
        try {
            // Trouver l'unité fonctionnelle correspondant au id fourni
            FunctionalUnit aFunctionalUnit = functionalUnitService.findFunctionalUnitById(ifunctionalUnitKey);

            // Récupérer les groupes de salles associés à cette unité fonctionnelle
            return aFunctionalUnit.getRoomList();
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite lors de la recherche des groupes de salles par nom d'unité fonctionnelle : " + e.getMessage());
            return Collections.emptyList(); // Retourner une liste vide en cas d'erreur
        }
    }

    @PutMapping("/{RoomGroup_Ky}")
    public ResponseEntity<RoomGroup> updateRoomGroup(@PathVariable("RoomGroup_Ky") long RoomGroup_Ky, @RequestBody RoomGroup roomGroup) {
        // Vérification de l'ID du groupe de salles : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (RoomGroup_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RoomGroup aUpdatedRoomGroup;
        try {
            aUpdatedRoomGroup = roomGroupService.updateRoomGroup(roomGroup, RoomGroup_Ky);
        } catch (Exception e) {
            // Gérer les erreurs internes du serveur
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(aUpdatedRoomGroup);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomGroup(@PathVariable("id") long RoomGroup_Ky) {
        // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (RoomGroup_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            roomGroupService.deleteRoomGroup(RoomGroup_Ky);
            return ResponseEntity.ok("RoomGroup deleted successfully.");
        } catch (FunctionalUnitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete RoomGroup: " + e.getmessage());
        }
    }

}
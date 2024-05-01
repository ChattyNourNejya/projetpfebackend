package iss4u.ehr.clinique_projet.settings.controllers;

import iss4u.ehr.clinique_projet.exception.FunctionalUnitException;
import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import iss4u.ehr.clinique_projet.settings.entities.ServiceZone;
import iss4u.ehr.clinique_projet.settings.services.FunctionalUnitServiceImpl;
import iss4u.ehr.clinique_projet.settings.services.ServiceZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functionalUnit")
@CrossOrigin(origins = "*")
public class FunctionalUnitController {

    @Autowired
    private FunctionalUnitServiceImpl functionalUnitService;

    @Autowired
    private ServiceZoneService serviceZoneService;


    @GetMapping("/all")
    public List<FunctionalUnit> findAllFunctionalUnit() {
        try {
            return functionalUnitService.findAllFunctionalUnit();
        } catch (Exception e) {
            // Gérer les exceptions générales
            throw new RuntimeException("Une erreur s'est produite lors de la récupération de toutes les unités fonctionnelles.", e);
        }
    }

    @GetMapping("/functionalunitnames")
    public List<String> getAllFunctionalUnitNames() {
        try {
            return functionalUnitService.findAllFunctionalUnitNames();
        } catch (Exception e) {
            // Gérer les exceptions générales
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des noms des unités fonctionnelles.", e);
        }
    }


    // Ajouter une nouvelle unité d’exploitation à une zone de services existante.
    @PostMapping("/add-functionalUnit/{idSrvZone}")
    public ResponseEntity<FunctionalUnit> saveFunctionalUnit(@RequestBody FunctionalUnit functionalUnit , @PathVariable("idSrvZone") int idSrvZone) {
        try {
            // Vérifier si l'ID de la zone de service est valide (supérieur à zéro)
            if (idSrvZone <= 0) {
            throw new IllegalArgumentException("L'ID de la zone de service doit être positif.");
        }

        // Trouver la zone de service correspondant à l'idSrvZone fourni
        ServiceZone aServiceZone = serviceZoneService.findServiceZoneById(idSrvZone).orElse(null);

        // Définir la zone de service pour l'unité fonctionnelle
        functionalUnit.setServiceZone(aServiceZone);

        // Retourner une réponse contenant l'unité fonctionnelle enregistrée
        return new ResponseEntity<>(functionalUnitService.saveFunctionalUnit(functionalUnit), HttpStatus.CREATED);
    } catch (Exception e) {
        // Gérer les exceptions générales
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }



    // Méthode pour obtenir les unités fonctionnelles par nom de zone de service
    @GetMapping("/{zoneName}/functionalunits")
    public List<FunctionalUnit> getFunctionalUnitsByServiceZoneName(@PathVariable("zoneName") String zoneName) {
        try {
            // Trouver la zone de service correspondant au nom de zone fourni
            ServiceZone aServiceZone = serviceZoneService.findServiceZoneByName(zoneName);
            // Retourner la liste des unités fonctionnelles de la zone de service
            return aServiceZone.getFunctionalUnits();
        } catch (Exception e) {
            throw new RuntimeException("Une erreur s'est produite lors de la récupération des unités fonctionnelles pour la zone de service.", e);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<FunctionalUnit> getFunctionalUnitById(@PathVariable("id") long FunctionalUnit_Ky) {

        try {
            // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
            if (FunctionalUnit_Ky <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            // Rechercher l'unité fonctionnelle par son ID
            FunctionalUnit aFunctionalUnit = functionalUnitService.findFunctionalUnitById(FunctionalUnit_Ky);

            return new ResponseEntity<>(aFunctionalUnit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{add-functionalUnit}")
    public ResponseEntity<FunctionalUnit> saveFunctionalUnit(@RequestBody FunctionalUnit functionalUnit) {
        try {
            return new ResponseEntity<>(functionalUnitService.saveFunctionalUnit(functionalUnit), HttpStatus.CREATED);
        } catch (Exception e) {
            // Gérer les exceptions générales
            throw new RuntimeException("Une erreur s'est produite lors de l'enregistrement de l'unité fonctionnelle.", e);
        }
    }

    @GetMapping("/{name}")
    public FunctionalUnit getFunctionalUnitNameByNm(@PathVariable String name) {
        try {
            return functionalUnitService.findFunctionalUnitNameByNm(name);
        } catch (Exception e) {
            // Gérer les exceptions générales
            throw new RuntimeException("Une erreur s'est produite lors de la récupération de l'unité fonctionnelle avec le nom spécifié.", e);
        }
    }


    @PutMapping("/{FunctionalUnit_Ky}")
    public ResponseEntity<FunctionalUnit> updateFunctionalUnit(@PathVariable("FunctionalUnit_Ky") long FunctionalUnit_Ky, @RequestBody FunctionalUnit functionalUnit) {
        // Vérifier si la clé primaire est valide (supérieure à zéro)
        if (FunctionalUnit_Ky <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        FunctionalUnit aUpdatedFunctionalUnit;
        try {
            // Mettre à jour l'unité fonctionnelle avec la nouvelle clé primaire
            aUpdatedFunctionalUnit = functionalUnitService.updateFunctionalUnit(functionalUnit, FunctionalUnit_Ky);
        } catch (Exception e) {
            // Gérer les erreurs internes du serveur
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(aUpdatedFunctionalUnit, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFunctionalUnit(@PathVariable("id") long FunctionalUnit_Ky) {
        // Vérification de l'ID : s'il est inférieur ou égal à zéro, la requête est incorrecte
        if (FunctionalUnit_Ky <= 0) {
            return ResponseEntity.badRequest().body("Invalid FunctionalUnit ID.");
        }

        try {
            functionalUnitService.deleteFunctionalUnit(FunctionalUnit_Ky);
            return ResponseEntity.ok("FunctionalUnit deleted successfully.");
        } catch (FunctionalUnitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete FunctionalUnit: " + e.getMessage());
        }
    }
}




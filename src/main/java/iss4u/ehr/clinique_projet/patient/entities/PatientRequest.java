package iss4u.ehr.clinique_projet.patient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class PatientRequest {
    @JsonProperty("patient")
    private Patient patient;


}

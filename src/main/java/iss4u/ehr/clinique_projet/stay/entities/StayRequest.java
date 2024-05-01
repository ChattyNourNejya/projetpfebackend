package iss4u.ehr.clinique_projet.stay.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class StayRequest {
    @JsonProperty("stay")
    private Stay stay;
}

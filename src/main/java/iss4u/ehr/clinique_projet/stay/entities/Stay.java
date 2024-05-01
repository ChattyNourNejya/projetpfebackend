package iss4u.ehr.clinique_projet.stay.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.settings.entities.Servicee;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Stay")
@EntityScan
public class Stay {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "Stay_Ky")
    @JsonProperty("stay_ky")
    private Long stayKy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "Staypatient")
    @JoinColumn(name = "Patient_Id")
    @JsonProperty("stay_patient")
    private Patient stayPrntPatient;



    @ManyToMany
    @JsonIgnoreProperties(value = "stays")
    @JoinTable(
            name = "stay_insurance",
            joinColumns = @JoinColumn(name = "stay_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )

    @JsonProperty("insurances")
    private List<Insurance> insurances = new ArrayList<>();

    @OneToMany(mappedBy = "stay",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Stayservice")
    @JsonProperty("stay_pertinent_service")
   // @JsonIgnoreProperties(value ="stay")
    private List<Servicee> stayPertinentService= new ArrayList<>();

    public void addStayPertinentService(Servicee newService) {
        stayPertinentService.add(newService);
        newService.setStay(this);
    }

    @Column(name = "stay_emergency_contact")
    @JsonProperty("stay_emergency_contact")
    private String stayEmergencyContact;

    @Column(name = "stay_type")
    @JsonProperty("stay_type")
    private Stay_type stayType;

    @Column(name = "stay_family_doctor")
    @JsonProperty("stay_family_doctor")
    private String stayFamilyDoctor;

    @Column(name = "stay_previsional_begin")
    @JsonProperty("stay_previsional_begin")
    private Date stayPrevisionalBegin;

    @Column(name = "stay_previsional_end")
    @JsonProperty("stay_previsional_end")
    private Date stayPrevisionalEnd;

    @Column(name = "stay_status")
    @JsonProperty("stay_status")
    private Stay_Status stayStatus;

    @Column(name = "stay_note")
    @JsonProperty("stay_note")
    private String stayNote;


    public void setPatient(Patient patient) {
        this.stayPrntPatient = patient;
    }



}
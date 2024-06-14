package iss4u.ehr.clinique_projet.stay.entities;

import com.fasterxml.jackson.annotation.*;
import iss4u.ehr.clinique_projet.insurance.entities.Insurance;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.settings.entities.LeService;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "stay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Stayservice")
    @JsonProperty("stay_pertinent_service")
    @JsonIgnoreProperties(value = "stay")
    private List<LeService> stayPertinentService = new ArrayList<>();

    public void addStayPertinentService(LeService newService) {
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

    @Column(name = "stay_family_doctor_phone")
    @JsonProperty("stay_family_doctor_phone")
    private Long stayFamilyDoctorPhone;

    @Column(name = "stay_family_doctor_email")
    @JsonProperty("stay_family_doctor_email")
    private String stayFamilyDoctorEmail;

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

    @Override
    public String toString() {
        return "Stay{" +
                "stayKy=" + stayKy +
                ", stayPrntPatient=" + stayPrntPatient +
                ", insurances=" + insurances +
                ", stayPertinentService=" + stayPertinentService +
                ", stayEmergencyContact='" + stayEmergencyContact + '\'' +
                ", stayType=" + stayType +
                ", stayFamilyDoctor='" + stayFamilyDoctor + '\'' +
                ", stayFamilyDoctorEmail='" + stayFamilyDoctorEmail + '\'' +
                ", stayFamilyDoctorPhone=" + stayFamilyDoctorPhone +
                ", stayPrevisionalBegin=" + stayPrevisionalBegin +
                ", stayPrevisionalEnd=" + stayPrevisionalEnd +
                ", stayStatus=" + stayStatus +
                ", stayNote='" + stayNote + '\'' +

                '}';
    }
}

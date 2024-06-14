package iss4u.ehr.clinique_projet.MedicalAct.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedicalAct_Ky")
    private int MedicalAct_Ky;


    @Column(name = "MedicalAct_Name")
    private String MedicalAct_Name;

    @Column(name = "MedicalAct_Prepation")
    private String MedicalAct_Prepation;

    @Column(name = "MedicalAct_Consigne")
    private String MedicalAct_Consigne;

    @Column(name = "MedicalAct_Remarque")
    private String MedicalAct_Remarque;

    @Column(name = "MedicalAct_Type")
    private MedicalActType MedicalAct_Type;


    @OneToMany(mappedBy = "MedVisitSchdld_MedActKy", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference("MedVisitSchdlds")
    @Getter
    @Setter
    private List<MedVisitSchdld> medVisitSchdlds;


}



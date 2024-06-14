package iss4u.ehr.clinique_projet.patient.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="Patient")
public class Patient  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Patient_Ky",nullable = false,unique = true)
    private int patientKy;

    @Column(name = "Patient_LastName",nullable = false)
    private String patientLastName;

    @Column(name = "Patient_FirstName",nullable = false)
    private String patientFirstName;

    @Column(name = "Patient_MiddleName")
    private String patientMiddleName;

    @Column(name = "Patient_MaritalStatus")
    private MaritalStatus patientMaritalStatus;

    @Column(name = "Patient_BirthDate")
    private Date patientBirthDate;

    @Column(name = "Patient_Gender")
    private Gender patientGender;

    @Column(name = "Patient_IdentityNumber")
    private String patientIdentityNumber;

    @Column(name = "Patient_IdentityType")
    private IdentityType patientIdentityType;

    @Column(name = "Patient_Nationality")
    private String patientNationality;

    @Column(name = "Patient_DeathDate")
    private Date patientDeathDate;

    @Column(name = "Patient_DeathRemarks")
    private String patientDeathRemarks;



    @Column(name = "Patient_Remarks")
    private String patientRemarks;

    @Column(name = "Patient_Status")
    private PatientStatus patientStatus;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.LAZY)
    @JsonManagedReference(value ="phonePatient")
    private List<Phone> phones;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.LAZY)
    @JsonManagedReference(value = "adressPatient")
    private List<Address> addresses;

    @OneToMany(mappedBy = "MedVisitSchdld_PatientKy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("MedVisitSchdldPatient")
    private List<MedVisitSchdld> medVisitSchdlds;


    public void addMedVisitSchdld(MedVisitSchdld medVisitSchdld) {
        medVisitSchdlds.add(medVisitSchdld);
        medVisitSchdld.setMedVisitSchdld_PatientKy(this);
    }

    public List<MedVisitSchdld> getMedVisitSchdlds() {
        return medVisitSchdlds;
    }

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.LAZY)
    @JsonManagedReference(value = "EmailPatient")
    private List<Email> emails;
    @OneToMany(mappedBy = "stayPrntPatient", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference(value = "Staypatient")
    private List<Stay> stays;

    // fonction pour l'ajout d'un stay dans un patient
    public void addStay(Stay newStay) {
        stays.add(newStay);
        newStay.setPatient(this);
    }

    public List<Stay> getStay() {
        return stays;
    }
}
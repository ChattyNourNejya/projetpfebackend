package iss4u.ehr.clinique_projet.patient.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Phone_Ky")
    private int phoneKy;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Phone_PrntType")
    private PhonePrntType phonePrntType;



    @Column(name = "Phone_Primary")
    private boolean phonePrimary;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Phone_Country")
    private Country phoneCountry;

    @Column(name = "Phone_Professional")
    private boolean phoneProfessional;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Phone_Type")
    private PhoneType phoneType;

    @Column(name = "Phone_ReceiveSMS")
    private boolean phoneReceiveSMS;


    //Phone Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference("phonePatient")
    @JoinColumn(name = "Patient_Id")
    private Patient patient;

}

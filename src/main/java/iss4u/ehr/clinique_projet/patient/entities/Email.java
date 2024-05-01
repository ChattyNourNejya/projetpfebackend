package iss4u.ehr.clinique_projet.patient.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="Email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Email_Ky")
    private int emailKy;

   // @Enumerated(EnumType.STRING)
    @Column(name = "Email_PrntType")
    private EmailPrntType emailPrntType;



    @Column(name = "Email_Primary")
    private boolean emailPrimary;

    @Column(name = "Email_Value")
    private String emailValue;


    //Email Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference("EmailPatient")
    @JoinColumn(name = "Patient_Id")
    private Patient patient;
}

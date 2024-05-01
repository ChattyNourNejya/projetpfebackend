package iss4u.ehr.clinique_projet.patient.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name = "User_Ky", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKy;


    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "First_Name", nullable = false)
    private String firstName;



    @Column(name = "User_Id", nullable = false, unique = true) // unique identifier (ssn)
    private String userId;

    @Column(name = "Id_Type", nullable = false)
    private String Id_Type;

    @Column(name = "Maiden_Name")
    private String maidenName;

    @Column(name = "Birth_Date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "civil_status", nullable = false)
    private String civilStatus;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "User_Role", nullable = false)
    private String userRole;

    @Column(name="User_Status", nullable=false)
    private Integer userStatus ;


}

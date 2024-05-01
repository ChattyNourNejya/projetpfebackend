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
@Table(name="Address")
public class  Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Address_Ky")
    private int addressKy;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Address_PrntType")
    private AddressPrntType addressPrntType;



    @Column(name = "Address_Primary")
    private boolean addressPrimary;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Address_Type")
    private AddressType addressType;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Address_Valid")
    private AddressStatus addressValid;

    @Column(name = "Address_StreetNumber")
    private int addressStreetNumber;

    @Column(name = "Address_StreetLabel")
    private String addressStreetLabel;

    @Column(name = "Address_AvenueLabel")
    private String addressAvenueLabel;

    @Column(name = "Address_PostalCode")
    private String addressPostalCode;

    @Column(name = "Address_ApartmentNumber")
    private int addressApartmentNumber;

    @Column(name = "Address_BuildingLabel")
    private String addressBuildingLabel;

    @Column(name = "Address_ResidenceLabel")
    private String addressResidenceLabel;

    @Column(name = "Address_Details")
    private String addressDetails;

    @Column(name = "Address_City")
    private String addressCity;

    //@Enumerated(EnumType.STRING)
    @Column(name = "Address_Country")
    private Country addressCountry;


    //Address Relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference("adressPatient")
    @JoinColumn(name = "Patient_Id")
    private Patient patient;
}

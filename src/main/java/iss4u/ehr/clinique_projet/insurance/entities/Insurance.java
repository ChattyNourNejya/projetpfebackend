package iss4u.ehr.clinique_projet.insurance.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Insurance")
public class Insurance{
    @Id
    @Column(name="Insurance_Ky", nullable=false, unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insKy;

    @Column(name = "insurance_name", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Default Insurance Name'")
    private String insNm;
    @Column(name="Insurance_CompanyNumber", nullable=false)
    private String insNumber ;

    @Column(name="Insurance_PolicyNumber", nullable=false)
    private String policyNum ;
    @Column(name="Insurance_PolicyName", nullable=false)
    private String policyNm ;
    @Column(name="Insurance_WebsiteLink", nullable=false)
    private String website ;

    @Column(name="Insurance_PolicyType", nullable=false)
    private PolicyType policyType;
    @Column(name="Insurance_ContactName", nullable=false)
    private String insContactNm;
    @Column(name="Insurance_ContactEmail", nullable=false)
    private String insContactEml;
    @Column(name="Insurance_ContactPhone", nullable=false)
    private String  insContactPhone ;
    //
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Insurance_UnxTmCrt", nullable=true)
    private Date insuranceUnxTmCrt ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Insurance_UnxTmUpdt", nullable = true)
    private Date insuranceUnxTmUpdt;

    @Column(name = "insurance_rcrd_sts", nullable = true)
    private Integer insuranceRcrdSts;

    @ManyToMany(mappedBy = "insurances")
    @JsonIgnoreProperties(value = "insurances")
    private List<Stay> stays;


    @PrePersist
    protected void onCreate() {
        if (insuranceUnxTmCrt == null) {
            insuranceUnxTmCrt = new Date();
        }
        if (insuranceUnxTmUpdt == null) {
            insuranceUnxTmUpdt = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (insuranceUnxTmUpdt == null) {
            insuranceUnxTmUpdt = new Date();
        }
    }


    public Long getInsKy() {
        return insKy;
    }




}

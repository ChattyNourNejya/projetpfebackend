package iss4u.ehr.clinique_projet.appointment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import iss4u.ehr.clinique_projet.MedicalAct.entities.MedicalAct;
import iss4u.ehr.clinique_projet.patient.entities.Patient;
import iss4u.ehr.clinique_projet.settings.entities.Staff;
import lombok.*;

import jakarta.persistence.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MedVisitSchdld {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MedVisitSchdld_Ky")
  @Getter
  @Setter
  private int MedVisitSchdld_Ky;

  @ManyToOne
  @JsonBackReference("MedVisitSchdldPatient")
  @JoinColumn(name = "Patient_Ky", nullable = false)
  @Getter
  @Setter
  private Patient MedVisitSchdld_PatientKy;

  @OneToOne(mappedBy = "medVisitSchdld", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonBackReference("medVisitSchdld-staff")
  private Staff staff;


  @ManyToOne
  @JsonBackReference("MedVisitSchdlds")
  @JoinColumn(name = "MedActKy")
  @Getter
  @Setter
  private MedicalAct MedVisitSchdld_MedActKy;

  @Column(name = "MedVisitSchld_UnxTmBgn")
  @Getter
  @Setter
  private Date MedVisitSchld_UnxTmBgn;

  @Column(name = "MedVisitSchdld_UnxTmEnd")
  @Getter
  @Setter
  private Date MedVisitSchdld_UnxTmEnd;

  @Enumerated(EnumType.STRING)
  @Column(name = "MedVisitSchdld_Status")
  @Getter
  @Setter
  private MedVisitStatus MedVisitSchdld_Status;


  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
    if (staff != null) {
      staff.setMedVisitSchdld(this);
    }
  }

  @Override
  public String toString() {
    return "MedVisitSchdld{" +
            "MedVisitSchdld_Ky=" + MedVisitSchdld_Ky +
            ", MedVisitSchdld_PatientKy=" + (MedVisitSchdld_PatientKy) +
            ", MedVisitSchdld_MedActKy=" + (MedVisitSchdld_MedActKy ) +
            ", MedVisitSchld_UnxTmBgn=" + MedVisitSchld_UnxTmBgn +
            ", MedVisitSchdld_UnxTmEnd=" + MedVisitSchdld_UnxTmEnd +
            ", MedVisitSchdld_Status=" + MedVisitSchdld_Status +
            ", Staff="  + staff+
            '}';
  }

}
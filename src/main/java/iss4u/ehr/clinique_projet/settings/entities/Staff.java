package iss4u.ehr.clinique_projet.settings.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.appointment.entities.MedVisitSchdld;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Staff_Ky;
	private String Staff_Nm ;
	private String Staff_FrstNm ;

	private Stafff_Status Staff_status;

	@OneToOne
	@JoinColumn(name = "medVisitSchdld_id", nullable = false)
	@JsonManagedReference("medVisitSchdld-staff")
	private MedVisitSchdld medVisitSchdld;

	@Enumerated(EnumType.STRING)
	private StaffRole StaffJob;

	public MedVisitSchdld getMedVisitSchdld() {
		return medVisitSchdld;
	}

	public void setMedVisitSchdld(MedVisitSchdld medVisitSchdld) {
		this.medVisitSchdld = medVisitSchdld;
		if (medVisitSchdld != null) {
			medVisitSchdld.setStaff(this);
		}
	}
	@ManyToOne
	private Site site ;

	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonBackReference(value = "serviceStaff")
	private LeService leService;




	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}





	public int getStaff_Ky() {
		return Staff_Ky;
	}
	public void setStaff_Ky(int staff_Ky) {
		Staff_Ky = staff_Ky;
	}
	public   String getStaff_Nm() {
		return Staff_Nm;
	}
	public void setStaff_Nm(String staff_Nm) {
		Staff_Nm = staff_Nm;
	}
	public String getStaff_FrstNm() {
		return Staff_FrstNm;
	}
	public void setStaff_FrstNm(String staff_FrstNm) {
		Staff_FrstNm = staff_FrstNm;
	}

	public LeService getLeService() {
		return leService;
	}

	public void setLeService(LeService leService) {
		this.leService = leService;
	}

	public Site getStaff_PrntKy() {
		return site;
	}
	public void setStaff_PrntKy(Site site) {
		this.site = site;
	}


	public Stafff_Status getStaff_status() {
		return Staff_status;
	}

	public void setStaff_status(Stafff_Status staff_status) {
		Staff_status = staff_status;
	}

	public StaffRole getStaffJob() {
		return StaffJob;
	}

	public void setStaffJob(StaffRole staffJob) {
		StaffJob = staffJob;
	}

	public Staff() {
		super();

	}


	@Override
	public String toString() {
		return "Staff{" +
				"Staff_Ky=" + Staff_Ky +
				", Staff_Nm='" + Staff_Nm + '\'' +
				", Staff_FrstNm='" + Staff_FrstNm + '\'' +
				", Staff_status=" + Staff_status +
				", site=" + site +
				", Service=" + leService +
				", staffjob=" + StaffJob +
				'}';
	}
}

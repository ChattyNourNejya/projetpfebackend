package iss4u.ehr.clinique_projet.settings.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Staff_Ky;
	private String Staff_Nm ;
	private String Staff_FrstNm ;
	private String Staff_UsrId ;
	private String Staff_Color ;
	private Boolean Staff_status;
	@ManyToOne
	private Site site ;

	@ManyToMany
	@JoinTable(
	    name = "StaffGroupLnk",
	    joinColumns = @JoinColumn(name = "StaffGroupLnk_PrntStaffGroup"),
	    inverseJoinColumns = @JoinColumn(name = "StaffGroupLnk_PrntStaff")
	)

    private Set<StaffGroup> groups = new HashSet<>();

	public Set<StaffGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<StaffGroup> groups) {
		this.groups = groups;
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
	public String getStaff_UsrId() {
		return Staff_UsrId;
	}
	public void setStaff_UsrId(String staff_UsrId) {
		Staff_UsrId = staff_UsrId;
	}
	public String getStaff_Color() {
		return Staff_Color;
	}
	public void setStaff_Color(String staff_Color) {
		Staff_Color = staff_Color;
	}
	public Site getStaff_PrntKy() {
		return site;
	}
	public void setStaff_PrntKy(Site site) {
		this.site = site;
	}

	public Boolean getStaff_status() {
		return Staff_status;
	}

	public void setStaff_status(Boolean staff_status) {
		Staff_status = staff_status;
	}

	public Staff() {
		super();

	}
	public Staff(int staff_Ky, String staff_Nm, String staff_FrstNm, String staff_UsrId, String staff_Color,
			Site site, List<StaffGroup> staffgroup, Boolean staff_status) {
		super();
		this.Staff_Ky = staff_Ky;
		this.Staff_Nm = staff_Nm;
		this.Staff_FrstNm = staff_FrstNm;
		this.Staff_UsrId = staff_UsrId;
		this.Staff_Color = staff_Color;
		this.site = site;
		this.Staff_status= staff_status;
	}

	
}

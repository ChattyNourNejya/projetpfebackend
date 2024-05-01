package iss4u.ehr.clinique_projet.settings.entities;

import jakarta.persistence.*;


@Entity
public class StaffGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int StaffGroup_Ky;
	@ManyToOne
	private Servicee service;
	private String StaffGroup_Nm ;
	private int StaffGroup_Privilege ;
	private String StaffGroup_Type ;
	private String StaffGroup_Color ;

	public int getStaffGroup_Ky() {
		return StaffGroup_Ky;
	}
	public void setStaffGroup_Ky(int staffGroup_Ky) {
		StaffGroup_Ky = staffGroup_Ky;
	}
	public String getStaffGroup_Nm() {
		return StaffGroup_Nm;
	}
	public void setStaffGroup_Nm(String staffGroup_Nm) {
		StaffGroup_Nm = staffGroup_Nm;
	}
	public int getStaffGroup_Privilege() {
		return StaffGroup_Privilege;
	}
	public void setStaffGroup_Privilege(int staffGroup_Privilege) {
		StaffGroup_Privilege = staffGroup_Privilege;
	}
	public String getStaffGroup_Type() {
		return StaffGroup_Type;
	}
	public void setStaffGroup_Type(String staffGroup_Type) {
		StaffGroup_Type = staffGroup_Type;
	}
	public String getStaffGroup_Color() {
		return StaffGroup_Color;
	}
	public void setStaffGroup_Color(String staffGroup_Color) {
		StaffGroup_Color = staffGroup_Color;
	}
	public StaffGroup() {
		super();

	}
	public StaffGroup(int StaffGroup_Ky , String StaffGroup_Nm , int StaffGroup_Privilege , 
			String StaffGroup_Color , String StaffGroup_Type , Servicee service) {
		super();
		this.StaffGroup_Color= StaffGroup_Color;
		this.StaffGroup_Ky= StaffGroup_Ky;
		this.StaffGroup_Nm= StaffGroup_Nm;
		this.StaffGroup_Privilege=StaffGroup_Privilege;
		this.service=service;
		this.StaffGroup_Type=StaffGroup_Type;

	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	

}

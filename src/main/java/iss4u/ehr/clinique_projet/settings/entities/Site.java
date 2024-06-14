package iss4u.ehr.clinique_projet.settings.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Site {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Site_ky ;
	private String	Site_Nm ;
	private	String Site_Country;
	private	String SiteFirstContact ;

	@JsonBackReference
	@ManyToOne
	private SiteGroup sitegroup;

	@JsonManagedReference(value = "Siteservice")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", fetch = FetchType.LAZY)
	private List<LeService> Leservice;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "site", fetch = FetchType.LAZY)
	private List<Staff> staff;



	public Site() {
		super();
	}



	public Site(int site_ky, String site_Nm, String site_Country, String siteFirstContact, SiteGroup sitegroup,
				List<LeService> Leservice ,List<Staff> staff ) {
		super();
		Site_ky = site_ky;
		Site_Nm = site_Nm;
		Site_Country = site_Country;
		SiteFirstContact = siteFirstContact;
		this.sitegroup = sitegroup;
		this.Leservice = Leservice;
		this.staff= staff;
	}

	// fonction pour l'ajout d'un service dans un site
	public void addService(LeService newService) {
		Leservice.add(newService);
		newService.setSite(this);
	}



	public SiteGroup getSitegroup() {
		return sitegroup;
	}



	public void setSitegroup(SiteGroup sitegroup) {
		this.sitegroup = sitegroup;
	}



	public List<LeService> getService() {
		return Leservice;
	}



	public void setService(List<LeService> service) {
		this.Leservice = service;
	}



	public int getSite_ky() {
		return Site_ky;
	}

	public void setSite_ky(int site_ky) {
		Site_ky = site_ky;
	}

	public String getSite_Nm() {
		return Site_Nm;
	}

	public void setSite_Nm(String site_Nm) {
		Site_Nm = site_Nm;
	}



	public String getSite_Country() {
		return Site_Country;
	}



	public void setSite_Country(String site_Country) {
		Site_Country = site_Country;
	}

	public String getSiteFirstContact() {
		return SiteFirstContact;
	}

	public void setSiteFirstContact(String siteFirstContact) {
		SiteFirstContact = siteFirstContact;
	}


	public void addStaff(Staff newStaff) { staff= (List<Staff>) newStaff;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}




	@Override
	public String toString() {
		return "Site{" +
				"Site_ky=" + Site_ky +
				", Site_Nm='" + Site_Nm + '\'' +
				", Site_Country='" + Site_Country + '\'' +
				", SiteFirstContact='" + SiteFirstContact + '\'' +

				'}';
	}

}


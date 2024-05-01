package iss4u.ehr.clinique_projet.settings.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Site {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Site_ky ;
	private String	Site_Nm ; 
	private	String Site_Country;
	private	String SiteFirstContact ;
	
	@JsonBackReference
    @ManyToOne
    private SiteGroup sitegroup;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "site", fetch = FetchType.EAGER)
    private List<Servicee> service;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "site", fetch = FetchType.EAGER)
	private  List<Staff> staff;



	public Site() {
		super();
	}

	

	public Site(int site_ky, String site_Nm, String site_Country, String siteFirstContact, SiteGroup sitegroup,
			List<Servicee> service ,List<Staff> staff ) {
		super();
		Site_ky = site_ky;
		Site_Nm = site_Nm;
		Site_Country = site_Country;
		SiteFirstContact = siteFirstContact;
		this.sitegroup = sitegroup;
		this.service = service;
		this.staff= staff;
	}

	// fonction pour l'ajout d'un service dans un site
	public void addService(Servicee newService) {
		service.add(newService);
		newService.setSite(this);
	}

	

	public SiteGroup getSitegroup() {
		return sitegroup;
	}



	public void setSitegroup(SiteGroup sitegroup) {
		this.sitegroup = sitegroup;
	}



	public List<Servicee> getService() {
		return service;
	}



	public void setService(List<Servicee> service) {
		this.service = service;
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
}


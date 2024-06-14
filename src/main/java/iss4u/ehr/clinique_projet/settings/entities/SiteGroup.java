package iss4u.ehr.clinique_projet.settings.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
public class SiteGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int SiteGroup_ky ;
	private String SiteGroup_Nm;
	
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sitegroup")
    private List<Site> site;
	
	

	public SiteGroup(int siteGroup_ky, String siteGroup_Nm, List<Site> site) {
		super();
		this.SiteGroup_ky = siteGroup_ky;
		this.SiteGroup_Nm = siteGroup_Nm;
		this.site = site;
	}

	public SiteGroup() {
		super();
	}


	// fonction pour l'ajout d'un site dans un siteGroup
	public void addSite(Site newSite) {
		site.add(newSite);
		newSite.setSitegroup(this);
	}
	
	

	public List<Site> getSite() {
		return site;
	}

	public void setSite(List<Site> site) {
		this.site = site;
	}

	public int getSiteGroup_ky() {
		return SiteGroup_ky;
	}

	public void setSiteGroup_ky(int siteGroup_ky) {
		SiteGroup_ky = siteGroup_ky;
	}

	public String getSiteGroup_Nm() {
		return SiteGroup_Nm;
	}

	public void setSiteGroup_Nm(String siteGroup_Nm) {
		SiteGroup_Nm = siteGroup_Nm;
	}

	@Override
	public String toString() {
		return "SiteGroup{" +
				"SiteGroup_ky=" + SiteGroup_ky +
				", SiteGroup_Nm='" + SiteGroup_Nm + '\'' +

				'}';
	}
	
	


}


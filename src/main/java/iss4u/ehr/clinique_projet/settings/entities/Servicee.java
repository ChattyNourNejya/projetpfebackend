package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.stay.entities.Stay;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
@Setter
@Getter
@Entity
public class Servicee {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Service_ky ;
	@Column(name = ("serviceName"))
	private String Service_Nm ;
	
	@JsonBackReference
    @ManyToOne
    private Site site;

    @JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = FetchType.LAZY)
	private List<ServiceZone> servicezone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = FetchType.LAZY)
	private List<StaffGroup> staffGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stay_id")
	@JsonBackReference(value = "Stayservice")
	private Stay stay;




	public Servicee(int service_ky, String service_Nm, Site site, List<ServiceZone> servicezone, List<StaffGroup> staffGroup) {
		super();
		Service_ky = service_ky;
		Service_Nm = service_Nm;
		this.site = site;
		this.servicezone = servicezone;
	}

	public Servicee() {
		super();
	}

	// fonction pour l'ajout d'un serviceZone dans un service
	public void addServiceZone(ServiceZone newServiceZone) {
		servicezone.add(newServiceZone);
		newServiceZone.setService(this);
	}

	public int getService_ky() {
		return Service_ky;
	}

	public void setService_ky(int service_ky) {
		Service_ky = service_ky;
	}

	public String getService_Nm() {
		return Service_Nm;
	}

	public void setService_Nm(String service_Nm) {
		Service_Nm = service_Nm;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<ServiceZone> getServicezone() {
		return servicezone;
	}

	public void setServicezone(List<ServiceZone> servicezone) {
		this.servicezone = servicezone;
	}

    public void addStaffGroup(StaffGroup newStaffGroup) {
		 staffGroup = (List<StaffGroup>) newStaffGroup;
    }

	public List<StaffGroup> getStaffGroup() {
		return staffGroup;
	}

	public void setStaffGroup(List<StaffGroup> staffGroup) {
		this.staffGroup = staffGroup;
	}
}

package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ServiceZone {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int ServiceZone_ky ;
	private String ServiceZone_Nm ;

	@JsonBackReference
	@ManyToOne
	private Servicee service;

	@JsonIgnore
	@OneToMany(mappedBy = "serviceZone", cascade = CascadeType.ALL)
	private List<FunctionalUnit> functionalUnits;

	public ServiceZone(int serviceZone_ky, String serviceZone_Nm, Servicee service) {
		super();
		ServiceZone_ky = serviceZone_ky;
		ServiceZone_Nm = serviceZone_Nm;
		this.service = service;
	}

	public ServiceZone() {
		super();
	}
	
	

	public Servicee getService() {
		return service;
	}

	public void setService(Servicee service) {
		this.service = service;
	}

	public int getServiceZone_ky() {
		return ServiceZone_ky;
	}

	public void setServiceZone_ky(int serviceZone_ky) {
		ServiceZone_ky = serviceZone_ky;
	}

	public String getServiceZone_Nm() {
		return ServiceZone_Nm;
	}

	public void setServiceZone_Nm(String serviceZone_Nm) {
		ServiceZone_Nm = serviceZone_Nm;
	}


	// Méthode toString() pour afficher les informations de l'objet ServiceZone
	@Override
	public String toString() {
		return "ServiceZone{" +
				"ServiceZone_ky=" + ServiceZone_ky +
				", ServiceZone_Nm='" + ServiceZone_Nm + '\'' +
				", service=" + service +
				'}';
	}

	// Méthode pour obtenir la liste des unités fonctionnelles associées à cette zone de service
	public List<FunctionalUnit> getFunctionalUnits() {
		return functionalUnits;
	}

	// Méthode pour définir la liste des unités fonctionnelles associées à cette zone de service
	public void setFunctionalUnits(List<FunctionalUnit> functionalUnits) {
		this.functionalUnits = functionalUnits;
	}
}




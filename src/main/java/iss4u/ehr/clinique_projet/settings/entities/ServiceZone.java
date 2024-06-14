package iss4u.ehr.clinique_projet.settings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import iss4u.ehr.clinique_projet.settings.entities.FunctionalUnit;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ServiceZone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ServiceZone_ky ;
	private String ServiceZone_Nm ;

	@JsonBackReference(value = "serviceServiceZone")
	@ManyToOne(fetch = FetchType.LAZY)
	private LeService Leservice;

	@JsonIgnore
	@OneToMany(mappedBy = "serviceZone",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "serviceZoneFunctinalUnit")
	private List<FunctionalUnit> functionalUnits;

	public ServiceZone(int serviceZone_ky, String serviceZone_Nm, LeService Leservice) {
		super();
		ServiceZone_ky = serviceZone_ky;
		ServiceZone_Nm = serviceZone_Nm;
		this.Leservice = Leservice;
	}

	public ServiceZone() {
		super();
	}



	public LeService getService() {
		return Leservice;
	}

	public void setService(LeService Leservice) {
		this.Leservice = Leservice;
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




package cesi.sourcesapi.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dossier_Dossier")
public class Dossier_Dossier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dossier_Dossier() {
		super();
	}

	@Id
	@Column(name= "idDossierParent")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDossierParent;
	
	@Id
	@Column(name= "idDossierEnfant")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idDossierEnfant;
	
	/*
	@ManyToMany(mappedBy = "dossier_dossier")
	private Set<Dossier> dossier = new HashSet<>();
	*/
	
	public int getIdParent() {
		return idDossierParent;
	}

	public void setIdParent(int idDossierParent) {
		this.idDossierParent = idDossierParent;
	}
	
	public int getIdEnfant() {
		return idDossierEnfant;
	}

	public void setIdEnfant(int ididDossierEnfant) {
		this.idDossierParent = idDossierEnfant;
	}

	/*
	public Set<Dossier> getDossier() {
		return dossier;
	}

	public void setDossier(Set<Dossier> dossier) {
		this.dossier = dossier;
	}*/
}

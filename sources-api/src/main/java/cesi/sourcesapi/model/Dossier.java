package cesi.sourcesapi.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Dossier")
public class Dossier {
	
	public Dossier() {}

	public Dossier(String name, String etat) {
		super();
		this.name = name;
		this.dateCreation = new Date(System.currentTimeMillis());
		this.etat = etat;
		this.utilisateur = null;
	}
	
	public Dossier(String name, String etat, Utilisateur user) {
		super();
		this.name = name;
		this.dateCreation = new Date(System.currentTimeMillis());
		this.etat = etat;
		this.utilisateur = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String name;
	
	@Column(name = "dateCreation", nullable = false)
	private Date dateCreation;
	
	@Column(name = "etat", nullable = false)
	private String etat;
	
	@ManyToOne
	@JoinColumn(name = "Utilisateur_id", insertable = true, updatable = true) 
	private Utilisateur utilisateur;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "Dossier_Dossier",
		joinColumns = { @JoinColumn(name = "id_dossier_parent")},
		inverseJoinColumns = { @JoinColumn(name = "id_dossier_enfant")}
	)
	private Set<Dossier> dossiersEnfant = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date dateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Set<Dossier> getDossiersEnfant() {
		return dossiersEnfant;
	}

	public void setDossiersEnfant(Set<Dossier> dossiersEnfant) {
		this.dossiersEnfant = dossiersEnfant;
	}
	
	public void addDossierEnfant(Dossier enfant) {
		this.dossiersEnfant.add(enfant);
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
}

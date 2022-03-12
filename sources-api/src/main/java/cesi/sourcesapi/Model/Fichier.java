package cesi.sourcesapi.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Fichier") 
public class Fichier {

	public Fichier(){}
	
	public Fichier(String nom, int taille, String type, Date dateCreation, String etat) {
		super();
		this.nom = nom;
		this.taille = taille;
		this.type = type;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.dossier = null;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "taille", nullable = false)
	private int taille;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "dateCreation", nullable = false)
	private Date dateCreation;
	
	@Column(name = "etat", nullable = false)
	private String etat;
	
	//Owning side of the relationship, prevent inconsistencies
	@ManyToOne
	@JoinColumn(name = "Dossier_id", insertable = false, updatable = false) 
	private Dossier dossier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateCreation() {
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

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	
	
}

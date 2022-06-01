package cesi.sourcesapi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fichier")
public class Fichier {
	
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
	
	public String getNom() {
		return nom;
	}
	
	public int getTaille() {
		return taille;
	}

	public String getType() {
		return type;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public String getEtat() {
		return etat;
	}
	
	public Fichier() {
		super();
	}
	
	public Fichier(String nom, int taille, String type, Date dateCreation, String etat) {
		super();
		this.nom = nom;
		this.taille = taille;
		this.type = type;
		this.dateCreation = dateCreation;
		this.etat = etat;
	}
}


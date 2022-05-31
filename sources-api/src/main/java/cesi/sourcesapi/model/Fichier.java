package cesi.sourcesapi.model;

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
	
	public Fichier() {
		super();
	}
	
	public String getNom() {
		return nom;
	}
	
	public Fichier(String nom) {
		super();
		this.nom = nom;
	}
}


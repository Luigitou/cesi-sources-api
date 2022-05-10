package cesi.sourcesapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Statut")
public class Statut {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String nom ;

	public Statut() {
		
	}
	
	public Statut(String nom) {
		
		super();
		this.nom = nom;
		
	}
	
}

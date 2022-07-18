package cesi.sourcesapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateur") 
public class Utilisateur {

	public Utilisateur() {
		super();
	}
	
	public Utilisateur(String nom, String prenom, String mail, String adresse, String password) {
		
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.password = password;		
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "prenom", nullable = false)
	private String prenom;
	
	@Column(name = "mail", nullable = false, length = 200)
	private String mail;
	
	@Column(name = "adresse", nullable = false)
	private String adresse;
	
	@Column(name = "mdp", nullable = false)
	private String password;
	
	/*@ManyToOne
	@JoinColumn(name = "Statut_id", insertable = true, updatable = true) 
	private Statut statut;*/
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "Utilisateur_Fichier",
		joinColumns = { @JoinColumn(name = "id_fichier")},
		inverseJoinColumns = { @JoinColumn(name = "id_favoris")}
	)
	private List<Fichier> favoris = new ArrayList<>();

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public List<Fichier> getList() {
		return this.favoris;
	}
	
	public List<Object> getFavoris() {
		List<Object> list = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		for(int i = 0; i < favoris.size(); i++) {
			map.put("nom", favoris.get(i).getNom());
			map.put("dateCreation", favoris.get(i).getDateCreation());
			map.put("etat", favoris.get(i).getEtat());
			
			list.add(map);
		}
		
		return list;
	}
	
	public void setFavoris(Fichier favoris) {
		this.favoris.add(favoris);
	}
	
	public void deleteFavoris(Fichier favoris) {
		this.favoris.remove(favoris);
	}

	
}

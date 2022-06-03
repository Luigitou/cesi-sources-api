package cesi.sourcesapi.model;

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
		name = "Utilisateur_Utilisateur",
		joinColumns = { @JoinColumn(name = "id_utilisateur")},
		inverseJoinColumns = { @JoinColumn(name = "id_ami")}
	)
	private Set<Utilisateur> amis;

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

	public Set<Utilisateur> getAmi(Integer id_ami) {
		return amis;
	}

	public void setAmi(Set<Utilisateur> amis) {
		this.amis = amis;
	}	
}

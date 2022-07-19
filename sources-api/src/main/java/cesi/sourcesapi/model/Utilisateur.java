 package cesi.sourcesapi.model;

import java.util.List;
//import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
	
	public Utilisateur() {}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@Column(name = "prenom", nullable = false)
	private String prenom;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "mail", nullable = false, length = 200)
	private String mail;
	
	@Column(name = "adresse", nullable = false)
	private String adresse;
	
	@Column(name = "mdp", nullable = false)
	private String password;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
		name = "Utilisateur_Utilisateur",
		joinColumns = { @JoinColumn(name = "id_utilisateur")},
		inverseJoinColumns = { @JoinColumn(name = "id_ami")}
	)
	private List<Utilisateur> amis = new ArrayList<>();
	
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();*/
	
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

	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dossier> dossierEnfant = new HashSet<>();


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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Utilisateur> getAmis() {
		return amis;
	}

	public void setAmis(List<Utilisateur> amis) {
		this.amis = amis;
	}

	public Set<Dossier> getDossierEnfant() {
		return dossierEnfant;
	}

	public void setDossierEnfant(Set<Dossier> dossierEnfant) {
		this.dossierEnfant = dossierEnfant;
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

	public List<Utilisateur> getAmi() {
		return amis;
	}

	public void setAmi(Utilisateur ami) {
		this.amis.add(ami);
	}	
	
	public void deleteAmi(Utilisateur ami) {
		this.amis.remove(ami);
	}	
}

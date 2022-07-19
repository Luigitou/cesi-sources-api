package cesi.sourcesapi.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cesi.sourcesapi.model.Dossier;
import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private String prenom;
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String mail;
    private String adresse;
    @NotNull
    @NotBlank
    private String password;
    private List<Utilisateur> amis;
    private List<Fichier> favoris;
    private Set<Dossier> dossierEnfant;
    
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

package cesi.sourcesapi.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.FichierRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;

@Service
public class UtilisateurServices {
  @Autowired
  private UtilisateurRepository utilisateurRepository;
  @Autowired
  private FichierRepository fichierRepository;
  @Autowired
  private FichierServices FichierServices;
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
	
	public Utilisateur getUtilisateurById(int id_utilisateur){
		return utilisateurRepository.findById(id_utilisateur);
	}
	
	public boolean fileAlreadyInFavorites(int id_fichier,int id_utilisateur) throws Exception {
		try {
			Fichier currentFile = fichierRepository.findById(id_fichier);
			Utilisateur user = utilisateurRepository.findById(id_utilisateur);
			//boolean exists = utilisateurRepository.existsById(id_fichier);
			boolean exists = user.getList().contains(currentFile);
			return exists;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Object> listFavorites(int id_utilisateur) throws Exception {
		try {
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		return user.getFavoris();
		}catch(Exception e) {
			throw e;
		}
	}
	
	public boolean addFavorites(int id_utilisateur,int id_fichier) throws Exception {
		try {
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		Fichier userFavoris = FichierServices.getFilesById(id_fichier);
		user.setFavoris(userFavoris);
		//"Save" permet de sauvegarder l'update en BDD
		utilisateurRepository.save(user);
		return true;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public boolean deleteFavorites(int id_utilisateur,int id_fichier) throws Exception {
		try {
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		Fichier userFavoris = FichierServices.getFilesById(id_fichier);
		user.deleteFavoris(userFavoris);
		utilisateurRepository.save(user);
		return true;
		}catch(Exception e) {
			throw e;
		}
	}
}

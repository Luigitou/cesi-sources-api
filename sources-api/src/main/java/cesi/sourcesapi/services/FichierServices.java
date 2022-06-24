package cesi.sourcesapi.services;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.model.Dossier;
import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.DossierRepository;
import cesi.sourcesapi.repository.FichierRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;

@Service
public class FichierServices {
	
	private String LOCAL_UPLOAD_PATH = "/home/louis/API - cesi/uploads/";
	
	@Autowired
	private FichierRepository fichierRepository;
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public List<Fichier> getFichiers(int idUtilisateur, int idDossierParent) {
		try {
			Utilisateur user = utilisateurRepository.findById(idUtilisateur);
			Dossier dossier = dossierRepository.findByIdAndUtilisateur(idDossierParent, user);
			
			return fichierRepository.findByDossier(dossier);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean createFichier(int idUtilisateur, int idDossierParent, int statut, MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				Utilisateur user = utilisateurRepository.findById(idUtilisateur);
				Dossier parent = dossierRepository.findByIdAndUtilisateur(idDossierParent, user);
				
				String pathname = UUID.randomUUID().toString();
				
				if (saveOnDisk(file, pathname)) {
					Fichier newFile = new Fichier(file.getOriginalFilename(), (int) file.getSize(), file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1), new Date(System.currentTimeMillis()), String.valueOf(statut), parent, pathname);
					fichierRepository.save(newFile);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean saveOnDisk(MultipartFile file, String pathname) {
		try {
			if (!new File(LOCAL_UPLOAD_PATH).exists()) {
				new File(LOCAL_UPLOAD_PATH).mkdir();
			}
			
			File destination = new File(LOCAL_UPLOAD_PATH + pathname);
			file.transferTo(destination);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Fichier> getFichiers(String nom){
		return fichierRepository.findByNomContainingIgnoreCase(nom);
	}
	
	public List<Fichier> getFichierByType(String type){
		return fichierRepository.findByType(type);
	}
	
}

package cesi.sourcesapi.services;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
	
	private final String LOCAL_UPLOAD_PATH = "/home/louis/uploads/";
	
	@Autowired
	private FichierRepository fichierRepository;
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public List<Fichier> getSearchedFiles(String nom) throws Exception {
		try {
			return fichierRepository.findByNomContainingIgnoreCase(nom);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Fichier> getFichiers(int idUtilisateur, int idDossierParent) {
		try {
			Utilisateur user = utilisateurRepository.findById(idUtilisateur);
			Dossier dossier = dossierRepository.findByIdAndUtilisateur(idDossierParent, user);
			
			return fichierRepository.findByDossier(dossier);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean createFichier(int idUtilisateur, int idDossierParent, int statut, MultipartFile file) throws Exception {
		try {
			if (!file.isEmpty()) {
				Utilisateur user = utilisateurRepository.findById(idUtilisateur);
				Dossier parent = dossierRepository.findByIdAndUtilisateur(idDossierParent, user);
				
				String pathname = UUID.randomUUID().toString();
				
				if (saveOnDisk(file, pathname)) {
					Fichier newFile = new Fichier(file.getOriginalFilename(), (int) file.getSize(), file.getContentType(), new Date(System.currentTimeMillis()), String.valueOf(statut), parent, pathname);
					fichierRepository.save(newFile);
					return true;
				} else {
					throw new Exception("File cant be saved on disk !");
				}
			} else {
				throw new Exception("File is empty !");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean saveOnDisk(MultipartFile file, String pathname) throws Exception{
		try {
			
			if (!new File(LOCAL_UPLOAD_PATH).exists()) {
				new File(LOCAL_UPLOAD_PATH).mkdirs();
			}
			
			File destination = new File(new File(LOCAL_UPLOAD_PATH).getAbsolutePath() + "/" + pathname);
			//file.transferTo(destination);
			byte[] newFile = file.getBytes();
			FileOutputStream fos = new FileOutputStream(destination);
			fos.write(newFile);
			fos.close();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Fichier> getFichiers(String nom){
		try {
			return fichierRepository.findByNomContainingIgnoreCase(nom);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Fichier getFilesById(int id){
		try {
			return fichierRepository.findById(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Fichier> getFichierByType(String type){
		return fichierRepository.findByType(type);
	}
	
	public HashMap<String, String> getHeader(int idFichier) {
		HashMap<String, String> map = new HashMap<>();
		
		Fichier file = fichierRepository.findById(idFichier);
		map.put("name", file.getNom());
		map.put("type", file.getType());
		
		return map;
	}
	
	public FileSystemResource downloadFile(int idFichier) {
		Fichier file = fichierRepository.findById(idFichier);
		return new FileSystemResource(LOCAL_UPLOAD_PATH + file.getPath());
	}

}

package cesi.sourcesapi.Services;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.DossierRepository;
import cesi.sourcesapi.Repository.FichierRepository;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@Service
public class FichierService {
	
	@Autowired
	private FichierRepository ficherRepository;
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	public Fichier addFichier(MultipartFile fichier, String dossier, String mail) {
		try {
			boolean good = saveFileOnDisk(fichier);
			if (good) {
				Fichier fichierData = getDataFromFile(fichier, dossier, mail);
				return ficherRepository.save(fichierData);
			} else {
				throw new Exception("Upload du fichier échoué.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean saveFileOnDisk(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				String uploadDir = "/uploads/";
				String realPathToUpload = "/home/louis/API - cesi" + uploadDir;//request.getServletContext().getRealPath(uploadDir);
				System.out.println(realPathToUpload);
			
				if (! new File(realPathToUpload).exists()) {
					new File(realPathToUpload).mkdir();
				}
				
				String orgName = file.getOriginalFilename();
				String filePath = realPathToUpload + orgName;
				
				File dest = new File(filePath);
				file.transferTo(dest);
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private Fichier getDataFromFile(MultipartFile file, String dossier, String mail) {
		Utilisateur user = utilisateurRepository.findByMail(mail).get(0);
		List<Dossier> parents = dossierRepository.findByUtilisateur(user);
		
		Dossier parent = null;
		
		for (Dossier dossier2 : parents) {
			if (dossier2.getName().equals(dossier)) {
				parent = dossier2;
			}
		}
		
		Fichier fichier = new Fichier(
				file.getResource().getFilename(), 
				(int) file.getSize(), 
				file.getResource().getFilename().substring(file.getResource().getFilename().lastIndexOf(".") + 1), 
				new Date(System.currentTimeMillis()),
				"OK",
				parent
		);
		System.out.println(parent);
		return fichier;
	}
}

package cesi.sourcesapi.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.DossierRepository;
import cesi.sourcesapi.Repository.FichierRepository;
import cesi.sourcesapi.Repository.UtilisateurRepository;
import cesi.sourcesapi.Services.FichierService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class FichierController {
	
	@Autowired
	private FichierRepository ficherRepository;
	
	@Autowired
	private FichierService fichierService;
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping("/fichiers")
    public List<String> fetchUtilisateurs(@RequestParam String dossier, @RequestParam String mail){
        try {
        	Utilisateur user = utilisateurRepository.findByMail(mail).get(0);
    		List<Dossier> folderList = dossierRepository.findByUtilisateur(user);
    		Dossier folder = null;
    		for (Dossier dos : folderList) {
				if (dos.getName().equals(dossier)) {
					folder = dos;
				}
			}
    		if (folder != null) {
    			List<String> res = new ArrayList<>();
        		 List<Fichier> list = ficherRepository.findByDossier(folder);
        		 for (Fichier fichier : list) {
        			 System.out.println(fichier.getNom());
					res.add(new File(fichier.getNom(), fichier.getDateCreation(), fichier.getTaille(), fichier.getType(), user.getNom()).toString());
				}
        		 return res;
    		}
    		return null;
        } catch (Exception e) {
        	e.printStackTrace();
			return null;
		}

    }
	
	@PostMapping("/fichiers")
	public String createUFichier(@RequestParam("file") MultipartFile file, @RequestParam String dossier, @RequestParam String mail) {
		//Fichier savedFichier = ficherRepository.save();
		
		fichierService.addFichier(file, dossier, mail);
		
		return String.format("File %s upload succesfully ", file.getOriginalFilename());
		
		//return new ResponseEntity<Object>(savedFichier, HttpStatus.OK);
	}
	
	@GetMapping("/fichiers/download")
	public ResponseEntity<Resource> getFichierById(@RequestParam String nom) {
		try {
			String uploadDir = "/uploads/";
			String realPathToUpload = "/home/louis/API - cesi" + uploadDir;
			java.io.File file = new java.io.File(realPathToUpload + nom);
			InputStreamResource i = new InputStreamResource(new FileInputStream(file));
			String mimeType = Files.probeContentType(file.toPath());
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.parseMediaTypes(mimeType).get(0));
			System.out.println(headers.getContentType());
			return new ResponseEntity<Resource>(i, headers, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/fichiers/{id}")
	public ResponseEntity<Object> updateFichier(@PathVariable("id") int id, @RequestBody Fichier fichier) {
			fichier.setId(id);
			Fichier savedFichier = ficherRepository.save(fichier);
			
			return new ResponseEntity<Object>(savedFichier, HttpStatus.OK);
	}
	
	@DeleteMapping("/fichiers/{id}")
	public ResponseEntity<HttpStatus> deleteFichier(@PathVariable("id") int id) {
			ficherRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);	
	}
	
	private class File {
		private String nom;
		private Date date;
		private int taille;
		private String type;
		private String user;
		
		public File(String nom, Date date, int taille, String type, String user) {
			super();
			this.nom = nom;
			this.date = date;
			this.taille = taille;
			this.type = type;
			this.user= user;
		}
		
		public String toString() {
			return String.format("{\"nom\": \"%s\", \"date\": \"%s\", \"taille\": \"%s\", \"type\": \"%s\", \"user\": \"%s\"}", nom, date.toString(), taille, type, user);
		}
		
	}
}

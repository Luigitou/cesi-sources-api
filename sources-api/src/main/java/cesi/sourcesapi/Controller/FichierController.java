package cesi.sourcesapi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Fichier> fetchUtilisateurs(@RequestParam String dossier, @RequestParam String mail){
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
    			System.out.println("hello");
        		return ficherRepository.findByDossier(folder);
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
	
	@GetMapping("/fichiers/{id}")
	public ResponseEntity<Object> getFichierById(@PathVariable("id") int id) {
		
			Fichier fichier = ficherRepository.findById(id).get();
			if(fichier != null) {
				return new ResponseEntity<Object>(fichier, HttpStatus.OK);				
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
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
}

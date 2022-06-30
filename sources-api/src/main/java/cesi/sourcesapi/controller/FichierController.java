
package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.services.FichierServices;

@RestController
public class FichierController {
	
	@Autowired
	private FichierServices fichierServices;    
    
    // Get files Containing Name
    @GetMapping("/rechercheFichier")
    public ResponseEntity<List<Fichier>> getFichiersByNom(@RequestParam String nom){
        try {
        	new ArrayList<Fichier>();
    		return new ResponseEntity<>(fichierServices.getFichiers(nom), HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
		}

    }

	@GetMapping("/getFichiers")
	public ResponseEntity<Object> getFichiersFromUserAndFolder(@RequestParam int idUtilisateur, @RequestParam int idDossierParent) {
		try {
			return new ResponseEntity<>(fichierServices.getFichiers(idUtilisateur, idDossierParent), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getImages")
	public List<Fichier> getImages(){       
		List<Fichier> types = new ArrayList<>();
		
		types.addAll(fichierServices.getFichierByType("jpg"));
		types.addAll(fichierServices.getFichierByType("png"));
		
		return types; 
	}
	
	@GetMapping("/getDocuments")
	public List<Fichier> getDocuments(){
		List<Fichier> types = new ArrayList<>();
		
		types.addAll(fichierServices.getFichierByType("pdf"));
		types.addAll(fichierServices.getFichierByType("doc"));
		
		return types;
	}
	
	@GetMapping("/getVideos")
	public List<Fichier> getVideos(){
		 return fichierServices.getFichierByType("mp4");
	}

	@PostMapping("/createFichier")
	public ResponseEntity<Object> createFichiers(@RequestParam int idUtilisateur, @RequestParam int idDossierParent, @RequestParam int statut, @RequestParam("file") MultipartFile file) {
		try {
			if (fichierServices.createFichier(idUtilisateur, idDossierParent, statut, file)) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

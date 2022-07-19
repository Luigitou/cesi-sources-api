
package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.services.FichierServices;
import cesi.sourcesapi.repository.FichierRepository;

@RestController
public class FichierController {
	
	@Autowired
	private FichierServices fichierServices;
    
    // Get files Containing Name
    @GetMapping("/rechercheFichier")
    public ResponseEntity<List<Fichier>> getFichiersPublicByNom(@RequestParam String nom, String etat){
    	List<Fichier> returnFiles = new ArrayList<Fichier>();;
        try {
        	List<Fichier> fichierList = fichierServices.getFichiers(nom);
        	for(Fichier files : fichierList) {
    			if (files.getEtat().equals("Public")) {
    				returnFiles.add(files);
    			}
    		}
        	return new ResponseEntity<List<Fichier>>(returnFiles, HttpStatus.CREATED);
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
	
	@PostMapping("/create Fichier")
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
	
    // Get the files details by files id
    @GetMapping("/getFichierFromId")
    public ResponseEntity<Fichier> getFilesDetailsByFilesId(@RequestParam int idFichier){
        try {
			return new ResponseEntity<>(fichierServices.getFilesById(idFichier), HttpStatus.OK);
        } catch (Exception e) {
           throw new InternalError(e.getMessage());
		}
    }	
}

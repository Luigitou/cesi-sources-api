
package cesi.sourcesapi.controller;

import java.util.ArrayList;
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
import cesi.sourcesapi.repository.FichierRepository;
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
	public List<Fichier> getImages(String type){
		List<String> typeExtension = new ArrayList<>();
		typeExtension.add("png");
		typeExtension.add("jpg");
		
		for(String typeExt : typeExtension) {
			if(typeExt == "png") {
				type = "png";
			} else {
				type = "jpg";
			}
		}

		return fichierServices.getFichierByType(type);
	}
	
	@GetMapping("/getDocuments")
	public List<Fichier> getDocuments(String type){
		type = "pdf";
		return fichierServices.getFichierByType(type);
	}
	
	@GetMapping("/getVideos")
	public List<Fichier> getVideos(String type){
		type = "mp4";
		return fichierServices.getFichierByType(type);
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


package cesi.sourcesapi.controller;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        	List<Fichier> fichierList = fichierServices.getSearchedFiles(nom);
        	for(Fichier files : fichierList) {
    			if (files.getEtat().equals("0")) {
    				returnFiles.add(files);
    				System.out.println("Test" + files.getNom() + files.getEtat());
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
			return new ResponseEntity<>(fichierServices.createFichier(idUtilisateur, idDossierParent, statut, file), HttpStatus.CREATED);
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

	@GetMapping("/getHeaderFromFile")
	public ResponseEntity<Object> getHeaderFromCommentaire(@RequestParam int idFichier) {
		try {
			return new ResponseEntity<>(fichierServices.getHeader(idFichier), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Object> downloadFile(@RequestParam int idFichier) {
		try {
			HttpHeaders headers = new HttpHeaders();
			FileSystemResource file = fichierServices.downloadFile(idFichier);
			headers.setContentType(MediaType.parseMediaType(fichierServices.getHeader(idFichier).get("type")));
			headers.setContentLength(file.contentLength());
			return new ResponseEntity<>(file, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

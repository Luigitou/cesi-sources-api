
package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.services.FichierService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class FichierController {
	
	@Autowired
	FichierService FichierService;
	
//	 // Get all fichier
//    @GetMapping("/fichier")
//    public ResponseEntity<List<Fichier>> getAllFiles() {
//
//        try {
//            List<Fichier> files = new ArrayList<Fichier>();
//            return new ResponseEntity<>(FichierService.getFichier().forEach(files::add);, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new InternalError(e.getMessage());
//        }
//
//    }
    
    
    // Get files Containing Name
    @GetMapping("/rechercheFichier")
    public ResponseEntity<List<Fichier>> getFichierByNom(@RequestParam String nom){
        try {
        	new ArrayList<Fichier>();
    		return new ResponseEntity<>(FichierService.getFichier(nom), HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
		}

    }
    
    
}
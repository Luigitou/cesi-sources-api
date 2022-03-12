package cesi.sourcesapi.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Repository.FichierRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class FichierController {
	
	@Autowired
	private FichierRepository ficherRepository;
	
	@GetMapping("/fichiers")
    public List<Fichier> fetchUtilisateurs(){
        return ficherRepository.findAll();
    }
	
	@PostMapping("/fichiers")
	public ResponseEntity<Object> createUFichier(@RequestBody Fichier fichier) {
		Fichier savedFichier = ficherRepository.save(fichier);
		
		return new ResponseEntity<Object>(savedFichier, HttpStatus.OK);
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

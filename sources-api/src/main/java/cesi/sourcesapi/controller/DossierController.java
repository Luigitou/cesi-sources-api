package cesi.sourcesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.services.DossierServices;


@RestController
public class DossierController {
	
	@Autowired
	private DossierServices dossierServices;
	
	@GetMapping("/getDossiers")
	public ResponseEntity<Object> getDossiersFromUtilisateur(@RequestParam int idUtilisateur, @RequestParam int idDossierParent) {
		try {
			return new ResponseEntity<>(dossierServices.getDossiers(0, 0), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createDossier")
	public ResponseEntity<Object> createDossier(@RequestParam int idUtilisateur, @RequestParam int idDossierParent, @RequestParam String name, @RequestParam int statut) {
		try {
			if (dossierServices.createDossier(idUtilisateur, idDossierParent, name, statut)) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/getBase")
	public ResponseEntity<Object> getBase(@RequestParam int idUtilisateur) {
		try {
			return new ResponseEntity<>(dossierServices.getBase(idUtilisateur), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

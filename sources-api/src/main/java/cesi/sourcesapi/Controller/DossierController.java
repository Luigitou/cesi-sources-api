package cesi.sourcesapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cesi.sourcesapi.Services.DossierServices;


@RestController
public class DossierController {
	
	@Autowired
	private DossierServices dossierServices;
	
	@PostMapping("/getDossiers")
	public ResponseEntity<Object> getDossiersFromUtilisateur(@RequestParam int idUtilisateur, @RequestParam int idDossierParent) {
		try {
			return new ResponseEntity<Object>(dossierServices.getDossiers(0, 0), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createDossier")
	public ResponseEntity<Object> createDossier(@RequestParam int idUtilisateur, @RequestParam int idDossierParent, @RequestParam String name, @RequestParam int statut) {
		try {
			boolean success = dossierServices.createDossier(idUtilisateur, idDossierParent, name, statut);
			if (success) {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}	}

}

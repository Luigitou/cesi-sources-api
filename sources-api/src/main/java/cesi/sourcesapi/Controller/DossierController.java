package cesi.sourcesapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.DossierRepository;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class DossierController {
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping("/dossiers")
	public List<Dossier> fetchDossiers(@RequestParam String mail) {
		Utilisateur user = utilisateurRepository.findByMail(mail).get(0);
		return dossierRepository.findByUtilisateur(user);
	}
	
	@PostMapping("/dossiers")
	public ResponseEntity<Object> createDossier(@RequestParam String mail, @RequestParam String name) {
		try {
			Dossier dossier = new Dossier(name, "OK");
			Utilisateur utilisateur = utilisateurRepository.findByMail(mail).get(0);
			dossier.setUtilisateur(utilisateur);
			dossierRepository.save(dossier);
			
			return new ResponseEntity<Object>("" + mail + " - " + name, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

}

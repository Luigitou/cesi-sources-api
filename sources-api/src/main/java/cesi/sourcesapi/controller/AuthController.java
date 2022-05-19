package cesi.sourcesapi.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.model.Statut;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.StatutRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class AuthController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository; // FIXME : pas de repository dans le controller

	@Autowired
	private StatutRepository statutRepository;

	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
/*	
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Statut statut = statutRepository.findByName("Utilisateur").get(0);
		Utilisateur newUser = utilisateur;
		newUser.setStatut(statut);
		utilisateurRepository.save(newUser);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
*/	
}
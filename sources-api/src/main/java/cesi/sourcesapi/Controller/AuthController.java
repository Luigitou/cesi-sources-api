package cesi.sourcesapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@RestController
public class AuthController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	/*
	@PostMapping("/auth")
	public ResponseEntity<User> authenticate() {
		User user = 
		return ResponseEntity.ok().body(null);
	}
	*/
	
	@PostMapping("createUser")
	public void addUser() {
		Utilisateur user = new Utilisateur("Bellefemine", "Louis", "louis@mail.com", "hop", "8 rue du bout du monde");
		
	}
}

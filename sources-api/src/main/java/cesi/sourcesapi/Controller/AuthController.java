package cesi.sourcesapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
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
	
	/*
	@PostMapping("createUser")
	public void addUser() {
		Utilisateur user = new Utilisateur("Bellefemine", "Louis", "louis@mail.com", "hop", "8 rue du bout du monde");
		
	}*/
	
	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
	
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
		
		return new ResponseEntity<Object>(savedUtilisateur, HttpStatus.OK);
	}
}

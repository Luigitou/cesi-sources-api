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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;
import cesi.sourcesapi.Services.AuthServices;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class AuthController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AuthServices authServices;
	
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
	
	@PostMapping("/auth")
	public ResponseEntity<Object> authenticate(@RequestParam String mail, @RequestParam String pwd) {
		return new ResponseEntity<Object>(authServices.getAuth(mail, pwd), HttpStatus.OK);
	}
	
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
		
		return new ResponseEntity<Object>(savedUtilisateur, HttpStatus.OK);
	}
	
	@GetMapping("/utilisateurs/{id}")
	public ResponseEntity<Object> getUtilisateurById(@PathVariable("id") int id) {
		try {
			Utilisateur utilisateur = utilisateurRepository.findById(id).get();
			if(utilisateur != null) {
				return new ResponseEntity<Object>(utilisateur, HttpStatus.OK);				
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception ex) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/utilisateurs/{id}")
	public ResponseEntity<Object> updateUtilisateur(@PathVariable("id") int id, @RequestBody Utilisateur utilisateur) {
		try {
			utilisateur.setId(id);
			Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
			return new ResponseEntity<Object>(savedUtilisateur, HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/utilisateurs/{id}")
	public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") int id) {
		try {
			utilisateurRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}

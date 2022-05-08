package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Statut;
import model.Utilisateur;
import repository.StatutRepository;
import repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class AuthController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private StatutRepository statutRepository;

	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
	
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Statut statut = statutRepository.findByName("Utilisateur").get(0);
		Utilisateur newUser = utilisateur;
		newUser.setStatut(statut);
		utilisateurRepository.save(newUser);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}

package cesi.sourcesapi.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilisateur")
public class UserController {

    @GetMapping
    public Map<String, Object> getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", authentication.getName());
        userMap.put("error", false);
        return userMap;
    }
}
/*@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class UserController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	StatutRepository statutRepository;
	
	@Autowired
	private AuthServices authServices;
	
	// Authentification
	@PostMapping("/auth")
	public ResponseEntity<Object> authenticate(@RequestParam String mail, @RequestParam String pwd) {
		return authServices.getAuth(mail, pwd);
	}
	
	// Create user
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {

		Utilisateur newUser = utilisateur;

		utilisateurRepository.save(newUser);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	
	//update user
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

	//Get all users
	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
	
	//get user by id
	@GetMapping("/utilisateurs/{id}")
	public ResponseEntity<Object> getUtilisateurById(@PathVariable("id") int id) {
		try {
			Utilisateur utilisateur = utilisateurRepository.findById(id);
			if(utilisateur != null) {
				return new ResponseEntity<Object>(utilisateur, HttpStatus.OK);				
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception ex) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//delete user
	@DeleteMapping("/utilisateurs/{id}")
	public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") int id) {
		try {
			utilisateurRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}*/

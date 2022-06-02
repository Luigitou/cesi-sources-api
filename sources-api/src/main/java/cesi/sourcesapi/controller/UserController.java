package cesi.sourcesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.StatutRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class UserController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	StatutRepository statutRepository;
	
	// Create user
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		
		Utilisateur newUser = utilisateur;

		utilisateurRepository.save(newUser);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

    
    /*// Update user
    @PutMapping("utilisateurs/{id}")
    public ResponseEntity<Utilisateur> 
       updateUser(@PathVariable("id") int id,
            @RequestBody Utilisateur utilisateur) {

        Optional<Utilisateur> userdata = utilisateurRepository.findById(id);
        if (userdata.isPresent()) {
            Utilisateur _utilisateur = userdata.get();
            _utilisateur.setMail(utilisateur.getMail());
            _utilisateur.setNom(utilisateur.getNom());
            _utilisateur.setPrenom(utilisateur.getPrenom());
            _utilisateur.setAdresse(utilisateur.getAdresse());
            _utilisateur.setPassword(utilisateur.getPassword());
            return new ResponseEntity<>
              (utilisateurRepository.save(_utilisateur), HttpStatus.OK);
        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }
    
 // Get all Users
    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUsers() {

        try {
            List<Utilisateur> users = new ArrayList<Utilisateur>();
            utilisateurRepository.findAll().forEach(users::add);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }

    }

    // Get user by ID
    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> 
        getUserByID(@PathVariable("id") int id) {

        Optional<Utilisateur> userdata = utilisateurRepository.findById(id);
        if (userdata.isPresent()) {
            return new ResponseEntity<>
                (userdata.get(), HttpStatus.OK);
        } else {
            throw new UserNotFound("Invalid User Id");
        }

    }
    // Delete user
    @DeleteMapping("utilisateurs/{id}")
    public ResponseEntity<Utilisateur> 
       deleteUser(@PathVariable("id") int id) {

        Optional<Utilisateur> userdata = utilisateurRepository.findById(id);
        if (userdata.isPresent()) {
            utilisateurRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }*/
}

package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.exception.InternalServerError;
import cesi.sourcesapi.exception.UserNotFound;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class UserController {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	// Create user
    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> 
        createUser(@RequestBody Utilisateur utilisateur) {
        try {
        	Utilisateur newuser = new Utilisateur(utilisateur.getNom(),
        			utilisateur.getPrenom(), utilisateur.getMail(),
        			utilisateur.getAdresse(), utilisateur.getPassword(),
        			utilisateur.getStatut());
            utilisateurRepository.save(newuser);
            return new ResponseEntity<>
            (newuser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    
    // Update user
    @PutMapping("utilisateurs/{id}")
    public ResponseEntity<Utilisateur> 
       updateUser(@PathVariable("id") Long id,
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
        getUserByID(@PathVariable("id") Long id) {

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
       deleteUser(@PathVariable("id") Long id) {

        Optional<Utilisateur> userdata = utilisateurRepository.findById(id);
        if (userdata.isPresent()) {
            utilisateurRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }
}

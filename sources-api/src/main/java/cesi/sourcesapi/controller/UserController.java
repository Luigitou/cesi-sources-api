package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.StatutRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;
import cesi.sourcesapi.services.AuthServices;
import cesi.sourcesapi.services.UtilisateurServices;
import cesi.sourcesapi.services.FichierServices;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class UserController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private UtilisateurServices utilisateurServices;
	@Autowired
	private FichierServices FichierServices;
	
	
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

	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
	
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

	// Liste des favoris
	@GetMapping("/favoris") 
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> listeFavoris(@RequestParam int id_utilisateur){
		try {
			Utilisateur user = utilisateurServices.getUtilisateurById(id_utilisateur);
			return new ResponseEntity(user.getFavoris(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
		 
	// Ajouter favoris
	@PostMapping("/addFavoris")
	public ResponseEntity<Object> ajouterAmi(@RequestParam int id_utilisateur, @RequestParam int id_fichier){
		
		Utilisateur user = utilisateurServices.getUtilisateurById(id_utilisateur);
		Fichier userFavoris = FichierServices.getFilesById(id_fichier);
		
		user.setFavoris(userFavoris);
		//"Save" permet de sauvegarder l'update en BDD
		utilisateurRepository.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	// Supprimer favoris
	@DeleteMapping("/deleteFavoris") 
	public ResponseEntity<Object> supprimerAmi(@RequestParam int id_utilisateur, @RequestParam int id_fichier){

		Utilisateur user = utilisateurServices.getUtilisateurById(id_utilisateur);
		Fichier userFavoris = FichierServices.getFilesById(id_fichier);
		
		user.deleteFavoris(userFavoris);
		utilisateurRepository.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

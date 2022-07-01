package cesi.sourcesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.StatutRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;
import cesi.sourcesapi.services.AuthServices;
import cesi.sourcesapi.services.UtilisateurService;

@RestController
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
	
	// Liste des amis
	@GetMapping("/{id_ami}/ami") 
	@ResponseStatus(HttpStatus.OK)
	public List<Utilisateur> afficherLeschoix(@PathVariable(value = "id_ami") int id_ami){
		List<Utilisateur> listAmis = utilisateurRepository.choixAmi(id_ami);

		return listAmis;
	}
		 
	// Ajouter ami
	@PostMapping("/addAmi")
	public int[] ajouterAmi(@RequestParam int id_utilisateur, @RequestParam int id_ami){
		int[] userAndAmi = new int[2];
		
		userAndAmi[0] = id_utilisateur;
		userAndAmi[1] = id_ami;
		
		return userAndAmi;
	}
		
	// Supprimer ami
		 
}

package cesi.sourcesapi.controller;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UpdateUserDto;
import cesi.sourcesapi.dto.UtilisateurDto;
import cesi.sourcesapi.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.StatutRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;
import cesi.sourcesapi.services.AuthServices;
import cesi.sourcesapi.services.UtilisateurServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private UtilisateurServices utilisateurServices;
	
	
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
    
<<<<<<< HEAD
=======
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

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Response> getUserByUserName(@PathVariable("username")String username) {
        Response res = utilisateurService.getUserByUsername(username);
        return new ResponseEntity<>(res, res.getStatus());
    }
    @GetMapping()
    public ResponseEntity<Response> getAllUsers() {
        Response res = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(res, res.getStatus());
    }
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Response> deleteUser(@PathVariable("username")String username) {
        Response res = utilisateurService.deleteUtilisateur(username);
        return new ResponseEntity<>(res, res.getStatus());
    }
    @PutMapping("/username/{username}")
    public ResponseEntity<Response> updateUser(@PathVariable("username")String username, @Valid @RequestBody UpdateUserDto dto) {
        Response res = utilisateurService.updateUtilisateur(username,dto);
        return new ResponseEntity<>(res, res.getStatus());
    }
<<<<<<< HEAD
=======
>>>>>>> 3955724c94d0a2bcf1a0aa821638c0df2172a92b
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
	@GetMapping("/ami") 
	@ResponseStatus(HttpStatus.OK)
	public List<Utilisateur> listeAmis(@RequestParam int id_utilisateur){
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		
		return user.getAmi();
	}
		 
	// Ajouter ami
	@PostMapping("/addAmi")
	public ResponseEntity<Object> ajouterAmi(@RequestParam int id_utilisateur, @RequestParam int id_ami){
		
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		Utilisateur userAmi = utilisateurRepository.findById(id_ami);
		
		user.setAmi(userAmi);
		utilisateurRepository.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	// Supprimer ami
	@DeleteMapping("/deleteAmi") 
	public ResponseEntity<Object> supprimerAmi(@RequestParam int id_utilisateur, @RequestParam int id_ami){
		Utilisateur user = utilisateurRepository.findById(id_utilisateur);
		Utilisateur userAmi = utilisateurRepository.findById(id_ami);
		
		user.deleteAmi(userAmi);
		utilisateurRepository.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Verification 
	@GetMapping("/FavVerif") 
	public ResponseEntity<Object> fileAlreadyInFavorites(@RequestParam int id_utilisateur, @RequestParam int id_fichier){
		try {
			return new ResponseEntity<>(utilisateurServices.fileAlreadyInFavorites(id_fichier, id_utilisateur),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// Liste des favoris
	@GetMapping("/favorites") 
	public ResponseEntity<Object> listFavorites(@RequestParam int id_utilisateur){
		try {
			return new ResponseEntity<>(utilisateurServices.listFavorites(id_utilisateur),HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		 
	// Ajouter favoris
	@PostMapping("/addFavorites")
	public ResponseEntity<Object> addFavorites(@RequestParam int id_utilisateur, @RequestParam int id_fichier){
		try {
			utilisateurServices.addFavorites(id_utilisateur, id_fichier);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.NO_CONTENT);
		}
	}
		
	// Supprimer favoris
	@DeleteMapping("/deleteFavorites") 
	public ResponseEntity<Object> deleteFavorites(@RequestParam int id_utilisateur, @RequestParam int id_fichier){
		try {
			utilisateurServices.deleteFavorites(id_utilisateur, id_fichier);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

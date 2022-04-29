package cesi.sourcesapi.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Statut;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.DossierRepository;
import cesi.sourcesapi.Repository.StatutRepository;
import cesi.sourcesapi.Repository.UtilisateurRepository;
import cesi.sourcesapi.Services.AuthServices;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class AuthController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository; // FIXME : pas de repository dans le controller
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	
	@Autowired
	private AuthServices authServices;
	
	@GetMapping("/utilisateurs")
    public List<Utilisateur> fetchUtilisateurs(){
        return utilisateurRepository.findAll();
    }
	
	@PostMapping("/auth")
	public ResponseEntity<Object> authenticate(@RequestParam String mail, @RequestParam String pwd) {
		return authServices.getAuth(mail, pwd);
	}
	
	@PostMapping("/utilisateurs")
	public ResponseEntity<Object> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		Statut statut = statutRepository.findByName("Utilisateur").get(0);
		Utilisateur newUser = utilisateur;
		newUser.setStatut(statut);
		utilisateurRepository.save(newUser);
		
		Dossier dossier = new Dossier("base", "OK");
		dossier.setUtilisateur(newUser);
		
		dossierRepository.save(dossier);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	 // Liste des amis
	 @RequestMapping(
		value = "/{id_ami}/ami",
		method = RequestMethod.GET,
		produces = "application/json"
		)
		
		@ResponseStatus(HttpStatus.OK)
		public List<Utilisateur> afficherLeschoix(
				@PathVariable(value = "id_ami") Integer id_ami
		){
			List<Utilisateur> listAmis = utilisateurRepository.choixAmi(id_ami);

			return listAmis;
		}

	/*
	@GetMapping("/ami")
	public List<String> fetchAmis(@RequestParam String mail) {
		Utilisateur user = utilisateurRepository.findByMail(mail).get(0);
		List<Utilisateur> listAmis = utilisateurRepository.findByUtilisateur(user);
		List<String> res = new ArrayList<>();
		for (Utilisateur amisAjoute : listAmis) {
			if (amisAjoute.getMail().equals(mail)) {
				for (Utilisateur UserAmi : amisAjoute.getAmi()) {
					res.add(new Ami(UserAmi.getNom(), UserAmi.getPrenom(), UserAmi.getMail(), ((Utilisateur) UserAmi.getAmi()).getNom()).toString());
				}
			}
		}
		return res;
	}
	
	private class Ami {
		private String name;
		private String prenom;
		private String mail;
		private String adresse;
		
		public Ami(String name, String prenom, String mail, String adresse) {
			super();
			this.name = name;
			this.prenom = prenom;
			this.mail = mail;
			this.adresse = adresse;
		}
		
		public String toString() {
			return String.format("{\"nom\": \"%s\", \"prenom\": \"%s\", \"mail\": \"%s\"}", name, prenom, mail, adresse);
		}
		
	}
	*/
	
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

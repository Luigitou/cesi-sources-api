package cesi.sourcesapi.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.DossierRepository;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class DossierController {
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping("/dossiers")
	public List<String> fetchDossiers(@RequestParam String dossier, @RequestParam String mail) {
		Utilisateur user = utilisateurRepository.findByMail(mail).get(0);
		List<Dossier> listDossier = dossierRepository.findByUtilisateur(user);
		List<String> res = new ArrayList<>();
		for (Dossier dos : listDossier) {
			if (dos.getName().equals(dossier)) {
				for (Dossier child : dos.getDossiersEnfant()) {
					res.add(new Folder(child.getName(), child.getDateCreation(), child.getUtilisateur().getNom()).toString());
				}
			}
		}
		return res;
	}
	
	@PostMapping("/dossiers")
	public ResponseEntity<Object> createDossier(@RequestParam String mail, @RequestParam String name, @RequestParam String dossier) {
		try {
			Dossier parent = dossierRepository.findByName(dossier).get(0);
			
			Dossier newDossier = new Dossier(name, "OK");
			parent.addDossierEnfant(newDossier);
			
			Utilisateur utilisateur = utilisateurRepository.findByMail(mail).get(0);
			newDossier.setUtilisateur(utilisateur);
			dossierRepository.save(newDossier);
			
			return new ResponseEntity<Object>("" + mail + " - " + name, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	private class Folder {
		private String name;
		private Date date;
		private String nomUser;
		
		public Folder(String name, Date date, String nomUser) {
			super();
			this.name = name;
			this.date = date;
			this.nomUser = nomUser;
		}
		
		public String toString() {
			return String.format("{\"nom\": \"%s\", \"date\": \"%s\", \"user\": \"%s\"}", name, date.toString(), nomUser);
		}
		
	}

}

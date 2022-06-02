package cesi.sourcesapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;

@Service
public class AuthServices {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public ResponseEntity<Object> getAuth(String mail, String pwd) {
		List<Utilisateur> list = utilisateurRepository.findAll();

		System.out.println(list);
		
		if (list.size() > 0) {
			for (Utilisateur utilisateur : list) {
				if (utilisateur.getMail().equals(mail) && utilisateur.getPassword().equals(pwd)) {
					return new ResponseEntity<Object>(new Auth(
						list.get(0).getNom(),
						list.get(0).getPrenom(), 
						list.get(0).getMail(), 
						list.get(0).getAdresse()
					).toString(), HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Object>("{\"error\": \"User not found !\"}", HttpStatus.UNAUTHORIZED);
	}
	
	private class Auth {
		private String nom;
		private String prenom;
		private String mail;
		private String adresse;
		
		public Auth(String nom, String prenom, String mail, String adresse) {
			this.nom = nom;
			this.prenom = prenom;
			this.mail = mail;
			this.adresse = adresse;
		}
		
		public String toString() {
			return String.format("{\"nom\": \"%s\", \"prenom\": \"%s\", \"mail\": \"%s\", \"adresse\": \"%s\"}", nom, prenom, mail, adresse);
		}
	}
}

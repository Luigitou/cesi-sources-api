package cesi.sourcesapi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@Service
public class AuthServices {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public String getAuth(String mail, String pwd) {
		List<Utilisateur> list = utilisateurRepository.findAll();

		System.out.println(list);
		
		if (list.size() > 0) {
			for (Utilisateur utilisateur : list) {
				if (utilisateur.getMail().equals(mail) && utilisateur.getPassword().equals(pwd)) {
					return new Auth(
						list.get(0).getNom(),
						list.get(0).getPrenom(), 
						list.get(0).getMail(), 
						list.get(0).getAdresse()
					).toString();
				}
			}
		}
		return "User not found";
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

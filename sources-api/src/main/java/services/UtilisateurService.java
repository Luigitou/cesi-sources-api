package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Utilisateur;
import repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		
		return utilisateurRepository.save(utilisateur);
		
	}
}

package cesi.sourcesapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;

public class UtilisateurService {
  @Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
}

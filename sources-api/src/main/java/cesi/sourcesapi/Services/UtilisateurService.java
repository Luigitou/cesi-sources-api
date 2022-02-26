package cesi.sourcesapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
}

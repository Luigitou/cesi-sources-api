package cesi.sourcesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;

public class UtilisateurService {
  @Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
	
	public List<Utilisateur> getUtilisateurId(Integer id_utilisateur) {
		return utilisateurRepository.findUtilisateurById(id_utilisateur);
	}
	
	public List<Utilisateur> getAmiId(Integer id_ami) {
		return utilisateurRepository.findAmiById(id_ami);
	}
}

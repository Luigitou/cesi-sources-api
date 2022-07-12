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
	
//	public List<Utilisateur> addAmi(Integer id_utilisateur, Integer id_ami) {
//		return utilisateurRepository.addAmi(id_utilisateur, id_ami);
//	}
//	
//	public List<Utilisateur> deleteAmi(Integer id_utilisateur, Integer id_ami) {
//		return utilisateurRepository.deleteAmi(id_utilisateur, id_ami);
//	}
}

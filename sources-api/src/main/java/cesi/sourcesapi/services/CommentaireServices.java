package cesi.sourcesapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.model.Commentaire;
import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.CommentaireRepository;
import cesi.sourcesapi.repository.FichierRepository;
import cesi.sourcesapi.repository.UtilisateurRepository;

@Service
public class CommentaireServices {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private FichierRepository fichierRepository;
	
	@Autowired
	private CommentaireRepository commentaireRepository;

	public List<Commentaire> getCommentaire(int idFichier) {
		Fichier file = fichierRepository.findById(idFichier);
		return file.getCommentairesList();
	}
	
	public boolean createCommentaire(int idFichier, int idUtilisateur, String text) throws Exception {
		try {
			Utilisateur user = utilisateurRepository.findById(idUtilisateur);
			Fichier file = fichierRepository.findById(idFichier);
			
			Commentaire commentaire = new Commentaire(LocalDateTime.now(), text, user);
			commentaireRepository.save(commentaire);
			file.addCommentaires(commentaire);
			fichierRepository.save(file);
			return true;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
}

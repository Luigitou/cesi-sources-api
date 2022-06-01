package cesi.sourcesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Dossier;
import cesi.sourcesapi.model.Utilisateur;

public interface DossierRepository extends JpaRepository<Dossier, Integer> {
	
	Dossier findByIdAndUtilisateur(int id, Utilisateur utilisateur); 
	
	Dossier findByUtilisateurAndName(Utilisateur utilisateur, String name);
	
}
package cesi.sourcesapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Utilisateur;

public interface DossierRepository extends JpaRepository<Dossier, Integer> {
	
	Dossier findByIdAndUtilisateur(int id, Utilisateur utilisateur); 
	
}
package cesi.sourcesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Utilisateur findById(int id);
	
}
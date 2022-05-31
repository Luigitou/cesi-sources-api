package cesi.sourcesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Fichier;

public interface FichierRepository extends JpaRepository<Fichier, Integer>{

	List<Fichier> findByNomContaining(String nom);
	List<Fichier> findByNomContainingIgnoreCase(String nom);
	
}
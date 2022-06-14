package cesi.sourcesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Dossier;
import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.model.Utilisateur;

public interface FichierRepository extends JpaRepository<Fichier, Integer>{

	List<Fichier> findByNomContainingIgnoreCase(String nom);

	List<Fichier> findByEtat(String etat);

	List<Fichier> findByDossier(Dossier dossier);
}
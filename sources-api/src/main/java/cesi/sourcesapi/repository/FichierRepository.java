package cesi.sourcesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Fichier;

public interface FichierRepository extends JpaRepository<Fichier, Integer> {

}
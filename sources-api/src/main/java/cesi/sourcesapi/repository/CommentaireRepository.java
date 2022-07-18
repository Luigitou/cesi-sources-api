package cesi.sourcesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Commentaire;
import cesi.sourcesapi.model.Fichier;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
}

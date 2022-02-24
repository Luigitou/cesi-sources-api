package cesi.sourcesapi;

import org.springframework.data.jpa.repository.JpaRepository;
import cesi.sourcesapi.Model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

}

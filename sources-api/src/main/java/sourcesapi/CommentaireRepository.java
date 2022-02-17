package sourcesapi;

import org.springframework.data.jpa.repository.JpaRepository;
import sourcesapi.Model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

}

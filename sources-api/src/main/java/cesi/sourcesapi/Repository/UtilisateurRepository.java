package cesi.sourcesapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cesi.sourcesapi.Model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	List<Utilisateur> findByMail(String mail);
	
}

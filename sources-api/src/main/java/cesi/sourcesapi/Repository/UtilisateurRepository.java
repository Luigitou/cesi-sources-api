package cesi.sourcesapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cesi.sourcesapi.Model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Utilisateur findById(int id);
	
}
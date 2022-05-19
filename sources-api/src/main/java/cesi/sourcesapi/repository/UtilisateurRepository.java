package cesi.sourcesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cesi.sourcesapi.model.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	List<Utilisateur> findByMail(String mail);	
	
	// Afficher le contenu de la table associative utilisateur_utilisateur;
	@Query(value = "SELECT * FROM utilisateur_utilisateur, utilisateur WHERE id = id_ami;", nativeQuery = true)
	List<Utilisateur> choixAmi(@Param("id_ami") Integer id_ami);

}

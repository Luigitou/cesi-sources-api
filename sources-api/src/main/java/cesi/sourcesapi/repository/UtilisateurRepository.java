package cesi.sourcesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import cesi.sourcesapi.model.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	List<Utilisateur> findByMail(String mail);	
	
	// Afficher le contenu de la table associative utilisateur_utilisateur;
	@Query(value = 
			"SELECT * FROM utilisateur_utilisateur, utilisateur WHERE id = id_ami;", 
			nativeQuery = true)
	List<Utilisateur> choixAmi(@Param("id_ami") Integer id_ami);
	
	// Ajouter un ami
	/*
	@Modifying
	@Query(
	  value = 
	    "insert into utilisateur_utilisateur (id_utilisateur, id_ami) values (:id_utilisateur, :id_ami)",
	  nativeQuery = true)
	ResponseEntity<Object> insertAmi(@Param("id_utilisateur") Integer id_utilisateur, @Param("id_ami") Integer id_ami);
	*/
}

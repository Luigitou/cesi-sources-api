package cesi.sourcesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.model.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	List<Utilisateur> findByMail(String mail);	

}

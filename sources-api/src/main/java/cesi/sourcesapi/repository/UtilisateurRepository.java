package cesi.sourcesapi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cesi.sourcesapi.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	List<Utilisateur> findByMail(String mail);	

	Boolean existsByUsername(String username);
	Boolean existsByMail(String mail);
    Optional<Utilisateur> findUserByUsername(String username);
	Utilisateur findById(int id);
}
 
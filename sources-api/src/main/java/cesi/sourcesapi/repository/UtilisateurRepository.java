package cesi.sourcesapi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

>>>>>>> 3955724c94d0a2bcf1a0aa821638c0df2172a92b
import cesi.sourcesapi.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
<<<<<<< HEAD
	
	List<Utilisateur> findByMail(String mail);	

=======
	Boolean existsByUsername(String username);
	Boolean existsByMail(String mail);
    Optional<Utilisateur> findUserByUsername(String username);
>>>>>>> 3955724c94d0a2bcf1a0aa821638c0df2172a92b
	Utilisateur findById(int id);
}
 
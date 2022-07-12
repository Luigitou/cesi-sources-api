package cesi.sourcesapi.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cesi.sourcesapi.model.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	List<Utilisateur> findByMail(String mail);	

	Utilisateur findById(int id);
	
	// Afficher un ami;
	@Query(value = "SELECT * FROM utilisateur_utilisateur, utilisateur WHERE id = id_ami;", nativeQuery = true)
	List<Utilisateur> getAmi(@Param("id_ami") Integer id_ami);
	
	// Ajouter un ami
//	List<Utilisateur> addAmi(Integer id_utilisateur, Integer id_ami);

	// Supprimer un ami
//	List<Utilisateur> deleteAmi(Integer id_utilisateur, Integer id_ami);
}

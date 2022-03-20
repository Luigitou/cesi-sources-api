package cesi.sourcesapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.Model.Dossier;
import cesi.sourcesapi.Model.Utilisateur;

public interface DossierRepository extends JpaRepository<Dossier, Integer>{

	List<Dossier> findByUtilisateur(Utilisateur utilisateur);
	
	List<Dossier> findByName(String name);
	
}

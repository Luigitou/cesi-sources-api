package cesi.sourcesapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cesi.sourcesapi.Model.Fichier;

public interface FichierRepository extends JpaRepository<Fichier, Integer>{

}

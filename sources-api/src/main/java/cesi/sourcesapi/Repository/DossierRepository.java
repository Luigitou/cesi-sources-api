package cesi.sourcesapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cesi.sourcesapi.Model.Dossier;

public interface DossierRepository extends JpaRepository<Dossier, Integer>{

}

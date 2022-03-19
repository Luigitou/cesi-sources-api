package cesi.sourcesapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cesi.sourcesapi.Model.Statut;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer>{

	List<Statut> findByName(String name);
	
}

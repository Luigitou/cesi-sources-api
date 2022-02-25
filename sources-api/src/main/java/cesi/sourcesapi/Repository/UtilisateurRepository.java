package cesi.sourcesapi.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cesi.sourcesapi.Model.Utilisateur;

@Repository
@Transactional
public class UtilisateurRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insertUserInDb(Utilisateur user) {
		this.em = emf.createEntityManager();
		this.em.persist(user);
	}
}

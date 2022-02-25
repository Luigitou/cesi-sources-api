package cesi.sourcesapi.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;

import cesi.sourcesapi.Model.Privilege;
import cesi.sourcesapi.Model.Statut;
import cesi.sourcesapi.Model.Utilisateur;

public class UtilisateursTest {
    
    @Test
    public void testingTests() {
    	assertEquals(4, 2 + 2);
    }
    
    @Test
    public void CreateUser() {
    	/*
    	// Create entityManager for persistence
    	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Test Utilisateurs");
    	EntityManager em;
    	em = emFactory.createEntityManager();
    	em.getTransaction().begin();
    	*/
    	
    	// Init list to insert later
		Set<Utilisateur> usersList = new HashSet<>();
    	Set<Statut> statutList = new HashSet<>();
    	Set<Privilege> privList = new HashSet<>();
    	
    	// Init object to insert in db
    	Privilege p1 = new Privilege("Supprimer utilisateurs");
    	Privilege p2 = new Privilege("Creer admins");
    	privList.add(p1);
    	privList.add(p2);
    	
    	Statut s1 = new Statut("Moderateur");
    	Statut s2 = new Statut("Admin");
    	statutList.add(s1);
    	statutList.add(s2);
    	
    	Utilisateur u1 = new Utilisateur("Bellefemine", "Louis", "louis@mail.com", "hop", "8 rue du bout du monde");
    	Utilisateur u2 = new Utilisateur("Quelqun", "D'autre", "mail@hotmail.com", "pwd", "1 rue du debut du monde");
    	usersList.add(u1);
    	usersList.add(u2);
    	
    	assertEquals(0, s1.getPrivileges().size());
    	assertEquals(0, p1.getStatut().size());
    	assertEquals(null, u1.getStatut());
    	
    	// Add privilege to statut and statut to users and persist it
    	/*
    	em.persist(p1);
    	em.persist(p2);
    	*/
    	
    	s1.setPrivileges(privList);
    	s2.setPrivileges(privList);
    	/*
    	em.persist(s1);
    	em.persist(s2);
    	*/
    	
    	u1.setStatut(s1);
    	u2.setStatut(s2);
    	/*
    	em.persist(u1);
    	em.persist(u2);
    	*/
    	
    	// test if all data were succesfully set to each entity
    	assertNotNull(u1.getStatut());
    	assertNotNull(u2.getStatut());
    	assertNotNull(s1.getPrivileges());
    	assertNotNull(s2.getPrivileges());
    }
}

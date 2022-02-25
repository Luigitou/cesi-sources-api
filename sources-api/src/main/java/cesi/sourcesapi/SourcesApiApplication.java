package cesi.sourcesapi;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cesi.sourcesapi.Model.Privilege;
import cesi.sourcesapi.Model.Statut;
import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Repository.UtilisateurRepository;

@SpringBootApplication
@RestController
public class SourcesApiApplication {
	
	@RequestMapping("/test")
	public String test() {
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SourcesApiApplication.class, args);
		
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
    	
    	s1.setPrivileges(privList);
    	s2.setPrivileges(privList);
    	
    	u1.setStatut(s1);
    	u2.setStatut(s2);
    	
    	UtilisateurRepository ur = new UtilisateurRepository();
    	ur.insertUserInDb(u1);
    	ur.insertUserInDb(u2);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:8080");
			}
		};
	}

}

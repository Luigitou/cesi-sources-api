package cesi.sourcesapi;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cesi.sourcesapi.Model.Utilisateur;
import cesi.sourcesapi.Services.UtilisateurService;

@SpringBootApplication
@RestController
public class SourcesApiApplication implements CommandLineRunner{
	
	@RequestMapping("/api")
	public String test() {
		return "Source API is up and running !\n";
	}

	public static void main(String[] args) {
		SpringApplication.run(SourcesApiApplication.class, args);
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
	
	@Autowired
	private UtilisateurService utilisateurService;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
    	Utilisateur utilisateur = new Utilisateur("Bellefemine", "Louis", "louis@mail.com", "hop", "8 rue du bout du monde");
    	utilisateur = utilisateurService.addUtilisateur(utilisateur);
	}

}

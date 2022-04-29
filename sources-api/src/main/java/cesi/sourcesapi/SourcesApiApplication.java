package cesi.sourcesapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cesi.sourcesapi.Model.Privilege;
import cesi.sourcesapi.Model.Statut;
import cesi.sourcesapi.Repository.PrivilegeRepository;
import cesi.sourcesapi.Repository.StatutRepository;

@SpringBootApplication
@RestController  // FIXME : Retirer le controller du Main
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
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private StatutRepository statutRepository;
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
				try {
					List<Statut> listStatut = new ArrayList<Statut>();
					List<Privilege> listPrivilege = new ArrayList<Privilege>();
					
					listPrivilege.addAll(privilegeRepository.findAll());
					listStatut.addAll(statutRepository.findAll());
					
					if (listStatut.isEmpty() && listPrivilege.isEmpty()) {
						
						Privilege admin = new Privilege("admin");
						Privilege superadmin = new Privilege("superadmin");
						Privilege modo = new Privilege("Moderateur");
						Privilege user = new Privilege("Utilisateur");
						
						Statut adminS = new Statut("admin");
						Statut superadminS = new Statut("superadmin");
						Statut modoS = new Statut("Moderateur");
						Statut userS = new Statut("Utilisateur");
						
						adminS.addPrivilege(admin);
						superadminS.addPrivilege(superadmin);
						modoS.addPrivilege(modo);
						userS.addPrivilege(user);
						
						statutRepository.save(adminS);
						statutRepository.save(superadminS);
						statutRepository.save(modoS);
						statutRepository.save(userS);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

package cesi.sourcesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SourcesApiApplication {
	
	@RequestMapping("/test")
	public String test() {
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SourcesApiApplication.class, args);
	}

}


package cesi.sourcesapi.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Repository.FichierRepository;
import cesi.sourcesapi.Services.FichierService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
public class FichierController {
	
	@Autowired
	FichierRepository FichierRepository;
	
	 // Get all fichier
    @GetMapping("/fichier")
    public ResponseEntity<List<Fichier>> getAllFiles() {

        try {
            List<Fichier> files = new ArrayList<Fichier>();
            FichierRepository.findAll().forEach(files::add);
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }

    }
}
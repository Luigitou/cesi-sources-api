package cesi.sourcesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cesi.sourcesapi.services.CommentaireServices;

@RestController
public class CommentairesController {

	@Autowired
	private CommentaireServices commentaireServices;
	
	@GetMapping("/getCommentaires")
	public ResponseEntity<Object> getCommentairesFromFichier(@RequestParam int idFichier) {
		try {
			return new ResponseEntity<>(commentaireServices.getCommentaire(idFichier), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createCommentaire")
	public ResponseEntity<Object> createCommentaire(@RequestParam int idFichier, @RequestParam int idUtilisateur, @RequestParam String text) {
		try {
			return new ResponseEntity<>(commentaireServices.createCommentaire(idFichier, idUtilisateur, text), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

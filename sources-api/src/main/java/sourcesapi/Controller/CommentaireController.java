package sourcesapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sourcesapi.CommentaireRepository;
import sourcesapi.Model.Commentaire;

@RestController
public class CommentaireController {
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	/*
	@PostMapping("/commentaire")
	public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
		return commentaireRepository.save(commentaire);
	}*/
	
	@PostMapping("/commentaire")
	public ResponseEntity<List<Commentaire>> findAll(){
		return ResponseEntity.ok(commentaireRepository.findAll());
	}
	
	@GetMapping("/commentaire/{id}")
	public ResponseEntity<Commentaire> findCommentaireId(@PathVariable(value = "id")Integer id){
		Commentaire commentaire = commentaireRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Commentaire non trouvé" + id));
				return ResponseEntity.ok().body(commentaire);
	}
	
	@DeleteMapping("/commentaire/{id}")
	public ResponseEntity<Void> deleteCommentaire(@PathVariable(value = "id")Integer id){
		Commentaire commentaire = commentaireRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Commentaire non trouvé" + id));
				commentaireRepository.delete(commentaire);
				return ResponseEntity.ok().build();
	}
}

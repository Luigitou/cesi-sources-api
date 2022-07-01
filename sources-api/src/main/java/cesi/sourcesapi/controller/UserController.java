package cesi.sourcesapi.controller;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UpdateUserDto;
import cesi.sourcesapi.dto.UtilisateurDto;
import cesi.sourcesapi.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UtilisateurService utilisateurService;

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Response> getUserByUserName(@PathVariable("username")String username) {
        Response res = utilisateurService.getUserByUsername(username);
        return new ResponseEntity<>(res, res.getStatus());
    }
    @GetMapping()
    public ResponseEntity<Response> getAllUsers() {
        Response res = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(res, res.getStatus());
    }
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Response> deleteUser(@PathVariable("username")String username) {
        Response res = utilisateurService.deleteUtilisateur(username);
        return new ResponseEntity<>(res, res.getStatus());
    }
    @PutMapping("/username/{username}")
    public ResponseEntity<Response> updateUser(@PathVariable("username")String username, @Valid @RequestBody UpdateUserDto dto) {
        Response res = utilisateurService.updateUtilisateur(username,dto);
        return new ResponseEntity<>(res, res.getStatus());
    }
}

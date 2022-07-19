package cesi.sourcesapi.controller;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UtilisateurDto;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;
import cesi.sourcesapi.services.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    final UtilisateurRepository userRepository;
    final AuthenticationManager authenticationManager;
    final JwtUserDetailsService userDetailsService;

    public AuthenticationController(UtilisateurRepository userRepository, AuthenticationManager authenticationManager,
                                    JwtUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UtilisateurDto dto) {
        Response response = new Response();
        if (userRepository.existsByUsername(dto.getUsername())){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setSuccess(true);
            response.setData("Username is already taken!");
            return new ResponseEntity<>(response,response.getStatus());
        }
        if (userRepository.existsByMail(dto.getMail())){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setSuccess(true);
            response.setData("Email address already exist!");
            return new ResponseEntity<>(response,response.getStatus());
        }
        /*Utilisateur user = UtilisateurDto.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .username(dto.getUsername())
                .adresse(dto.getAdresse())
                .mail(dto.getMail())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .build();*/
        
        Utilisateur user = new Utilisateur();
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setUsername(dto.getUsername());
        user.setAdresse(dto.getAdresse());
        user.setMail(dto.getMail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        
        userRepository.save(user);
        response.setStatus(HttpStatus.OK);
        response.setSuccess(true);
        response.setData(user);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
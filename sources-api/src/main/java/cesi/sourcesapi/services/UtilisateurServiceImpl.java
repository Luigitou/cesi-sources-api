package cesi.sourcesapi.services;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UpdateUserDto;
import cesi.sourcesapi.model.Utilisateur;
import cesi.sourcesapi.repository.UtilisateurRepository;
import cesi.sourcesapi.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    public final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Response getUserByUsername(String username) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUserByUsername(username);
        return utilisateur.map(value -> new Response(true, value))
                .orElseGet(() -> new Response(false, "utilisateur not found with username " + username));
    }

    @Override
    public Response getAllUtilisateurs() {
        return new Response(true,utilisateurRepository.findAll());
    }

    @Override
    public Response deleteUtilisateur(String username) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUserByUsername(username);
        if (utilisateur.isPresent()){
            utilisateurRepository.delete(utilisateur.get());
            return new Response(true,"utilisateur deleted with username : "+username);
        }
        return new Response(false, "utilisateur not found with username " + username);
    }

    @Override
    public Response updateUtilisateur(String username, UpdateUserDto dto) {

        Optional<Utilisateur> utilisateur = utilisateurRepository.findUserByUsername(username);
        if (utilisateur.isPresent()){
            if (dto.getUsername()!= null && !dto.getUsername().isEmpty()){
                if (!dto.getUsername().equals(utilisateur.get().getUsername())){
                    if(utilisateurRepository.existsByUsername(dto.getUsername())){
                        return new Response(false,"utilisateur exist with username : "+dto.getUsername(), HttpStatus.BAD_REQUEST);
                    }
                }
            }
            utilisateur.get().setNom(Utils.validateString(dto.getNom(),utilisateur.get().getNom()));
            utilisateur.get().setPrenom(Utils.validateString(dto.getPrenom(),utilisateur.get().getPrenom()));
            utilisateur.get().setUsername(Utils.validateString(dto.getUsername(),utilisateur.get().getUsername()));
            utilisateur.get().setMail(Utils.validateString(dto.getMail(),utilisateur.get().getMail()));
            utilisateur.get().setAdresse(Utils.validateString(dto.getAdresse(),utilisateur.get().getAdresse()));
            if (dto.getPassword()!=null && !dto.getPassword().isEmpty()){
            utilisateur.get().setPassword(Utils.validateString(new BCryptPasswordEncoder().encode(dto.getPassword()),utilisateur.get().getPassword()));

            }
            utilisateurRepository.save(utilisateur.get());
            return new Response(true,utilisateur.get());
        }
        return new Response(false, "utilisateur not found with username " + username);
    }
}

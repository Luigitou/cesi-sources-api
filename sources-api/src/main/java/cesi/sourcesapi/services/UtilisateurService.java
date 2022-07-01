package cesi.sourcesapi.services;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UpdateUserDto;
import cesi.sourcesapi.dto.UtilisateurDto;
import cesi.sourcesapi.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Response getUserByUsername(String username);
    Response getAllUtilisateurs();
    Response deleteUtilisateur(String username);
    Response updateUtilisateur(String username, UpdateUserDto dto);
}

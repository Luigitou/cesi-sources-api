package cesi.sourcesapi.services;

import cesi.sourcesapi.dto.Response;
import cesi.sourcesapi.dto.UpdateUserDto;

public interface UtilisateurService {
    Response getUserByUsername(String username);
    Response getAllUtilisateurs();
    Response deleteUtilisateur(String username);
    Response updateUtilisateur(String username, UpdateUserDto dto);
}
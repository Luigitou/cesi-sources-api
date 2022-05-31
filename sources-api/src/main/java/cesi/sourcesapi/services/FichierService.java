package cesi.sourcesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.model.Fichier;
import cesi.sourcesapi.repository.FichierRepository;

@Service
public class FichierService {

	@Autowired
	private FichierRepository fichierRepository;
	
	public Fichier addFichier(Fichier fichier) {
		
		return fichierRepository.save(fichier);
		
	}
	
	public List<Fichier> getFichier(String nom){
		return fichierRepository.findByNomContainingIgnoreCase(nom);
	}
}
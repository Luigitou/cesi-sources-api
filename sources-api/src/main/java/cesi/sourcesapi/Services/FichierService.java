package cesi.sourcesapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Repository.FichierRepository;

@Service
public class FichierService {
	
	@Autowired
	private FichierRepository ficherRepository;
	
	public Fichier addFichier(Fichier fichier) {
		return ficherRepository.save(fichier);
	}
}

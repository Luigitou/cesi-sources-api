package cesi.sourcesapi.Services;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cesi.sourcesapi.Model.Fichier;
import cesi.sourcesapi.Repository.FichierRepository;

@Service
public class FichierService {
	
	@Autowired
	private FichierRepository ficherRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	public Fichier addFichier(MultipartFile fichier) {
		try {
			boolean good = saveFileOnDisk(fichier);
			if (good) {
				Fichier fichierData = getDataFromFile(fichier);
				return ficherRepository.save(fichierData);
			} else {
				throw new Exception("Upload du fichier échoué.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean saveFileOnDisk(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				String uploadDir = "/uploads/";
				String realPathToUpload = "/home/louis/API - cesi" + uploadDir;//request.getServletContext().getRealPath(uploadDir);
				System.out.println(realPathToUpload);
			
				if (! new File(realPathToUpload).exists()) {
					new File(realPathToUpload).mkdir();
				}
				
				String orgName = file.getOriginalFilename();
				String filePath = realPathToUpload + orgName;
				
				File dest = new File(filePath);
				file.transferTo(dest);
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	private Fichier getDataFromFile(MultipartFile file) {
		return new Fichier(
				file.getOriginalFilename(), 
				(int) file.getSize(), 
				file.getContentType(), 
				new Date(System.currentTimeMillis()),
				"OK"
		);
	}
}

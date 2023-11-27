package fr.DecoCN.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.DecoCN.bll.ArchivesService;
import fr.DecoCN.bll.FicheService;
import fr.DecoCN.bo.Archives;
import fr.DecoCN.bo.Fiche;
import jakarta.websocket.server.PathParam;

@Controller
public class ArchivesController {

	
	@Autowired
	FicheService ficheService;
	
	@Autowired
	ArchivesService archivesService;
	
	
	@GetMapping("/private/archiver")
	String archiver(@PathParam("id") long id,Model model) {
		model.addAttribute("fiche", ficheService.getFicheById(id));
		return "home/archiver";
	}
	
	@PostMapping("/private/archiver")
	String Archiver(@RequestParam("file") MultipartFile file,Fiche fiche) {
		try {
			Fiche existingFiche = ficheService.getFicheById(fiche.getId());
			
	        if (file != null && !file.isEmpty()) {
	            fiche.setNomDeFichier(file.getOriginalFilename());
	            fiche.setContenu(file.getBytes());
	        }
	        
	        
		archivesService.CreerArchives(existingFiche);
		ficheService.supprimerFiche(fiche.getId());
		} catch (Exception e) {
	        e.printStackTrace();
	    }		
		return "redirect:/private/liste_archives";
	}
	
	
	@GetMapping("/private/liste_archives")
	String listeArchives(Model model) {
		List<Archives> listeArchives = new ArrayList<Archives>();
		
		listeArchives.addAll(archivesService.consulterArchives());
		
		
		
		model.addAttribute("listeFiches", listeArchives);
		return "home/liste_archives";
	}
	
	@GetMapping("/private/detailArchives")
	String fichedetailArchives(@PathParam("id") long id, Model model) {
		model.addAttribute("fiche" , archivesService.getArchivesById(id));
		return "home/detail_archives";
	}
	
	
	@GetMapping("/private/modifier_archives")
	String modifierArchives(@PathParam("id") long id,Model model) {
		model.addAttribute("fiche", archivesService.getArchivesById(id));
		return "home/modifier_archives";
	}
	
	@PostMapping("/private/modifier_archives")
	String modifierArchives(@RequestParam("file") MultipartFile file,Archives archives) {
		try {
			Archives existingFiche = archivesService.getArchivesById(archives.getId());
			
	        if (file != null && !file.isEmpty()) {
	        	archives.setNomDeFichier(file.getOriginalFilename());
	        	archives.setContenu(file.getBytes());
	        }
	        
	        if (archives.isValidation()) {
	        	existingFiche.setValidation(true);
	        }
	        if (archives.isRefus()) {
	        	existingFiche.setRefus(true);
	        }
	        existingFiche.setCommentaire(archives.getCommentaire());
		archivesService.modifierArchives(existingFiche,archives.getId());
		} catch (Exception e) {
	        e.printStackTrace();
	    }		
		return "redirect:/private/liste_archives";
	}
}



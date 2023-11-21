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

import fr.DecoCN.bll.FicheService;
import fr.DecoCN.bo.Fiche;
import jakarta.websocket.server.PathParam;

@Controller
public class FicheController {

	@Autowired
	FicheService ficheService;
	
	@GetMapping("/nouvelle_fiche")
	String nouvelleFiche(Model model) {
		model.addAttribute("fiche", new Fiche());
		
		return "home/nouvelle_fiche"; 
		
	}
	
	
	@PostMapping("/nouvelle_fiche")
	String nouvelleFiche(@RequestParam("file") MultipartFile file, Fiche fiche) {
		try {
			fiche.setNomDeFichier(file.getOriginalFilename());
			fiche.setContenu(file.getBytes());
			ficheService.CreerFiche(fiche);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/accueil";
	}
	
	@GetMapping("/private/liste_commandes")
	String listeCommandes(Model model) {
		List<Fiche> listeFiches = new ArrayList<Fiche>();
		List<Fiche> listefichesAValide = new ArrayList<Fiche>();
		listeFiches.addAll(ficheService.consulterFiches());
		
		for (Fiche fiche : listeFiches) {
			if (!fiche.isValidation() || !fiche.isRefus()) {
				listefichesAValide.add(fiche);
			}
		}
		
		model.addAttribute("listeFiches", listefichesAValide);
		return "home/liste";
	}
	
	@GetMapping("/private/detail")
	String fichedetail(@PathParam("id") long id, Model model) {
		model.addAttribute("fiche" , ficheService.getFicheById(id));
		return "home/detail";
	}
}

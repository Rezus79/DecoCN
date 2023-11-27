package fr.DecoCN.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.DecoCN.bll.ArchivesService;
import fr.DecoCN.bll.FicheService;
import fr.DecoCN.bo.Archives;
import fr.DecoCN.bo.Fiche;
import jakarta.websocket.server.PathParam;

@Controller
public class FicheController {

	@Autowired
	FicheService ficheService;
	
	@Autowired
	ArchivesService archivesService;
	
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
			if (!fiche.isValidation() && !fiche.isRefus()) {
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
	
	@GetMapping("/private/detail/{id}/download")
    public ResponseEntity<Resource> downloadFiche(@PathVariable long id) {
        Fiche fiche = ficheService.getFicheById(id);

        if (fiche != null && fiche.getContenu() != null) {
            ByteArrayResource resource = new ByteArrayResource(fiche.getContenu());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fiche.getNomDeFichier())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(fiche.getContenu().length)
                    .body(resource);
        } else {
            // Gérer le cas où la fiche ou le contenu n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/private/modifier")
	String modifierFiche(@PathParam("id") long id,Model model) {
		model.addAttribute("fiche", ficheService.getFicheById(id));
		return "home/modifier";
	}
	
	@PostMapping("/private/modifier")
	String modifierFiche(@RequestParam("file") MultipartFile file,Fiche fiche) {
		try {
			Fiche existingFiche = ficheService.getFicheById(fiche.getId());
			
	        if (file != null && !file.isEmpty()) {
	            fiche.setNomDeFichier(file.getOriginalFilename());
	            fiche.setContenu(file.getBytes());
	        }
	        
	        if (fiche.isValidation()) {
	        	existingFiche.setValidation(true);
	        }
	        if (fiche.isRefus()) {
	        	existingFiche.setRefus(true);
	        }
		ficheService.modifierFiche(existingFiche,fiche.getId());
		} catch (Exception e) {
	        e.printStackTrace();
	    }		
		return "redirect:/private/liste_commandes";
	}
	
	@GetMapping("/private/liste_valides")
	String listeValides(Model model) {
		List<Fiche> listeFiches = new ArrayList<Fiche>();
		List<Fiche> listefichesValide = new ArrayList<Fiche>();
		listeFiches.addAll(ficheService.consulterFiches());
		
		for (Fiche fiche : listeFiches) {
			if (fiche.isValidation()) {
				listefichesValide.add(fiche);
			}
		}
		
		model.addAttribute("listeFiches", listefichesValide);
		return "home/liste_valides";
	}
	
	@GetMapping("/private/liste_refus")
	String listeRefuses(Model model) {
		List<Fiche> listeFiches = new ArrayList<Fiche>();
		List<Fiche> listefichesRefus = new ArrayList<Fiche>();
		listeFiches.addAll(ficheService.consulterFiches());
		
		for (Fiche fiche : listeFiches) {
			if (fiche.isRefus()) {
				listefichesRefus.add(fiche);
			}
		}
		
		model.addAttribute("listeFiches", listefichesRefus);
		return "home/liste_refus";
	}
	
	
	
	
	
}

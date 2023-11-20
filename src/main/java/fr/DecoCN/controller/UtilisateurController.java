package fr.DecoCN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.DecoCN.bll.UtilisateurService;
import fr.DecoCN.bo.Utilisateur;



@Controller
public class UtilisateurController {

	
	@Autowired
	UtilisateurService utilisateurService;
	
	@GetMapping("/login")
	String login() {
		return "home/login";
	}
	
	
	@GetMapping("/private/creer")
	String creer(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "home/creer";
	}
	
	@PostMapping("/private/creer")
	public String creer(@ModelAttribute Utilisateur utilisateur) {
		utilisateurService.CreerUtilisateur(utilisateur);
		return "redirect:/private/gerer";
	}
}

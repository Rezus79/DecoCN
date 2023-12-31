package fr.DecoCN.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.DecoCN.bll.UtilisateurService;
import fr.DecoCN.bo.Utilisateur;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;



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
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "nonConnecter");
        cookie.setPath("/"); 
        response.addCookie(cookie);

      
        SecurityContextHolder.clearContext();
		return "redirect:/";
	}
	
	
	@GetMapping("/private/liste_users")
	String listeUsers(Model model) {
		List<Utilisateur> listeUsers = new ArrayList<Utilisateur>();
		List<Utilisateur> listeUsersAdmin = new ArrayList<Utilisateur>();
		
		listeUsers.addAll(utilisateurService.consulterUtilisateurs());
		
		for (Utilisateur user : listeUsers) {
			if(user.getRole().getLibelle().equals("ADMIN")) {
				listeUsersAdmin.add(user);
			}
		}
		
		model.addAttribute("listeUsers", listeUsersAdmin);
		return "home/liste_users";
	}
	
	
	@GetMapping("/private/detail_user")
	String userDetail(@PathParam("id") long id, Model model) {

		model.addAttribute("user" , utilisateurService.getUtilisateurById(id));

		return "home/detail_user";
	}
	
	@GetMapping("/private/modifier_user")
	String userModifier(@PathParam("id") long id, Model model) {

		model.addAttribute("user" , utilisateurService.getUtilisateurById(id));

		return "home/modifier_user";
	}
	
	@PostMapping("/private/modifier_user")
	String userModifier(@PathParam("id") Long id, Utilisateur utilisateur) {
		Utilisateur existingUser = utilisateurService.getUtilisateurById(id);
		existingUser.setEmail(utilisateur.getEmail());
		existingUser.setNom(utilisateur.getNom());
		existingUser.setPrenom(utilisateur.getPrenom());
		utilisateurService.modifierUtilisateur(existingUser, existingUser.getId());
		return "redirect:/private/liste_users";
	}
	
	@GetMapping("/private/supprimer_user")
	String userSupprimer(@PathParam("id") long id, Model model) {
		model.addAttribute("user", utilisateurService.getUtilisateurById(id));
		return "home/supprimer_user";
	}
	
	@PostMapping("/private/supprimer_user")
	String userSupprimer(@PathParam("id") long id) {
		utilisateurService.supprimerUtilisateur(id);
		return "redirect:/private/liste_users";
	}
	
	@GetMapping("/private/change_mdp")
	String changeMdp(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = (User) authentication.getPrincipal();
        String username = userDetails.getUsername(); 
        
        Utilisateur user = utilisateurService.findByEmail(username);
        
        Long IdUser = user.getId();

        
		model.addAttribute("user", utilisateurService.getUtilisateurById(IdUser));
	
		return "home/change_mdp";
	}
	
	
}

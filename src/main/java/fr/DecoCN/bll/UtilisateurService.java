package fr.DecoCN.bll;

import java.util.List;
import java.util.Optional;

import fr.DecoCN.bo.Utilisateur;


public interface UtilisateurService {

	
    Utilisateur CreerUtilisateur(Utilisateur utilisateur);
	
	List<Utilisateur> consulterUtilisateurs();
	
	Optional<Utilisateur> getUtilisateurById(Long id);
	
	Utilisateur findByEmail(String email);
	
	Utilisateur findByNom(String nom);
	
	Utilisateur modifierUtilisateur(Utilisateur utilisateur, Long id);
	
	void supprimerUtilisateur(Long id);
}

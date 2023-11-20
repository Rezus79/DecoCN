package fr.DecoCN.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.DecoCN.bo.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

	
	Utilisateur findByNom(String nom);
	
	Utilisateur findByEmail(String email);
}

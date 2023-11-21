package fr.DecoCN.bll;

import java.util.List;
import java.util.Optional;

import fr.DecoCN.bo.Fiche;


public interface FicheService {

	
	Fiche CreerFiche(Fiche fiche);
	
	List<Fiche> consulterFiches();
	
	Fiche getFicheById(Long id);
	
	Fiche modifierFiche(Fiche fiche, Long id);
	
	void supprimerFiche(Long id);
	

}

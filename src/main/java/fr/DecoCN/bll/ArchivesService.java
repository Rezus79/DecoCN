package fr.DecoCN.bll;

import java.util.List;

import fr.DecoCN.bo.Archives;
import fr.DecoCN.bo.Fiche;

public interface ArchivesService {

	
	Archives CreerArchives(Fiche fiche);
	
	List<Archives> consulterArchives();
	
	Archives getArchivesById(Long id);
	
	Archives modifierArchives(Archives archives, long id);
	
	void supprimerArchives(Long id);

	
}

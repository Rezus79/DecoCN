package fr.DecoCN.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.DecoCN.bo.Archives;
import fr.DecoCN.bo.Fiche;
import fr.DecoCN.dal.ArchivesRepository;

@Service
public class ArchivesServiceImpl implements ArchivesService{

	
	@Autowired
	ArchivesRepository archivesRepository;
	
	public ArchivesServiceImpl(ArchivesRepository archivesRepository) {
		this.archivesRepository = archivesRepository;
	}

	@Override
	public Archives CreerArchives(Fiche fiche) {
		
		Archives archives = new Archives();
		
		 archives.setId(fiche.getId());
		 archives.setEmail(fiche.getEmail());
		 archives.setLongueur(fiche.getLongueur());
		 archives.setLargeur(fiche.getLargeur());
	     archives.setNomDeFichier(fiche.getNomDeFichier());
	     archives.setContenu(fiche.getContenu());
	     archives.setSupport(fiche.getSupport());
	     archives.setValidation(fiche.isValidation());
		return archivesRepository.save(archives);
	}

	@Override
	public List<Archives> consulterArchives() {
		// TODO Auto-generated method stub
		return archivesRepository.findAll();
	}

	@Override
	public Archives getArchivesById(Long id) {
		Optional<Archives> archivesOptional = archivesRepository.findById(id);
		return archivesOptional.orElse(null);
	}

	@Override
	public Archives modifierArchives(Archives archives, long id) {
		// TODO Auto-generated method stub
		return archivesRepository.saveAndFlush(archives);
	}

	@Override
	public void supprimerArchives(Long id) {
		archivesRepository.deleteById(id);
		
	}


	
	
}

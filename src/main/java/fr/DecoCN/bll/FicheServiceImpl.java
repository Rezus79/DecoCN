package fr.DecoCN.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.DecoCN.bo.Fiche;
import fr.DecoCN.dal.FicheRepository;

@Service
public class FicheServiceImpl implements FicheService{

	
	@Autowired
	FicheRepository ficheRepository;
	
	
	
	public FicheServiceImpl(FicheRepository ficheRepository) {
		this.ficheRepository = ficheRepository;
	}

	@Override
	public Fiche CreerFiche(Fiche fiche) {
		// TODO Auto-generated method stub
		return ficheRepository.save(fiche);
	}

	@Override
	public List<Fiche> consulterFiches() {
		// TODO Auto-generated method stub
		return ficheRepository.findAll();
	}

	@Override
	public Fiche getFicheById(Long id) {
		Optional<Fiche> ficheOptional = ficheRepository.findById(id);
		return ficheOptional.orElse(null);
	}

	@Override
	public Fiche modifierFiche(Fiche fiche, Long id) {
		// TODO Auto-generated method stub
		return ficheRepository.saveAndFlush(fiche);
	}

	@Override
	public void supprimerFiche(Long id) {
		ficheRepository.deleteById(id);
		
	}

}

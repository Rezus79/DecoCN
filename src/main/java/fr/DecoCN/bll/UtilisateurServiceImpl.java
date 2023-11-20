package fr.DecoCN.bll;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.DecoCN.bo.Utilisateur;
import fr.DecoCN.dal.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	@Override
	public Utilisateur CreerUtilisateur(Utilisateur utilisateur) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		utilisateur.setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public List<Utilisateur> consulterUtilisateurs() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}

	@Override
	public Optional<Utilisateur> getUtilisateurById(Long id) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findById(id);
	}

	@Override
	public Utilisateur findByEmail(String email) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByEmail(email);
	}

	@Override
	public Utilisateur findByNom(String nom) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByNom(nom);
	}

	@Override
	public Utilisateur modifierUtilisateur(Utilisateur utilisateur, Long id) {
		Utilisateur utilisateurDB = utilisateurRepository.findById(id).get();

		if (Objects.nonNull(utilisateur.getNom()) && !"".equalsIgnoreCase(utilisateur.getNom())) {
			utilisateurDB.setNom(utilisateur.getNom());
		}
		if (Objects.nonNull(utilisateur.getPrenom()) && !"".equalsIgnoreCase(utilisateur.getPrenom())) {
			utilisateurDB.setPrenom(utilisateur.getPrenom());
		}
		if (Objects.nonNull(utilisateur.getRole())) {
			utilisateurDB.setRole(utilisateur.getRole());
		}
		if (Objects.nonNull(utilisateur.getEmail()) && !"".equalsIgnoreCase(utilisateur.getEmail())) {
			utilisateurDB.setEmail(utilisateur.getEmail());
		}
		return utilisateurRepository.save(utilisateurDB);
	}

	@Override
	public void supprimerUtilisateur(Long id) {
		utilisateurRepository.deleteById(id);
		
	}

}

package fr.DecoCN.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.DecoCN.bo.Fiche;

@Repository
public interface FicheRepository extends JpaRepository<Fiche, Long>{

}

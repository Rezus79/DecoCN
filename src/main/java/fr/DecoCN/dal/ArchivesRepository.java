package fr.DecoCN.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.DecoCN.bo.Archives;

@Repository
public interface ArchivesRepository extends JpaRepository<Archives, Long>{

	

}

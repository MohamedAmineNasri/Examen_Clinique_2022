package tn.esprit.examenclinique2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examenclinique2022.entity.RendezVous;
@Repository

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    //another way to do getNbrRendezVousMedecin
    int countByMedecins_IdMedecin(Long idMedecin);

}

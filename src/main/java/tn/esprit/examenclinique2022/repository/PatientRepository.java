package tn.esprit.examenclinique2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examenclinique2022.entity.Patient;
@Repository

public interface PatientRepository extends JpaRepository<Patient,Long> {
}

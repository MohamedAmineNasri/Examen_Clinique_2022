package tn.esprit.examenclinique2022.service;

import tn.esprit.examenclinique2022.entity.*;

import java.util.Date;
import java.util.List;

public interface IService {
    Clinique addClinique (Clinique clinique);
    Medecin addMedecinAndAssignToClinique (Medecin medecin, Long cliniqueId);
    public Patient addPatient(Patient patient);
    public void addRDVAndAssignMedAndPatient(RendezVous rdv, Long idMedecin, Long idPatient);

    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite);
    public int getNbrRendezVousMedecin(Long idMedecin);
    public int getRenenuMedecin (Long idMedecin, Date startDate, Date endDate);
}

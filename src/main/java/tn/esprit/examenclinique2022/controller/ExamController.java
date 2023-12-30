package tn.esprit.examenclinique2022.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examenclinique2022.entity.*;
import tn.esprit.examenclinique2022.service.ServiceImp;

import java.util.Date;
import java.util.List;

@RestController
public class ExamController {
    @Autowired
    private ServiceImp serviceImp;
    @PostMapping("/addClinique")
    public Clinique addClinique(@RequestBody Clinique clinique){
        return serviceImp.addClinique(clinique);
    }
    @PostMapping("/addPatient")
    public Patient addPatient(@RequestBody Patient patient){
        return serviceImp.addPatient(patient);
    }
    @PostMapping("/addMedecinAndAssignToClinique/{cliniqueId}")
    public Medecin addMedecinAndAssignToClinique(@RequestBody Medecin medecin,@PathVariable Long cliniqueId) {
        return serviceImp.addMedecinAndAssignToClinique(medecin,cliniqueId);
    }
    @PostMapping("/addRDVAndAssignMedAndPatient/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody RendezVous rdv,@PathVariable Long idMedecin,@PathVariable Long idPatient) {
         serviceImp.addRDVAndAssignMedAndPatient(rdv,idMedecin,idPatient);
    }
    @GetMapping("/getRendezVousByCliniqueAndSpecialite/{idClinique}/{specialite}")
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(@PathVariable Long idClinique,@PathVariable Specialite specialite) {
       return serviceImp.getRendezVousByCliniqueAndSpecialite(idClinique,specialite);
    }

    @GetMapping("/getNbrRendezVousMedecin/{idMedecin}")
    public int getNbrRendezVousMedecin(@PathVariable Long idMedecin) {
        return serviceImp.getNbrRendezVousMedecin(idMedecin);
    }

    @GetMapping("/getRenenuMedecin/{idMedecin}/{startDate}/{endDate}")
    public int getRenenuMedecin(@PathVariable Long idMedecin, @PathVariable
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)  {
        return serviceImp.getRenenuMedecin(idMedecin,startDate,endDate);
    }


}

package tn.esprit.examenclinique2022.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examenclinique2022.entity.*;
import tn.esprit.examenclinique2022.repository.CliniqueRepository;
import tn.esprit.examenclinique2022.repository.MedecinRepository;
import tn.esprit.examenclinique2022.repository.PatientRepository;
import tn.esprit.examenclinique2022.repository.RendezVousRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ServiceImp implements IService{
    @Autowired//pour injection
    private CliniqueRepository cliniqueRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Override
    public Clinique addClinique(Clinique clinique) {
        return cliniqueRepository.save(clinique);
    }

    @Override
    public Medecin addMedecinAndAssignToClinique(Medecin medecin, Long cliniqueId) {
        //lina relation ManyToMany
        //ki ya3tina faza hadi mta3 assignToClinique ma3naha fazit id
        //lazimna nlawjo 3lih sa3a (mawjoud wala le)
        Clinique c = cliniqueRepository.findById(cliniqueId).orElse(null);
        //sna3na lista firgha mta3 medecin 7atina fiha medecin l3taholi fih paramètre
        List<Medecin>medecinList=new ArrayList<>();
        medecinList.add(medecin);
        //Fih cas mta3 clinique mafih 7ata medecin ywali yajoute lista ili fiha medecin wa7id
        //Fama faza manajmouch na3mlo .add l getMedecins fargha
        //3malna add lil c (clinique) khatir parent mahouch child
        if (c.getMedecins()==null) {
            //Fih cas mta3 clinique yabda fargha , bich n7ot lista fargha fih medecin wa7id barka w naffectilo lista hadika

            c.setMedecins(medecinList);
        }
        else {//cas ki yabda medecin ili 3indo akthir min medecin
            c.getMedecins().add(medecin);
        }
        //Fih likhir n save medecin bich ysir ajour
        //Dima affectation tsir 3la parent
        return medecinRepository.save(medecin);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void addRDVAndAssignMedAndPatient(RendezVous rdv, Long idMedecin, Long idPatient) {
        //kin makhdmitich n7i void w 7ot return
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);
        Patient patient = patientRepository.findById(idPatient).orElse(null);
        //sahla khatir rdv howa parent w 3idna relation ManyToOne fih zouz m3a medecins w patient
        rdv.setMedecins(medecin);
        rdv.setPatient(patient);
        rendezVousRepository.save(rdv);
    }

    @Override
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite) {
        Clinique clinique = cliniqueRepository.findById(idClinique).orElse(null);
        //Faha les rendezvous lkol
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        List<RendezVous> resultat= new ArrayList<>();
        //Ena ma3ndich relation direct mabinit RendezVous w Clinique ama 3idna relation mabinit RendezVous w Medecin
        // w min Medecin w Clinique
        for (RendezVous r:rendezVousList){
            //condition bich nodmin ili clinique ili nlawj 3lih mahich fargha
            if (r.getMedecins().getCliniques()!=null) {
               //medecin 3indo barcha clinique ena lazimni nthabit kin medecin ykhdim fih clinique wala le
                for (Clinique c:r.getMedecins().getCliniques()) {
                    if(clinique.equals(c)&& r.getMedecins().getSpecialite().equals(specialite)){
                        resultat.add(r);
                    }
                }
            }
        }
        return  resultat;
    }

    @Override
    public int getNbrRendezVousMedecin(Long idMedecin) {
        int nb=0;
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        for (RendezVous r:rendezVousList){
            if (r.getMedecins().equals(medecin)){
                nb++;
            }
        }
        return nb;
        //twali star barka
        //return rendezVousRepository.countByMedecins_IdMedecin(idMedecin);//keywords
    }

    @Override
    public int getRenenuMedecin(Long idMedecin, Date startDate, Date endDate) {
        int nb=0;
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        for (RendezVous r:rendezVousList){
            if(r.getMedecins().equals(medecin) && r.getDateRDV().after(startDate) && r.getDateRDV().before(endDate)){
                nb++;
            }
        }
        return medecin.getPrixConsultation()*nb;
    }

    //manich bich nista3mloha fih controller hadaka 3liha zayid n7otha fih interface
    //schedule method
    @Scheduled(cron = "*/30 * * * * *")
    public void retrieveRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        for(RendezVous r:rendezVousList) {
            if (r.getDateRDV().after(new Date())) {//new Date today date
                log.info("la liste des RendezVous :" + r.getDateRDV() +"Medecin :" + r.getMedecins() +"Patient :" +
                r.getPatient().getNomPatient());
            }
        }
    }
}

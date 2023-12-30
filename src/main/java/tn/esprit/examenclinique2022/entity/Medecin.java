package tn.esprit.examenclinique2022.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedecin;
    private String nomMedecin;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private int telephone;
    private int prixConsultation;

    @JsonIgnore
    @ManyToMany(mappedBy = "medecins")
    private List<Clinique>cliniques;

    @OneToMany(mappedBy = "medecins")
    @JsonIgnore
    private List<RendezVous>rendezVousList;
}

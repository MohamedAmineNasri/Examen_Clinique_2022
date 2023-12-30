package tn.esprit.examenclinique2022.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Clinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClinique;
    private String nomClinique;
    private String adresse;
    private int telephone;

    @ManyToMany
    private List<Medecin>medecins;
}

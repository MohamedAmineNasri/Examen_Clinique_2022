package tn.esprit.examenclinique2022.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRDV;
    private Date dateRDV;
    private String remarque;

    @ManyToOne
    private Medecin medecins;

    @ManyToOne
    private Patient patient;
}

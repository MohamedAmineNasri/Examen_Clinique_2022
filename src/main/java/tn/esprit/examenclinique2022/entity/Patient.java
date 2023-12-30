package tn.esprit.examenclinique2022.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPatient;
    private String nomPatient;
    private int telephone;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<RendezVous>rendezVousList;
}

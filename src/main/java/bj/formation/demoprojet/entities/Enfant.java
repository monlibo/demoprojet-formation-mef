package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="t_enfant")
public class Enfant implements Serializable {


    @Id()

    @Column(name = "enfan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "enfan_nom")
    private String nom;
    @Column(name = "enfan_prenom")
    private String prenom;
    @Column(name = "enfan_date_naissance")
    private Date dateNaissance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="enfan_niveau_enfant_id", referencedColumnName = "niveau_enfan_code")
    private NiveauEnfant niveauEnfant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="enfan_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;



    public void setAgent(Agent agent) {
        this.agent = agent;
    }

}

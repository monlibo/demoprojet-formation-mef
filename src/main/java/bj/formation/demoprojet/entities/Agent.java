package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_agent")
public class Agent implements Serializable {
    @Id
    @Column(name = "agen_matricule", unique = true)
    private String matricule;

    @Column(name = "agen_nom")
    private String nom;

    @Column(name = "agen_prenom")
    private String prenom;

    @Column(name = "agen_date_naissance")
    private Date dateNaissance;

    @Column(name = "agen_indice")
    private int indice;

    @Column(name = "agen_salaire_base")
    private int salaireBase;

    @Column(name = "agen_allocation_familiale")
    private int allocationFamiliale;

    @Column(name = "agen_nbre_enfant")
    private int nbreEnfant;

    @Column(name = "agen_actif")
    private Boolean actif;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<AgentGrade> listeAgentGrades;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<Enfant> listeEnfants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<AgentElementPaie> listeElementPaies;


    public List<Grade> grades() {
        return listeAgentGrades.stream().map(
                AgentGrade::getGrade
        ).toList();
    }

}

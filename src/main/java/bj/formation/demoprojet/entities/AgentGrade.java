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
@Entity(name="t_agent_grade")
public class AgentGrade implements Serializable {


    @Id

    @Column(name = "agen_grade_id")
    private int id;
    @Column(name = "agen_grade_date_debut")
    private Date dateDebut;
    @Column(name = "agen_grade_date_fin")
    private Date dateFin;

    //liaison avec agent
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="aggr_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;

     //liaison avec grade
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="aggr_grad_code", referencedColumnName = "grad_code")
    private Grade grade;
}

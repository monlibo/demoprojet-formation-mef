package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="t_agent_element_paie")
public class AgentElementPaie implements Serializable {



    @Id

    @Column(name = "agen_element_paie_id")
    private int id;
    @Column(name = "agen_element_paie_montant")
    private int montant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="agep_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;

    //liaison avec grade
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="agep_elemen_paie_code", referencedColumnName = "elemen_paie_code")
    private ElementPaie elementpaie;

}

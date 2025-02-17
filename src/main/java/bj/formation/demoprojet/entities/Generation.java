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
@Entity(name="t_generation")
public class Generation implements Serializable {
    @Id

    @Column(name = "generation_id")
    private int id;
    @Column(name = "generation_montant")
    private int montant;
    @Column(name = "generation_echeance")
    private Date echeance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="gener_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;
}

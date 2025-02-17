package bj.formation.demoprojet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="t_niveau_enfant")
public class NiveauEnfant implements Serializable {

    @Id

    @Column(name = "niveau_enfan_code")
    private String code;
    @Column(name = "niveau_enfan_libelle")
    private int libelle;
    @Column(name = "niveau_enfan_age_limit")
    private int ageLimit;

    public NiveauEnfant(String code) {
        this.code = code;
    }
}

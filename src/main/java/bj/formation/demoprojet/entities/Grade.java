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
@Entity(name="t_grade")

public class Grade implements Serializable {

    @Id

    @Column(name = "grad_code", length=10)
    private String code;
    @Column(name = "grad_libelle", length=150)
    private String libelle;
    @Column(name = "grad_indice")
    private int indice;

    public Grade(String code) {
        this.code = code;
    }
}

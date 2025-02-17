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
@Entity(name="t_element_paie")
public class ElementPaie implements Serializable {

    @Id

    @Column(name = "elemen_paie_code")
    private String code;
    @Column(name = "elemen_paie_libelle")
    private String libelle;


    public ElementPaie(String code) {
        this.code = code;
    }


}

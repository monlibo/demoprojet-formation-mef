package bj.formation.demoprojet.dtos.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record GradeDto(
        String code,
        String libelle,
        int indice
) {
}

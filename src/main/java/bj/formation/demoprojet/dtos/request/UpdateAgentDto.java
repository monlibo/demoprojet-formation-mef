package bj.formation.demoprojet.dtos.request;

import java.util.List;

public record UpdateAgentDto(
        String nom,
        String prenom,
        String dateNaissance,
        int indice,
        int salaireBase,
        int allocationFamiliale,
        int nbreEnfant,
        boolean actif,
        String grade
) {
}

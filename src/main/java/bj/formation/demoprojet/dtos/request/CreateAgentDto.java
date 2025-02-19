package bj.formation.demoprojet.dtos.request;

import lombok.Builder;

import java.util.List;


public record CreateAgentDto(
        String matricule,
        String nom,
        String prenom,
        String dateNaissance,
        int indice,
        int salaireBase,
        int allocationFamiliale,
        int nbreEnfant,
        boolean actif,
        List<EnfantDto> enfants,
        String grade
) {
}


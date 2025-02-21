package bj.formation.demoprojet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record AgentDto(
        @NotBlank(message = "Le matricule ne peut pas être vide")
        @Pattern(regexp = "^[0-9]+$", message = "Le matricule doit contenir uniquement des chiffres")
        String matricule,
        String nom,
        String prenom,
        String dateNaissance,
        int indice,
        int salaireBase,
        int allocationFamiliale,
        int nbreEnfant,
        boolean actif,
        List<EnfantDto> enfants

) {
}


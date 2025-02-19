package bj.formation.demoprojet.dtos.response;

import bj.formation.demoprojet.dtos.request.EnfantDto;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.Grade;
import lombok.Builder;

import java.util.List;
@Builder
public record AgentResponse(
        String matricule,
        String nom,
        String prenom,
        String dateNaissance,
        int indice,
        int salaireBase,
        int allocationFamiliale,
        int nbreEnfant,
        boolean actif,
        List<EnfantResponse> enfants,
        List<Grade> grades
) {
}

package bj.formation.demoprojet.dtos.response;

import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.NiveauEnfant;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Builder
public record EnfantResponse(
        int id,
        String nom,
        String prenom,
        Date dateNaissance
) {
}

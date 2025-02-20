package bj.formation.demoprojet.dtos.requests;

public record UpdateAgentRequest(
        String nom,
        String prenom,
        String dateNaissance,
        Integer indice,
        Integer salaireBase,
        Integer allocationFamiliale,
        Integer nbreEnfant,
        Boolean actif
) {}
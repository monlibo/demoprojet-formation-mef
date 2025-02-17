package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.request.CreateAgentWithEnfantRequest;
import bj.formation.demoprojet.dtos.request.EnfantDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentGrade;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.EnfantRepository;
import bj.formation.demoprojet.repositories.GradeRepository;
import bj.formation.demoprojet.repositories.AgentGradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final EnfantRepository enfantRepository;


    public AgentService(AgentRepository agentRepository,
                        EnfantRepository enfantRepository
    ) {
        this.agentRepository = agentRepository;
        this.enfantRepository = enfantRepository;

    }

    // Transactionnelle pour s'assurer que tout est bien sauvegardé ensemble
//    @Transactional
    public Agent createAgent(CreateAgentWithEnfantRequest payload) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);


        Agent agent = new Agent();
        agent.setMatricule(payload.matricule());
        agent.setNom(payload.nom());
        agent.setPrenom(payload.prenom());
        agent.setDateNaissance(formatter.parse(payload.dateNaissance()));
        agent.setIndice(payload.indice());
        agent.setSalaireBase(payload.salaireBase());
        agent.setAllocationFamiliale(payload.allocationFamiliale());
        agent.setNbreEnfant(payload.nbreEnfant());
        agent.setActif(payload.actif());


        // Association des enfants à l'agent et sauvegarde

        Set<Enfant> enfantSet = new HashSet<>();

//        for (EnfantDto item : payload.enfants()) {
//            Enfant enfant = new Enfant();
//            enfant.setNom(item.nom());
//            enfant.setPrenom(item.prenom());
//            enfant.setDateNaissance(formatter.parse(item.dateNaissance()));
//
//            enfantSet.add(enfant);
//        }
//
//        agent.setListeEnfants(enfantSet);

       return  agentRepository.saveAndFlush(agent);
    }
}

package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.AgentDto;
import bj.formation.demoprojet.dtos.EnfantDto;
import bj.formation.demoprojet.dtos.requests.UpdateAgentRequest;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentGrade;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.EnfantRepository;
import bj.formation.demoprojet.repositories.GradeRepository;
import bj.formation.demoprojet.repositories.AgentGradeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final EnfantRepository enfantRepository;
    private final GradeRepository gradeRepository;
    private final AgentGradeRepository agentGradeRepository;
    private static final String[] CATEGORIES = {"A1", "A2", "B1", "B2"};
    private static final Random RANDOM = new Random();

    private final NotificationService notificationService;


    public AgentService(AgentRepository agentRepository, EnfantRepository enfantRepository, GradeRepository gradeRepository, AgentGradeRepository agentGradeRepository, NotificationService notificationService) {
        this.agentRepository = agentRepository;
        this.enfantRepository = enfantRepository;
        this.gradeRepository = gradeRepository;
        this.agentGradeRepository = agentGradeRepository;
        this.notificationService = notificationService;
    }

    public Agent createAgent(AgentDto payload) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        Agent existingAgent = agentRepository.findByMatricule(payload.matricule());
        if (existingAgent != null) {
            throw new IllegalArgumentException("Un agent avec ce matricule existe déjà.");
        }

        // Création de l'agent
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

        // Création et association des enfants
        Set<Enfant> enfants = new HashSet<>();
        for (EnfantDto enfantDto : payload.enfants()) {
            Enfant enfant = new Enfant();
            enfant.setNom(enfantDto.nom());
            enfant.setPrenom(enfantDto.prenom());
            enfant.setDateNaissance(formatter.parse(enfantDto.dateNaissance()));
            enfant.setAgent(agent);
            enfants.add(enfant);
        }
        agent.setListeEnfants(enfants);
        agent = agentRepository.saveAndFlush(agent);
        enfantRepository.saveAll(enfants);

        Grade grade = findOrCreateGrade(agent.getIndice());

        AgentGrade agentGrade = createAgentGrade(agent, grade);
        agentGradeRepository.save(agentGrade);

        notificationService.sendMessage(agent.getMatricule());

        return agent;
    }

private Grade findOrCreateGrade(int indice) {
    Grade grade = gradeRepository.findByIndice(indice);
    if (grade == null) {
        String categorieAleatoire = CATEGORIES[RANDOM.nextInt(CATEGORIES.length)];
        grade = new Grade(categorieAleatoire + "-" + indice);
        grade.setLibelle("Grade " + categorieAleatoire + "-" + indice);
        grade.setIndice(indice);
        gradeRepository.save(grade);
    }
    return grade;
}

    private AgentGrade createAgentGrade(Agent agent, Grade grade) {
        AgentGrade agentGrade = new AgentGrade();
        agentGrade.setAgent(agent);
        agentGrade.setGrade(grade);
        agentGrade.setDateDebut(new Date());
        agentGrade.setDateFin(new Date());
        return agentGrade;
    }

    public Agent getAgentByMatricule(String matricule) {
        return agentRepository.findByMatricule(matricule);
    }

    public Agent updateAgent(String matricule, UpdateAgentRequest payload) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        // Rechercher l'agent dans la base
        Agent agent = agentRepository.findByMatricule(matricule);
        if (agent == null) {
            throw new RuntimeException("Agent introuvable");
        }

        // Mise à jour des champs
        if (payload.nom() != null) {
            agent.setNom(payload.nom());
        }
        if (payload.prenom() != null) {
            agent.setPrenom(payload.prenom());
        }
        if (payload.dateNaissance() != null) {
            agent.setDateNaissance(formatter.parse(payload.dateNaissance()));
        }
        if (payload.indice() != null) {
            agent.setIndice(payload.indice());
        }
        if (payload.salaireBase() != null) {
            agent.setSalaireBase(payload.salaireBase());
        }
        if (payload.allocationFamiliale() != null) {
            agent.setAllocationFamiliale(payload.allocationFamiliale());
        }
        if (payload.nbreEnfant() != null) {
            agent.setNbreEnfant(payload.nbreEnfant());
        }
        if (payload.actif() != null) {
            agent.setActif(payload.actif());
        }
        return agentRepository.save(agent);
    }

    public Page<Agent> getAllAgents(Pageable pageable) {
        return agentRepository.findAll(pageable);
    }

    public List<Agent> getAgentsWithLowestIndice() {
        return agentRepository.findAgentsWithLowestIndice();
    }

    public List<Agent> getAgentsWithHighstIndice() {
        return agentRepository.findAgentsWithHighsIndice();
    }


}

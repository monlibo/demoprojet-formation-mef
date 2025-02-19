package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.request.CreateAgentDto;
import bj.formation.demoprojet.dtos.request.EnfantDto;
import bj.formation.demoprojet.dtos.request.UpdateAgentDto;
import bj.formation.demoprojet.dtos.response.AgentResponse;
import bj.formation.demoprojet.dtos.response.EnfantResponse;
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
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final EnfantRepository enfantRepository;
    private final AgentGradeRepository agentGradeRepository;
    private final GradeRepository gradeRepository;

    public AgentService(AgentRepository agentRepository,
                        EnfantRepository enfantRepository,
                        AgentGradeRepository agentGradeRepository,
                        GradeRepository gradeRepository
    ) {
        this.agentRepository = agentRepository;
        this.enfantRepository = enfantRepository;
        this.agentGradeRepository = agentGradeRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<AgentResponse> findAllPaginated() {
        return list(agentRepository.findAll(Pageable.ofSize(10)).getContent());
    }

    public List<AgentResponse> findAll() {
        return list(agentRepository.getAll());
    }

    @Transactional
    public String create(CreateAgentDto payload) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        Agent existingAgent = agentRepository.findById(payload.matricule()).orElse(null);
        Grade existingGrade = gradeRepository.findById(payload.grade()).orElse(null);

        if (existingGrade == null) {
            return "Grade with code " + payload.grade() + " not found";
        }

        if (existingAgent != null) {
            return "Agent with matricule " + payload.matricule() + " already exists";
        }

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

        Set<Enfant> enfantSet = new HashSet<>();
        agentRepository.saveAndFlush(agent);

        for (EnfantDto item : payload.enfants()) {
            Enfant enfant = new Enfant();
            enfant.setNom(item.nom());
            enfant.setPrenom(item.prenom());
            enfant.setDateNaissance(formatter.parse(item.dateNaissance()));
            enfant.setAgent(agent);
            enfantSet.add(enfant);
        }

        enfantRepository.saveAllAndFlush(enfantSet);

        AgentGrade agentGrade = new AgentGrade();
        agentGrade.setDateDebut(LocalDateTime.now().toLocalDate());
        agentGrade.setAgent(agent);
        agentGrade.setGrade(existingGrade);
        agentGradeRepository.save(agentGrade);

        return "Agent created with matricule " + payload.matricule();
    }

    @Transactional
    public String update(UpdateAgentDto payload, String matricule) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        Agent existingAgent = agentRepository.findById(matricule).orElse(null);

        if (existingAgent == null) {
            return "Agent with matricule " + payload.nom() + " not found";
        }

        existingAgent.setNom(payload.nom());
        existingAgent.setPrenom(payload.prenom());
        existingAgent.setDateNaissance(formatter.parse(payload.dateNaissance()));
        existingAgent.setIndice(payload.indice());
        existingAgent.setSalaireBase(payload.salaireBase());
        existingAgent.setAllocationFamiliale(payload.allocationFamiliale());
        existingAgent.setNbreEnfant(payload.nbreEnfant());
        existingAgent.setActif(payload.actif());

        agentRepository.saveAndFlush(existingAgent);

        return "Agent " + matricule + " updated successfully ";
    }

    public List<AgentResponse> list(List<Agent> items) {
        return items.stream().map(
                this::toResponse
        ).toList();
    }

    public AgentResponse findByMatricule(String matricule) {
        return  agentRepository.findById(matricule).map(this::toResponse).orElse(null);
    }

    public AgentResponse toResponse(Agent agent) {
        return AgentResponse.builder()
                .actif(agent.getActif())
                .allocationFamiliale(agent.getAllocationFamiliale())
                .dateNaissance(agent.getDateNaissance().toString())
                .indice(agent.getIndice())
                .matricule(agent.getMatricule())
                .nom(agent.getNom())
                .prenom(agent.getPrenom())
                .salaireBase(agent.getSalaireBase())
                .enfants(
                        agent.getListeEnfants().stream().map(
                                (enfant) -> EnfantResponse.builder()
                                        .nom(enfant.getNom())
                                        .prenom(enfant.getPrenom())
                                        .dateNaissance(enfant.getDateNaissance()).build()).toList()
                )
                .grades(agent.grades())
                .build();
    }

}

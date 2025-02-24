package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.AgentDto;
import bj.formation.demoprojet.dtos.requests.UpdateAgentRequest;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.services.AgentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/create")
    public ResponseEntity<?> creerAgentAvecEnfantsEtGrades(@Valid @RequestBody AgentDto payload) {
        try {
            agentService.createAgent(payload);
            return response("Agent créé avec succès");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (ParseException ex) {
            return ResponseEntity.status(500).body("Erreur de format de date : " + ex.getMessage());
        }
    }


    @GetMapping("/{matricule}")
    public ResponseEntity<Agent> getAgentByMatricule(@Valid @PathVariable String matricule) {
        Agent agent = agentService.getAgentByMatricule(matricule);
        if (agent != null) {
            return ResponseEntity.ok(agent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<String> updateAgent(@Valid
            @PathVariable("matricule") String matricule,
            @RequestBody UpdateAgentRequest payload
    ) {
        try {
            agentService.updateAgent(matricule, payload);
            return ResponseEntity.ok("Agent mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : Agent introuvable ou mise à jour échouée");
        }
    }

    @GetMapping
    public ResponseEntity<Page<Agent>> getAllAgents(
            @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agent> agents = agentService.getAllAgents(pageable);
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/lowest-indice")
    public ResponseEntity<?> getAgentsWithLowestIndice() {
        List<Agent> agents = agentService.getAgentsWithLowestIndice();
        return response(agents, "Agents avec l'indice le plus bas récupérés avec succès");
    }

    @GetMapping("/highest-indice")
    public ResponseEntity<?> getAgentsWithHighstIndice() {
        List<Agent> agents = agentService.getAgentsWithHighstIndice();
        return response(agents, "Agents avec l'indice le plus haut récupérés avec succès");
    }


}

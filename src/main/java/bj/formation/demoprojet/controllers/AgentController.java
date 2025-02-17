package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.request.CreateAgentWithEnfantRequest;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.services.AgentService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/create")
    public ResponseEntity<Agent>  creerAgentAvecEnfantsEtGrades(@RequestBody CreateAgentWithEnfantRequest payload, HttpServletResponse response
    ) throws ParseException {


        return ResponseEntity.ok(agentService.createAgent(payload));
    }
}

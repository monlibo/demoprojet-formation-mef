package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.request.CreateAgentDto;
import bj.formation.demoprojet.dtos.request.UpdateAgentDto;
import bj.formation.demoprojet.dtos.response.HttpResponse;
import bj.formation.demoprojet.services.AgentService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/agents")
public class AgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public ResponseEntity<?> list() {
        return response(agentService.findAllPaginated(), "Agents retrieved successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateAgentDto payload
    ) throws ParseException {
        return response(agentService.create(payload), "Agent created successfully");
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<?> get(@PathVariable String matricule
    ) throws ParseException {
        return response(agentService.findByMatricule(matricule), "Agent created successfully");
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<?> update(@RequestBody UpdateAgentDto payload, @PathVariable String matricule
    ) throws ParseException {
        return response(agentService.update(payload, matricule), "Agent created successfully");
    }
}

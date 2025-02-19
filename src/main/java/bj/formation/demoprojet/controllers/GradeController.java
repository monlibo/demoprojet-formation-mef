package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.request.GradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GradeDto grade) {
        return ResponseEntity.ok(gradeService.saveGrade(grade));
    }
}

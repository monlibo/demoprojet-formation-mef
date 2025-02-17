package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.services.GradeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }
   @PostMapping
  public String create(@RequestBody Grade grade){
      String result =null;
    grade.setIndice((int)Math.random()*10);
      Grade grad = gradeService.saveGrade(grade);
     if(grad !=null) result="Ok";
      return result;

   }


}

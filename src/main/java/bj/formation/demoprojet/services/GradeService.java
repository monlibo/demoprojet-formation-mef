package bj.formation.demoprojet.services;

import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public  Grade saveGrade(Grade grade)
    {
        return gradeRepository.save(grade);

    }

//    public List<Grade> findAll()
//    {
//        return gradeRepository.findAll();
//
//    }

    // Méthode pour récupérer tous les grades
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }
}

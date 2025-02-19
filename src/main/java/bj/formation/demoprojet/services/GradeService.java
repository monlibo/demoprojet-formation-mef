package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.request.GradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.repositories.GradeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public String saveGrade(GradeDto payload) {
        Grade existingGrade = gradeRepository.findById(payload.code()).orElse(null);

        if (existingGrade != null) {
            return "Grade with code " + payload.code() + " already exists";
        }

        Grade grade = new Grade(payload.code(), payload.libelle(), payload.indice());
        gradeRepository.save(grade);

        return "Grade saved successfully";
    }
}

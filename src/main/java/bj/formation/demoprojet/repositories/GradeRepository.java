package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    Grade findByIndice(int indice);
}

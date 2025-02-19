package bj.formation.demoprojet.repositories;


import bj.formation.demoprojet.entities.AgentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentGradeRepository extends JpaRepository<AgentGrade, Integer> {
    @Query("SELECT a FROM #{#entityName} a WHERE agent.matricule = :matricule")
    List<AgentGrade> findByMatricule(@Param("matricule") String matricule);
}

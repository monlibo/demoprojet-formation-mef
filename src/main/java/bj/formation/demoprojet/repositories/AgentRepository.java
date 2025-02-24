package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {
    @Query("SELECT a FROM t_agent a WHERE a.matricule = ?1")
    Agent findByMatricule(String matricule);

    @Query(value = """
        SELECT a.*
        FROM t_agent a
        JOIN t_agent_grade ag ON a.agen_matricule = ag.aggr_agen_matricule
        JOIN t_grade g ON ag.aggr_grad_code = g.grad_code
        WHERE g.grad_indice = (SELECT MIN(g2.grad_indice) FROM t_grade g2 JOIN t_agent_grade ag2 on g2.grad_code = ag2.aggr_grad_code)
    """, nativeQuery = true)
    List<Agent> findAgentsWithLowestIndice();

    @Query(value = """
        SELECT a.*
        FROM t_agent a
        JOIN t_agent_grade ag ON a.agen_matricule = ag.aggr_agen_matricule
        JOIN t_grade g ON ag.aggr_grad_code = g.grad_code
        WHERE g.grad_indice = (SELECT MAX(g2.grad_indice) FROM t_grade g2 JOIN t_agent_grade ag2 on g2.grad_code = ag2.aggr_grad_code)
    """, nativeQuery = true)
    List<Agent> findAgentsWithHighsIndice();

}

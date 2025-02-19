package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String>  {
    @Query("SELECT a FROM t_agent a")
    List<Agent> getAll();
}

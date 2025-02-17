package bj.formation.demoprojet.repositories;


import bj.formation.demoprojet.entities.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Integer> {
}

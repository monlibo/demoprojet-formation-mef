package bj.formation.demoprojet.repositories;


import bj.formation.demoprojet.entities.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {
}

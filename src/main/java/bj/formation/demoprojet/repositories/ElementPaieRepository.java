package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.ElementPaie;
import bj.formation.demoprojet.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementPaieRepository extends JpaRepository<ElementPaie, String> {
}

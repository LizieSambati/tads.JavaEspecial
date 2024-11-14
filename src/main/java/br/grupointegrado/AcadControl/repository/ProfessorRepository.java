package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}

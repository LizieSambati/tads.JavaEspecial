package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}

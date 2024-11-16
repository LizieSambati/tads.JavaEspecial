package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}

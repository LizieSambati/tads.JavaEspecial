package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByTurmaId(Integer turmaId);
    List<Matricula> findByAlunoId(Integer id);
}

package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByMatriculaId(Integer alunoId);
    List<Nota> findByDisciplinaId(Integer id);
}

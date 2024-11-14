package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}

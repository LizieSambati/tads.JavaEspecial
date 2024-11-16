package br.grupointegrado.AcadControl.repository;

import br.grupointegrado.AcadControl.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}

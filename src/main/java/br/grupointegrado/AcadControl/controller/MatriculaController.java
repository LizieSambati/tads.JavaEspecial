package br.grupointegrado.AcadControl.controller;


import br.grupointegrado.AcadControl.dto.MatriculaRequestDTO;
import br.grupointegrado.AcadControl.model.Aluno;
import br.grupointegrado.AcadControl.model.Curso;
import br.grupointegrado.AcadControl.model.Matricula;
import br.grupointegrado.AcadControl.model.Turma;
import br.grupointegrado.AcadControl.repository.AlunoRepository;
import br.grupointegrado.AcadControl.repository.MatriculaRepository;
import br.grupointegrado.AcadControl.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")

public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public Matricula save(@RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        matricula.setAluno(aluno);
        Turma turma = turmaRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        matricula.setTurma(turma);

        return this.repository.save(matricula);
    }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Integer id,
                            @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        matricula.setAluno(aluno);
        Turma turma = turmaRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        matricula.setTurma(turma);

        return this.repository.save(matricula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        this.repository.delete(matricula);
    }

}

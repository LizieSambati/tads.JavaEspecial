package br.grupointegrado.AcadControl.controller;

import br.grupointegrado.AcadControl.dto.TurmaRequestDTO;
import br.grupointegrado.AcadControl.model.Curso;
import br.grupointegrado.AcadControl.model.Turma;
import br.grupointegrado.AcadControl.repository.CursoRepository;
import br.grupointegrado.AcadControl.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turma")

public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Turma> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
    }

    @PostMapping
    public Turma save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(String.valueOf(dto.ano()));
        turma.setSemestre(dto.semestre());
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id,
                       @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        turma.setAno(String.valueOf(dto.ano()));
        turma.setSemestre(dto.semestre());
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        turma.setCurso(curso);

        return this.repository.save(turma);
    }

}

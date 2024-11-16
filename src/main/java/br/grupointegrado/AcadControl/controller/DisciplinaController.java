package br.grupointegrado.AcadControl.controller;


import br.grupointegrado.AcadControl.dto.DisciplinaRequestDTO;
import br.grupointegrado.AcadControl.dto.TurmaRequestDTO;
import br.grupointegrado.AcadControl.model.Curso;
import br.grupointegrado.AcadControl.model.Disciplina;
import br.grupointegrado.AcadControl.model.Professor;
import br.grupointegrado.AcadControl.model.Turma;
import br.grupointegrado.AcadControl.repository.CursoRepository;
import br.grupointegrado.AcadControl.repository.DisciplinaRepository;
import br.grupointegrado.AcadControl.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Disciplina> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Disciplina findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
    }
    @PostMapping
    public Disciplina save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(Integer.valueOf(dto.codigo()));
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        disciplina.setCurso(curso);
        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    @PutMapping("/{id}")
    public Disciplina update(@PathVariable Integer id,
                        @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(Integer.valueOf(dto.codigo()));
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        disciplina.setCurso(curso);
        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        this.repository.delete(disciplina);
    }
}

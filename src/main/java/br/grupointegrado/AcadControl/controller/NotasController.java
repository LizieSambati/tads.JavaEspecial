package br.grupointegrado.AcadControl.controller;

import br.grupointegrado.AcadControl.dto.MatriculaRequestDTO;
import br.grupointegrado.AcadControl.dto.NotaRequestDTO;
import br.grupointegrado.AcadControl.model.*;
import br.grupointegrado.AcadControl.repository.DisciplinaRepository;
import br.grupointegrado.AcadControl.repository.MatriculaRepository;
import br.grupointegrado.AcadControl.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")

public class NotasController {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Nota> findAll() {
        return this.repository.findAll();
    }


    @GetMapping("/{id}")
    public Nota findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
    }
    @PostMapping
    public Nota save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();
        Matricula matricula = matriculaRepository.findById(dto.matricula())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));
        nota.setMatricula(matricula);
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        nota.setDisciplina(disciplina);
        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lançamento());

       return this.repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Integer id,
                       @RequestBody NotaRequestDTO dto) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        Matricula matricula = matriculaRepository.findById(dto.matricula())
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        nota.setMatricula(matricula);
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        nota.setDisciplina(disciplina);
        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lançamento());

        return this.repository.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        this.repository.delete(nota);
    }

}

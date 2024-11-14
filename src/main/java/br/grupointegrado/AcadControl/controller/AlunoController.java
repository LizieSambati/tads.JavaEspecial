package br.grupointegrado.AcadControl.controller;

import br.grupointegrado.AcadControl.dto.AlunoRequestDTO;
import br.grupointegrado.AcadControl.model.Aluno;
import br.grupointegrado.AcadControl.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
       // Aluno aluno = this.repository.findById(id)
       //         .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
       // return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

        return this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
    }
}

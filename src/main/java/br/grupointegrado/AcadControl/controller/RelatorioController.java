package br.grupointegrado.AcadControl.controller;

import br.grupointegrado.AcadControl.model.Aluno;
import br.grupointegrado.AcadControl.model.Matricula;
import br.grupointegrado.AcadControl.model.Nota;
import br.grupointegrado.AcadControl.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/boletim_aluno/{id}/{ano}/{semestre}")
    public ResponseEntity<Map<String, Map<String, List<BigDecimal>>>> getDesempenhoAluno(
            @PathVariable Integer id,
            @PathVariable Integer ano,
            @PathVariable Integer semestre) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(id);
        matriculas = matriculas.stream()
//                .filter(matricula -> matricula.getTurma().getAno().equals(ano))
                .filter(matricula -> matricula.getTurma().getSemestre().equals(semestre))
                .toList();
        List<Nota> notas = matriculas.stream()
                .flatMap(matricula -> notaRepository.findByMatriculaId(matricula.getId()).stream())
                .toList();
        Map<String, Map<String, List<BigDecimal>>> boletim = notas.stream()
                .collect(Collectors.groupingBy(
                        nota -> nota.getMatricula().getTurma().getAno(),
                        Collectors.groupingBy(
                                nota -> nota.getDisciplina().getNome(),
                                Collectors.mapping(Nota::getNota, Collectors.toList())
                        )
                ));

        return ResponseEntity.ok(boletim);
    }

    @GetMapping("/historico_aluno/{id}")
    public ResponseEntity<Map<String, List<BigDecimal>>> getHistorico(@PathVariable Integer id) {
        alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        List<Nota> notas = notaRepository.findByMatriculaId(id);
        Map<String, List<BigDecimal>> historico = notas.stream()
                .collect(Collectors.groupingBy(
                        nota -> nota.getDisciplina().getNome(),
                        Collectors.mapping(Nota::getNota, Collectors.toList())
                ));

        return ResponseEntity.ok(historico);
    }

    @GetMapping("/boletim_turma/{id}")
    public ResponseEntity<Map<String, Map<String, List<BigDecimal>>>> getBoletimTurma(@PathVariable Integer id) {
        turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        List<Matricula> matriculas = matriculaRepository.findByTurmaId(id);
        List<Nota> notas = matriculas.stream()
                .flatMap(matricula -> notaRepository.findByMatriculaId(matricula.getId()).stream())
                .toList();
        Map<String, Map<String, List<BigDecimal>>> boletim = notas.stream()
                .collect(Collectors.groupingBy(
                        nota -> nota.getMatricula().getAluno().getNome(),
                        Collectors.groupingBy(
                                nota -> nota.getDisciplina().getNome(),
                                Collectors.mapping(Nota::getNota, Collectors.toList())
                        )
                ));

        return ResponseEntity.ok(boletim);
    }

    @GetMapping("/desempenho_turma/{id}")
    public ResponseEntity<List<BigDecimal>> getDesempenhoTurma(@PathVariable Integer id) {
        turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        List<Matricula> matriculas = matriculaRepository.findByTurmaId(id);
        List<Nota> notas = matriculas.stream()
                .flatMap(matricula -> notaRepository.findByMatriculaId(matricula.getId()).stream())
                .toList();
        List<BigDecimal> listaNotas = notas.stream()
                .map(Nota::getNota)
                .toList();
        return ResponseEntity.ok(listaNotas);
    }

    @GetMapping("/desempenho_disciplina/{id}")
    public ResponseEntity<Map<String, List<BigDecimal>>> getDesempenhoDisciplina(@PathVariable Integer id) {
        disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        List<Nota> notas = notaRepository.findByDisciplinaId(id);
        Map<String, List<BigDecimal>> desempenho = notas.stream()
                .collect(Collectors.groupingBy(
                        nota -> nota.getMatricula().getTurma().getAno(),
                        Collectors.mapping(Nota::getNota, Collectors.toList())
                ));

        return ResponseEntity.ok(desempenho);
    }

}

package br.grupointegrado.AcadControl.dto;

import jakarta.persistence.Column;

import java.util.Date;

public record AlunoRequestDTO(String nome, String email, String matricula, Date data_nascimento) {
}
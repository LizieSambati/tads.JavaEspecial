package br.grupointegrado.AcadControl.dto;

import java.math.BigDecimal;
import java.util.Date;

public record NotaRequestDTO(Integer matricula, Integer disciplina, BigDecimal nota, Date data_lançamento) {
}

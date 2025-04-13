package br.com.fiap.localtech.locatech.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
        String marca,

        String modelo,

        String placa,

        @Positive(message = "Ano não pode ser negativo")
        int ano,

        String cor,

        @PositiveOrZero(message = "Valor da diária precisa ser positivo ou zero")
        BigDecimal valorDiaria
) {
}

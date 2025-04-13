package br.com.fiap.localtech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
        @Schema(description = "Marca do carro ex: Honda, Chevrolet")
        String marca,
        @Schema(description = "Modelo do carro ex: Civic, Renegade")
        String modelo,
        @Schema(description = "Placa do carro")
        String placa,

        @Schema(description = "Ano de frabricação do carro")
        @Positive(message = "Ano não pode ser negativo")
        int ano,

        @Schema(description = "Cor do carro")
        String cor,

        @Schema(description = "Valor da díaria do carro para aluguel")
        @PositiveOrZero(message = "Valor da diária precisa ser positivo ou zero")
        BigDecimal valorDiaria
) {
}

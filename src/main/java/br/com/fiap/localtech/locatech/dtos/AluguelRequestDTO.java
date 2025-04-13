package br.com.fiap.localtech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @Schema(description = "Id da pesssoa que está alugado o veículo")
        @NotNull(message = "O id da pessoa não pode ser nulo")
        Long pessoaId,
        @Schema(description = "Id do veículo alugado")
        @NotNull(message = "O id do veículo não pode ser nulo")
        Long veiculoId,
        @Schema(description = "Data da ínicio da locação")
        LocalDate dataInicio,
        @Schema(description = "Data final da locação")
        LocalDate dataFim
) {
}

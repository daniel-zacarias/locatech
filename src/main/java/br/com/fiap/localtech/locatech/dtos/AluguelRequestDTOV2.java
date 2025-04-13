package br.com.fiap.localtech.locatech.dtos;

import br.com.fiap.localtech.locatech.validators.annotations.DataInicioAntesOuIgualAFim;
import br.com.fiap.localtech.locatech.validators.annotations.ExisteId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@DataInicioAntesOuIgualAFim(dataInicio = "dataInicio", dataFim = "dataFim")
public record AluguelRequestDTOV2(
        @Schema(description = "Id da pesssoa que está alugado o veículo")
        @NotNull(message = "O id da pessoa não pode ser nulo")
        @ExisteId(tabela = "pessoas", message = "Pessoa não existe")
        Long pessoaId,
        @Schema(description = "Id do veículo alugado")
        @NotNull(message = "O id do veículo não pode ser nulo")
        @ExisteId(tabela = "veiculos", message = "Veículo não existe")
        Long veiculoId,
        @Schema(description = "Data da ínicio da locação")
        LocalDate dataInicio,
        @Schema(description = "Data final da locação")
        LocalDate dataFim
) {
}

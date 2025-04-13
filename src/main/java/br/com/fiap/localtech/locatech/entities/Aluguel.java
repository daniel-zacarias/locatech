package br.com.fiap.localtech.locatech.entities;

import br.com.fiap.localtech.locatech.dtos.AluguelRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Aluguel {
    private Long id;

    private Long pessoaId;

    private Long veiculoId;

    private String veiculoModelo;

    private String veiculoPlaca;

    private String pessoaCpf;

    private String pessoaNome;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal valorTotal;

    public Aluguel(AluguelRequestDTO dto, BigDecimal valor) {
        this.pessoaId = dto.pessoaId();
        this.veiculoId = dto.veiculoId();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.valorTotal = valor;
    }
}

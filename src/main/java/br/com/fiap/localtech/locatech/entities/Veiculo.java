package br.com.fiap.localtech.locatech.entities;

import br.com.fiap.localtech.locatech.dtos.VeiculoRequestDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Veiculo {
    private Long id;

    private String marca;

    private String modelo;

    private String placa;

    private int ano;

    private String cor;

    private BigDecimal valorDiaria;

    public Veiculo(VeiculoRequestDTO veiculo) {
        this.marca = veiculo.marca();
        this.modelo = veiculo.modelo();
        this.placa = veiculo.placa();
        this.ano = veiculo.ano();
        this.cor = veiculo.cor();
        this.valorDiaria = veiculo.valorDiaria();
    }
}

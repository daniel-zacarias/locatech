package br.com.fiap.localtech.locatech.entities;

import br.com.fiap.localtech.locatech.dtos.PessoaRequestDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pessoa {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    public Pessoa(PessoaRequestDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.telefone = dto.telefone();
    }

}

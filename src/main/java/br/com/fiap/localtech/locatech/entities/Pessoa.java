package br.com.fiap.localtech.locatech.entities;

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

}

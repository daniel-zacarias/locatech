package br.com.fiap.localtech.locatech.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequestDTO (
        String nome,

        @CPF(message = "Documento da pessoa é inválida")
        String cpf,

        @Email(message = "E-mail inválido")
        String email,

        @Pattern(
                regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}",
                message = "Telefone inválido. Ex: (11) 91234-5678"
        )
        String telefone
) {
}

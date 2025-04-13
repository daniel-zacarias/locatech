package br.com.fiap.localtech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequestDTO (
        @Schema(description = "Nome da pessoa cadastrada")
        String nome,

        @Schema(description = "Documento da pessoa cadastrada")
        @CPF(message = "Documento da pessoa é inválida")
        String cpf,

        @Schema(description = "E-mail da pessoa cadastrada")
        @Email(message = "E-mail inválido")
        String email,

        @Schema(description = "Telefone da pessoa no formato (11) 91234-5678")
        @Pattern(
                regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}",
                message = "Telefone inválido. Ex: (11) 91234-5678"
        )
        String telefone
) {
}

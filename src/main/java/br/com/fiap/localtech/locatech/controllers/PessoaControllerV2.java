package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.PessoaRequestDTO;
import br.com.fiap.localtech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.localtech.locatech.dtos.ValidationErrorDto;
import br.com.fiap.localtech.locatech.entities.Pessoa;
import br.com.fiap.localtech.locatech.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/v2/pessoas")
@RestController
@Tag(name = "Pessoa", description = "Controller para crud de pessoas")
public class PessoaControllerV2 {
    private static final Logger logger = LoggerFactory.getLogger(PessoaControllerV2.class);

    private final PessoaService pessoaService;

    public PessoaControllerV2(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(
            summary = "Buscar pessoa por ID",
            description = "Retorna os dados de uma pessoa com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(
            @Parameter(description = "ID da pessoa", example = "1") @PathVariable Long id
    ) {
        logger.info("/pessoas/" + id);
        var pessoa = this.pessoaService.findByPessoaIdOrThrowError(id);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(
            summary = "Cadastrar nova pessoa",
            description = "Cria um novo registro de pessoa com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados para pessoa inválida",
                            content = @Content(schema = @Schema(implementation = ValidationErrorDto.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Void> savePessoa(
            @Parameter(description = "Dados da nova pessoa") @RequestBody PessoaRequestDTO pessoa
    ) {
        logger.info("POST -> /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            summary = "Atualizar dados da pessoa",
            description = "Atualiza as informações de uma pessoa existente.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pessoa atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @Parameter(description = "ID da pessoa a ser atualizada") @PathVariable Long id,
            @Parameter(description = "Novos dados da pessoa") @RequestBody PessoaRequestDTO pessoa
    ) {
        logger.info("PUT -> /pessoas/");
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Remover pessoa",
            description = "Remove uma pessoa com base no ID informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
            @Parameter(description = "ID da pessoa a ser removida", example = "1") @PathVariable Long id
    ) {
        logger.info("DELETE -> /pessoas/");
        this.pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}

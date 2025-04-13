package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.AluguelRequestDTOV2;
import br.com.fiap.localtech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.localtech.locatech.dtos.ValidationErrorDto;
import br.com.fiap.localtech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/alugueis")
@Tag(name = "Aluguel", description = "Controller para crud de alugueis")
public class AluguelControllerV2 {
    private static final Logger logger = LoggerFactory.getLogger(AluguelControllerV2.class);

    private final AluguelService aluguelService;

    public AluguelControllerV2(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @Operation(
            summary = "Criar novo aluguel",
            description = "Cria um novo aluguel com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Aluguel criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do aluguel",
                            content = @Content(schema = @Schema(implementation = ValidationErrorDto.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveAluguel(
            @Parameter(description = "Dados do novo aluguel") @RequestBody @Valid AluguelRequestDTOV2 aluguel
    ) {
        logger.info("POST -> /alugueis");
        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            summary = "Atualizar aluguel",
            description = "Atualiza os dados de um aluguel existente.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Aluguel atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Aluguel não encontrado",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @Parameter(description = "ID do aluguel a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Novos dados do aluguel") @RequestBody AluguelRequestDTOV2 aluguel
    ){
        logger.info("PUT -> /alugueis/");
        this.aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @PathVariable Long id
    ){
        logger.info("DELETE -> /alugueis/");
        this.aluguelService.delete(id);
        return ResponseEntity.ok().build();
    }
}

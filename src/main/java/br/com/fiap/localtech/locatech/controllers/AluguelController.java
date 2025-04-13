package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.localtech.locatech.entities.Aluguel;
import br.com.fiap.localtech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/alugueis")
public class AluguelController {

    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @Operation(
            summary = "Listar aluguéis",
            description = "Retorna todos os aluguéis cadastrados de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de aluguéis retornada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAluguels(
            @Parameter(description = "Número da página (começa em 1)", example = "1") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de itens por página", example = "10") @RequestParam("size") int size
    ) {
        logger.info("/alugueis");
        var alugueis = this.aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @Operation(
            summary = "Buscar aluguel por ID",
            description = "Retorna um aluguel com base no ID informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluguel encontrado"),
                    @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(
            @Parameter(description = "ID do aluguel", example = "1") @PathVariable Long id
    ) {
        logger.info("/alugueis/" + id);
        var aluguel = this.aluguelService.findByAluguelId(id);
        return ResponseEntity.ok(aluguel);
    }

    @Operation(
            summary = "Criar novo aluguel",
            description = "Cria um novo aluguel com os dados fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Aluguel criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do aluguel")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveAluguel(
            @Parameter(description = "Dados do novo aluguel") @RequestBody @Valid AluguelRequestDTO aluguel
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
                    @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @Parameter(description = "ID do aluguel a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Novos dados do aluguel") @RequestBody Aluguel aluguel
    ) {
        logger.info("PUT -> /alugueis/");
        this.aluguelService.updateAluguel(aluguel, id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Remover aluguel",
            description = "Remove um aluguel com base no ID informado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluguel removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @Parameter(description = "ID do aluguel a ser removido", example = "1") @PathVariable Long id
    ) {
        logger.info("DELETE -> /alugueis/");
        this.aluguelService.delete(id);
        return ResponseEntity.ok().build();
    }
}

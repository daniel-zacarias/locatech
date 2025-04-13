package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.localtech.locatech.dtos.ValidationErrorDto;
import br.com.fiap.localtech.locatech.dtos.VeiculoRequestDTO;
import br.com.fiap.localtech.locatech.entities.Veiculo;
import br.com.fiap.localtech.locatech.services.VeiculoService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/veiculos")
@Tag(name = "Veículo", description = "Controller para crud de veículos")
public class VeiculoControllerV2 {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoControllerV2.class);

    private final VeiculoService veiculoService;

    public VeiculoControllerV2(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            description = "Busca todos os veículos paginados",
            summary = "Busca de veículos",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("/veiculos");
        var veiculos = this.veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @Operation(
            summary = "Buscar veículo por ID",
            description = "Retorna os dados de um véículo com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findPessoa(
            @Parameter(description = "ID do veículp", example = "1") @PathVariable Long id
    ) {
        logger.info("/veiculos/" + id);
        var veiculo = this.veiculoService.findByVeiculoId(id);
        return ResponseEntity.ok(veiculo);
    }

    @Operation(
            summary = "Cadastrar novo veículo",
            description = "Cria um novo veículo com os dados enviados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Veículo inválido",
                            content = @Content(schema = @Schema(implementation = ValidationErrorDto.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveVeiculo(
            @Parameter(description = "Dados do veículo") @RequestBody VeiculoRequestDTO veiculo
    ) {
        logger.info("POST -> /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            summary = "Atualizar veículo",
            description = "Atualiza os dados de um veículo existente.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Veículo atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @Parameter(description = "Id do veículo atualizado") @PathVariable Long id,
            @Parameter(description = "Dados do veículo") @RequestBody @Valid VeiculoRequestDTO veiculo
    ) {
        logger.info("PUT -> /veiculos/");
        this.veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Deletar veículo",
            description = "Deleta os dados de um veículo existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Veículo deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado", content = @Content(schema = @Schema(implementation = ResourceNotFoundDTO.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(
            @Parameter(description = "Id do veículo a ser deletado") @PathVariable Long id
    ) {
        logger.info("DELETE -> /veiculos/");
        this.veiculoService.delete(id);
        return ResponseEntity.ok().build();
    }

}

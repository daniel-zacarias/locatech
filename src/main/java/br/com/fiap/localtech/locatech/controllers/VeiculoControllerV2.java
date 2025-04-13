package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.VeiculoRequestDTO;
import br.com.fiap.localtech.locatech.entities.Veiculo;
import br.com.fiap.localtech.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(
            @PathVariable Long id
    ) {
        logger.info("/veiculos/" + id);
        var veiculo = this.veiculoService.findByVeiculoId(id);
        return ResponseEntity.ok(veiculo);
    }

    @Operation(
            summary = "Cadastrar novo veículo",
            description = "Cria um novo veículo com os dados enviados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso")
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @PathVariable Long id,
            @RequestBody @Valid VeiculoRequestDTO veiculo
    ) {
        logger.info("PUT -> /veiculos/");
        this.veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(
            @PathVariable Long id
    ){
        logger.info("DELETE -> /veiculos/");
        this.veiculoService.delete(id);
        return ResponseEntity.ok().build();
    }

}

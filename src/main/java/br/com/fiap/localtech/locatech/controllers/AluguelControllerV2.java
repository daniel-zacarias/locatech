package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.AluguelRequestDTOV2;
import br.com.fiap.localtech.locatech.entities.Aluguel;
import br.com.fiap.localtech.locatech.services.AluguelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/alugueis")
public class AluguelControllerV2 {
    private static final Logger logger = LoggerFactory.getLogger(AluguelControllerV2.class);

    private final AluguelService aluguelService;

    public AluguelControllerV2(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping()
    public ResponseEntity<Void> saveAluguel(
            @RequestBody @Valid AluguelRequestDTOV2 aluguel
    ) {
        logger.info("POST -> /alugueis");
        this.aluguelService.saveAluguel(aluguel);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @PathVariable Long id,
             @RequestBody @Valid AluguelRequestDTOV2 aluguel
    ) {
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

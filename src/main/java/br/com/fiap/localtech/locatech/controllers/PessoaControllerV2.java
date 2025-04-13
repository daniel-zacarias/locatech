package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.dtos.PessoaRequestDTO;
import br.com.fiap.localtech.locatech.entities.Pessoa;
import br.com.fiap.localtech.locatech.services.PessoaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/v2/pessoas")
@RestController
public class PessoaControllerV2 {
    private static final Logger logger = LoggerFactory.getLogger(PessoaControllerV2.class);

    private final PessoaService pessoaService;

    public PessoaControllerV2(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(
            @PathVariable Long id
    ) {
        logger.info("/pessoas/" + id);
        var pessoa = this.pessoaService.findByPessoaIdOrThrowError(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(
            @RequestBody @Valid PessoaRequestDTO pessoa
            ) {
        logger.info("POST -> /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @PathVariable Long id,
            @RequestBody @Valid PessoaRequestDTO pessoa
    ) {
        logger.info("PUT -> /pessoas/");
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
            @PathVariable Long id
    ){
        logger.info("DELETE -> /pessoas/");
        this.pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package br.com.fiap.localtech.locatech.controllers;

import br.com.fiap.localtech.locatech.entities.Pessoa;
import br.com.fiap.localtech.locatech.services.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pessoas")
@RestController
public class PessoaController {
    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("/pessoas");
        var pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(
            @PathVariable Long id
    ) {
        logger.info("/pessoas/" + id);
        var pessoa = this.pessoaService.findByPessoaId(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(
            @RequestBody Pessoa pessoa
    ) {
        logger.info("POST -> /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @PathVariable Long id,
            @RequestBody Pessoa pessoa
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

package br.com.fiap.localtech.locatech.services;

import br.com.fiap.localtech.locatech.dtos.PessoaRequestDTO;
import br.com.fiap.localtech.locatech.entities.Pessoa;
import br.com.fiap.localtech.locatech.repositories.PessoaRepository;
import br.com.fiap.localtech.locatech.services.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoas(int page, int size){
        int offset = (page - 1) * size;

        return this.pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findByPessoaId(Long id) {
        return this.pessoaRepository.findById(id);
    }

    public Optional<Pessoa> findByPessoaIdOrThrowError(Long id) {
        return Optional.of(this.pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada")));
    }

    public void savePessoa(Pessoa pessoa) {
        var save = this.pessoaRepository.save(pessoa);
        Assert.state(save == 1, "Erro ao salvar pessoa" + pessoa.getNome());
    }

    public void updatePessoa(Pessoa pessoa, Long id) {
        var update = this.pessoaRepository.update(pessoa, id);
        if(update == 0) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    public void delete(Long id) {
        var delete = this.pessoaRepository.delete(id);
        if(delete == 0) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    public void savePessoa(PessoaRequestDTO pessoa) {
        Pessoa pessoaEntity = new Pessoa(pessoa);
        this.savePessoa(pessoaEntity);
    }

    public void updatePessoa(@Valid PessoaRequestDTO pessoa, Long id) {
        Pessoa pessoaEntity = new Pessoa(pessoa);
        this.updatePessoa(pessoaEntity, id);
    }
}

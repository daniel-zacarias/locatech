package br.com.fiap.localtech.locatech.services;

import br.com.fiap.localtech.locatech.dtos.VeiculoRequestDTO;
import br.com.fiap.localtech.locatech.entities.Veiculo;
import br.com.fiap.localtech.locatech.repositories.VeiculoRepository;
import br.com.fiap.localtech.locatech.services.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> findAllVeiculos(int page, int size){
        int offset = (page - 1) * size;

        return this.veiculoRepository.findAll(size, offset);
    }

    public Optional<Veiculo> findByVeiculoId(Long id) {
        return Optional.ofNullable(this.veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado")));
    }

    public void saveVeiculo(Veiculo veiculo) {
        var save = this.veiculoRepository.save(veiculo);
        Assert.state(save == 1, "Erro ao salvar veículo" + veiculo.getModelo());
    }

    public void updateVeiculo(Veiculo veiculo, Long id) {
        var update = this.veiculoRepository.update(veiculo, id);
        if(update == 0) {
            throw new ResourceNotFoundException("Veículo não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.veiculoRepository.delete(id);
        if(delete == 0) {
            throw new ResourceNotFoundException("Veículo não encontrado");
        }
    }

    public void saveVeiculo(VeiculoRequestDTO veiculo) {
        Veiculo veiculoEntity = new Veiculo(veiculo);
        this.saveVeiculo(veiculoEntity);
    }

    public void updateVeiculo(@Valid VeiculoRequestDTO veiculo, Long id) {
        Veiculo veiculoEntity = new Veiculo(veiculo);
        this.updateVeiculo(veiculoEntity, id);
    }
}

package service;

import model.Veiculo;
import repository.VeiculoRepository;

public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Veiculo veiculo) {
        repository.salvar(veiculo);
    }
}
package service;

import model.OrdemServico;
import repository.OrdemServicoRepository;
import repository.VeiculoRepository;

import java.math.BigDecimal;

public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final VeiculoRepository veiculoRepository;

    public OrdemServicoService(
            OrdemServicoRepository repository,
            VeiculoRepository veiculoRepository) {

        this.repository = repository;
        this.veiculoRepository = veiculoRepository;
    }

    public void abrirOrdem(OrdemServico os) {

        if (!veiculoRepository.existe(os.getIdVeiculo())) {
            throw new RuntimeException(
                    "Veículo não cadastrado."
            );
        }

        if (os.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(
                    "Valor não pode ser negativo."
            );
        }

        repository.salvar(os);
    }
}
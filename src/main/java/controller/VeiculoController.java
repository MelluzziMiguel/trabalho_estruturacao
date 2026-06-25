package controller;

import model.Veiculo;
import service.VeiculoService;

public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    public void salvar(Veiculo veiculo) {
        service.salvar(veiculo);
    }
}
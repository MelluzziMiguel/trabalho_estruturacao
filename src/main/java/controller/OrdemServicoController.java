package controller;

import model.OrdemServico;
import service.OrdemServicoService;

public class OrdemServicoController {

    private final OrdemServicoService service;

    public OrdemServicoController(
            OrdemServicoService service) {

        this.service = service;
    }

    public void abrirOrdem(OrdemServico os) {
        service.abrirOrdem(os);
    }
}
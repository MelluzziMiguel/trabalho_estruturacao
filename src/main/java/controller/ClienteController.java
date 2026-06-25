package controller;

import model.Cliente;
import service.ClienteService;

public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    public void salvar(Cliente cliente) {
        service.salvar(cliente);
    }
}
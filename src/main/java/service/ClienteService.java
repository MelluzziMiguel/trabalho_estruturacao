package service;

import model.Cliente;
import repository.ClienteRepository;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void salvar(Cliente cliente) {
        repository.salvar(cliente);
    }
}
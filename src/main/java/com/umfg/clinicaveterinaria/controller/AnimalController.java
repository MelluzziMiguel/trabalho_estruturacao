package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.service.AnimalService;

import java.sql.SQLException;
import java.util.List;

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    public Animal cadastrar(Animal animal) throws SQLException {
        return animalService.cadastrarAnimal(animal);
    }

    public Animal buscarPorId(int id) throws SQLException {
        return animalService.buscarPorId(id);
    }

    public List<Animal> listarTodos() throws SQLException {
        return animalService.listarTodos();
    }

    public List<Animal> listarPorTutor(int idTutor) throws SQLException {
        return animalService.listarPorTutor(idTutor);
    }

    public boolean atualizar(Animal animal) throws SQLException {
        return animalService.atualizar(animal);
    }

    public boolean excluir(int id) throws SQLException {
        return animalService.excluir(id);
    }
}
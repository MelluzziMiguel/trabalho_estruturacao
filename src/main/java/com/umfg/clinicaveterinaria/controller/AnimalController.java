package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.service.AnimalService;

import java.sql.SQLException;
import java.util.List;

public class AnimalController {

    private final AnimalService animalService = new AnimalService();

    public Animal cadastrar(String nome, String especie, String raca, int idTutor) {
        try {
            Animal animal = animalService.cadastrarAnimal(nome, especie, raca, idTutor);
            System.out.println("[OK] Animal cadastrado: " + animal);
            return animal;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[ERRO] Falha ao cadastrar animal: " + e.getMessage());
            return null;
        }
    }

    public Animal buscarPorId(int id) {
        try {
            Animal animal = animalService.buscarPorId(id);
            if (animal == null) {
                System.out.println("[INFO] Animal com id " + id + " não encontrado.");
            }
            return animal;
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao buscar animal: " + e.getMessage());
            return null;
        }
    }

    public List<Animal> listarTodos() {
        try {
            return animalService.listarTodos();
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao listar animais: " + e.getMessage());
            return List.of();
        }
    }

    public List<Animal> listarPorTutor(int idTutor) {
        try {
            return animalService.listarPorTutor(idTutor);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao listar animais do tutor: " + e.getMessage());
            return List.of();
        }
    }

    public boolean atualizar(Animal animal) {
        try {
            return animalService.atualizar(animal);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao atualizar animal: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            return animalService.excluir(id);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao excluir animal: " + e.getMessage());
            return false;
        }
    }
}
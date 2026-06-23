package com.umfg.clinicaveterinaria.service;

import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.repository.AnimalRepository;
import com.umfg.clinicaveterinaria.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {

    private final AnimalRepository animalRepository = new AnimalRepository();
    private final TutorRepository tutorRepository = new TutorRepository();

    public Animal cadastrarAnimal(String nome, String especie, String raca, int idTutor) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do animal é obrigatório.");
        }
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("A espécie do animal é obrigatória.");
        }

        Tutor tutor = tutorRepository.buscarPorId(idTutor);
        if (tutor == null) {
            throw new IllegalArgumentException("Não é possível cadastrar o animal: tutor com id " + idTutor + " não encontrado.");
        }

        Animal animal = new Animal(nome, especie, raca, idTutor);
        return animalRepository.salvar(animal);
    }

    public Animal buscarPorId(int id) throws SQLException {
        return animalRepository.buscarPorId(id);
    }

    public List<Animal> listarTodos() throws SQLException {
        return animalRepository.listarTodos();
    }

    public List<Animal> listarPorTutor(int idTutor) throws SQLException {
        return animalRepository.listarPorTutor(idTutor);
    }

    public boolean atualizar(Animal animal) throws SQLException {
        return animalRepository.atualizar(animal);
    }

    public boolean excluir(int id) throws SQLException {
        return animalRepository.excluir(id);
    }
}
package com.umfg.clinicaveterinaria;

import com.umfg.clinicaveterinaria.controller.AnimalController;
import com.umfg.clinicaveterinaria.controller.ConsultaController;
import com.umfg.clinicaveterinaria.controller.TutorController;
import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.model.Tutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TutorController tutorController = new TutorController();
        AnimalController animalController = new AnimalController();
        ConsultaController consultaController = new ConsultaController();

        System.out.println("===== 1) Cadastro do tutor =====");
        Tutor tutor = tutorController.cadastrar(
                "Maria Souza",
                "Rua das Flores, 123",
                "(44) 99999-1234"
        );

        if (tutor == null) {
            System.out.println("Não foi possível continuar a simulação sem um tutor cadastrado.");
            return;
        }

        System.out.println("\n===== 2) Cadastro do animal vinculado ao tutor =====");
        Animal animal = animalController.cadastrar(
                "Rex",
                "Cachorro",
                "Labrador",
                tutor.getId()
        );

        if (animal == null) {
            System.out.println("Não foi possível continuar a simulação sem um animal cadastrado.");
            return;
        }

        System.out.println("\n===== 3) Registro da consulta (movimento) =====");
        consultaController.registrar(
                animal.getId(),
                LocalDate.now(),
                "Vacinação anual",
                new BigDecimal("150.00")
        );

        System.out.println("\n===== 4) Histórico de consultas do animal =====");
        List<Consulta> historico = consultaController.listarHistoricoPorAnimal(animal.getId());
        historico.forEach(System.out::println);

        System.out.println("\n===== 5) Listagem de animais do tutor =====");
        List<Animal> animaisDoTutor = animalController.listarPorTutor(tutor.getId());
        animaisDoTutor.forEach(System.out::println);

        System.out.println("\n===== 6) Testando regra de negocio: consulta para animal inexistente =====");
        consultaController.registrar(
                99999,
                LocalDate.now(),
                "Consulta inválida",
                new BigDecimal("100.00")
        );

        System.out.println("\n===== 7) Testando regra de negocio: valor negativo =====");
        consultaController.registrar(
                animal.getId(),
                LocalDate.now(),
                "Consulta com valor invalido",
                new BigDecimal("-50.00")
        );

        System.out.println("\nSimulação finalizada.");
    }
}
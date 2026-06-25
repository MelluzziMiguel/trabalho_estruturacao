package com.umfg.clinicaveterinaria;

import com.umfg.clinicaveterinaria.controller.AnimalController;
import com.umfg.clinicaveterinaria.controller.ConsultaController;
import com.umfg.clinicaveterinaria.controller.TutorController;
import com.umfg.clinicaveterinaria.model.Animal;
import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.repository.AnimalRepository;
import com.umfg.clinicaveterinaria.repository.ConsultaRepository;
import com.umfg.clinicaveterinaria.repository.TutorRepository;
import com.umfg.clinicaveterinaria.service.AnimalService;
import com.umfg.clinicaveterinaria.service.ConsultaService;
import com.umfg.clinicaveterinaria.service.TutorService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TutorRepository tutorRepository = new TutorRepository();
        AnimalRepository animalRepository = new AnimalRepository();
        ConsultaRepository consultaRepository = new ConsultaRepository();

        TutorService tutorService = new TutorService(tutorRepository);
        AnimalService animalService = new AnimalService(animalRepository, tutorRepository);
        ConsultaService consultaService = new ConsultaService(consultaRepository, animalRepository);

        TutorController tutorController = new TutorController(tutorService);
        AnimalController animalController = new AnimalController(animalService);
        ConsultaController consultaController = new ConsultaController(consultaService);

        try {
            Tutor tutor = new Tutor("Maria Souza", "Rua das Flores, 123", "(44) 99999-1234");
            tutorController.cadastrar(tutor);

            Animal animal = new Animal("Rex", "Cachorro", "Labrador", tutor.getId());
            animalController.cadastrar(animal);

            Consulta consulta = new Consulta(animal.getId(), LocalDate.now(), "Vacinação anual", new BigDecimal("150.00"));
            consultaController.registrar(consulta);

            System.out.println("Tutor, animal e consulta cadastrados");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
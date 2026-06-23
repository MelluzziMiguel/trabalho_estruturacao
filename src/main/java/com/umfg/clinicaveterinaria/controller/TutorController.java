package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.service.TutorService;

import java.sql.SQLException;
import java.util.List;

public class TutorController {

    private final TutorService tutorService = new TutorService();

    public Tutor cadastrar(String nome, String endereco, String telefone) {
        try {
            Tutor tutor = tutorService.cadastrarTutor(nome, endereco, telefone);
            System.out.println("[OK] Tutor cadastrado: " + tutor);
            return tutor;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[ERRO] Falha ao cadastrar tutor: " + e.getMessage());
            return null;
        }
    }

    public Tutor buscarPorId(int id) {
        try {
            Tutor tutor = tutorService.buscarPorId(id);
            if (tutor == null) {
                System.out.println("[INFO] Tutor com id " + id + " não encontrado.");
            }
            return tutor;
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao buscar tutor: " + e.getMessage());
            return null;
        }
    }

    public List<Tutor> listarTodos() {
        try {
            return tutorService.listarTodos();
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao listar tutores: " + e.getMessage());
            return List.of();
        }
    }

    public boolean atualizar(Tutor tutor) {
        try {
            return tutorService.atualizar(tutor);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao atualizar tutor: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            return tutorService.excluir(id);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao excluir tutor: " + e.getMessage());
            return false;
        }
    }
}
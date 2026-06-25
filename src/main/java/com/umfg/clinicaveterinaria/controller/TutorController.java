package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.service.TutorService;

import java.sql.SQLException;
import java.util.List;

public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    public Tutor cadastrar(Tutor tutor) throws SQLException {
        return tutorService.cadastrarTutor(tutor);
    }

    public Tutor buscarPorId(int id) throws SQLException {
        return tutorService.buscarPorId(id);
    }

    public List<Tutor> listarTodos() throws SQLException {
        return tutorService.listarTodos();
    }

    public boolean atualizar(Tutor tutor) throws SQLException {
        return tutorService.atualizar(tutor);
    }

    public boolean excluir(int id) throws SQLException {
        return tutorService.excluir(id);
    }
}
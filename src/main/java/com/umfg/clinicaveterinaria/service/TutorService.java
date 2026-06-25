package com.umfg.clinicaveterinaria.service;

import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;

public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor cadastrarTutor(Tutor tutor) throws SQLException {
        if (tutor.getNome() == null || tutor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do tutor é obrigatório.");
        }
        if (tutor.getTelefone() == null || tutor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do tutor é obrigatório.");
        }

        return tutorRepository.salvar(tutor);
    }

    public Tutor buscarPorId(int id) throws SQLException {
        return tutorRepository.buscarPorId(id);
    }

    public List<Tutor> listarTodos() throws SQLException {
        return tutorRepository.listarTodos();
    }

    public boolean atualizar(Tutor tutor) throws SQLException {
        return tutorRepository.atualizar(tutor);
    }

    public boolean excluir(int id) throws SQLException {
        return tutorRepository.excluir(id);
    }
}
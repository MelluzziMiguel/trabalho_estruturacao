package com.umfg.clinicaveterinaria.service;

import com.umfg.clinicaveterinaria.model.Tutor;
import com.umfg.clinicaveterinaria.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;

public class TutorService {

    private final TutorRepository tutorRepository = new TutorRepository();

    public Tutor cadastrarTutor(String nome, String endereco, String telefone) throws SQLException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do tutor é obrigatório.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("O telefone do tutor é obrigatório.");
        }

        Tutor tutor = new Tutor(nome, endereco, telefone);
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
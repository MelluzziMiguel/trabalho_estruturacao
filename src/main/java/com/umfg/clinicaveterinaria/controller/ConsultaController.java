package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.service.ConsultaService;

import java.sql.SQLException;
import java.util.List;

public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public Consulta registrar(Consulta consulta) throws SQLException {
        return consultaService.registrarConsulta(consulta);
    }

    public Consulta buscarPorId(int id) throws SQLException {
        return consultaService.buscarPorId(id);
    }

    public List<Consulta> listarTodas() throws SQLException {
        return consultaService.listarTodas();
    }

    public List<Consulta> listarHistoricoPorAnimal(int idAnimal) throws SQLException {
        return consultaService.listarHistoricoPorAnimal(idAnimal);
    }

    public boolean atualizar(Consulta consulta) throws SQLException {
        return consultaService.atualizar(consulta);
    }

    public boolean excluir(int id) throws SQLException {
        return consultaService.excluir(id);
    }
}
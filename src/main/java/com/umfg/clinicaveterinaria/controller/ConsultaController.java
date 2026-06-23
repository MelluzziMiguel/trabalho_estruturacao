package com.umfg.clinicaveterinaria.controller;

import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.service.ConsultaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ConsultaController {

    private final ConsultaService consultaService = new ConsultaService();

    public Consulta registrar(int idAnimal, LocalDate data, String motivo, BigDecimal valor) {
        try {
            Consulta consulta = consultaService.registrarConsulta(idAnimal, data, motivo, valor);
            System.out.println("[OK] Consulta registrada: " + consulta);
            return consulta;
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("[ERRO] Falha ao registrar consulta: " + e.getMessage());
            return null;
        }
    }

    public Consulta buscarPorId(int id) {
        try {
            Consulta consulta = consultaService.buscarPorId(id);
            if (consulta == null) {
                System.out.println("[INFO] Consulta com id " + id + " não encontrada.");
            }
            return consulta;
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao buscar consulta: " + e.getMessage());
            return null;
        }
    }

    public List<Consulta> listarTodas() {
        try {
            return consultaService.listarTodas();
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao listar consultas: " + e.getMessage());
            return List.of();
        }
    }

    public List<Consulta> listarHistoricoPorAnimal(int idAnimal) {
        try {
            return consultaService.listarHistoricoPorAnimal(idAnimal);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao listar histórico do animal: " + e.getMessage());
            return List.of();
        }
    }

    public boolean atualizar(Consulta consulta) {
        try {
            return consultaService.atualizar(consulta);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao atualizar consulta: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        try {
            return consultaService.excluir(id);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao excluir consulta: " + e.getMessage());
            return false;
        }
    }
}
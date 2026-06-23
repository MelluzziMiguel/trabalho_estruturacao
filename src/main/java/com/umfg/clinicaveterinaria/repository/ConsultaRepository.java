package com.umfg.clinicaveterinaria.repository;

import com.umfg.clinicaveterinaria.model.Consulta;
import com.umfg.clinicaveterinaria.util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    public Consulta salvar(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (id_animal, data_atendimento, motivo, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, consulta.getIdAnimal());
            stmt.setDate(2, Date.valueOf(consulta.getDataAtendimento()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setBigDecimal(4, consulta.getValor());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getInt(1));
                }
            }
        }
        return consulta;
    }

    public Consulta buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearConsulta(rs);
                }
            }
        }
        return null;
    }

    public List<Consulta> listarTodas() throws SQLException {
        String sql = "SELECT * FROM consulta ORDER BY data_atendimento DESC";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                consultas.add(mapearConsulta(rs));
            }
        }
        return consultas;
    }

    public List<Consulta> listarPorAnimal(int idAnimal) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id_animal = ? ORDER BY data_atendimento DESC";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAnimal);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapearConsulta(rs));
                }
            }
        }
        return consultas;
    }

    public boolean atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET id_animal = ?, data_atendimento = ?, motivo = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getIdAnimal());
            stmt.setDate(2, Date.valueOf(consulta.getDataAtendimento()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setBigDecimal(4, consulta.getValor());
            stmt.setInt(5, consulta.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Consulta mapearConsulta(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getInt("id"),
                rs.getInt("id_animal"),
                rs.getDate("data_atendimento").toLocalDate(),
                rs.getString("motivo"),
                rs.getBigDecimal("valor")
        );
    }
}
package repository;

import model.Aluno;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoRepository {

    public Aluno salvar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (nome, email, telefone) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Aluno(rs.getLong("id"), aluno.getNome(), aluno.getEmail(), aluno.getTelefone());
        }
    }

    public Optional<Aluno> buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public List<Aluno> listarTodos() throws SQLException {
        String sql = "SELECT * FROM aluno ORDER BY nome";
        List<Aluno> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setLong(4, aluno.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Aluno mapear(ResultSet rs) throws SQLException {
        return new Aluno(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("telefone")
        );
    }
}

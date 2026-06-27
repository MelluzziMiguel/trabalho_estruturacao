package repository;

import model.Curso;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepository {

    public Curso salvar(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis) "
                   + "VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Curso(rs.getLong("id"), curso.getNome(), curso.getDescricao(),
                    curso.getCargaHoraria(), curso.getVagasTotais(), curso.getVagasDisponiveis());
        }
    }

    public Optional<Curso> buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM curso WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public List<Curso> listarTodos() throws SQLException {
        String sql = "SELECT * FROM curso ORDER BY nome";
        List<Curso> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void atualizar(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ?, "
                   + "vagas_totais = ?, vagas_disponiveis = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            stmt.setLong(6, curso.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Curso mapear(ResultSet rs) throws SQLException {
        return new Curso(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("carga_horaria"),
                rs.getInt("vagas_totais"),
                rs.getInt("vagas_disponiveis")
        );
    }
}

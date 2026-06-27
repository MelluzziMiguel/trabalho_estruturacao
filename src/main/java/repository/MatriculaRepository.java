package repository;

import model.Aluno;
import model.Curso;
import model.Matricula;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatriculaRepository {

    private AlunoRepository alunoRepository = new AlunoRepository();
    private CursoRepository cursoRepository = new CursoRepository();

    public Matricula salvar(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO matricula (id_aluno, id_curso, data_matricula, valor) "
                   + "VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, matricula.getAluno().getId());
            stmt.setLong(2, matricula.getCurso().getId());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setBigDecimal(4, matricula.getValor());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Matricula(rs.getLong("id"), matricula.getAluno(), matricula.getCurso(),
                    matricula.getDataMatricula(), matricula.getValor());
        }
    }

    public Optional<Matricula> buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
        }
    }

    public boolean existeMatricula(Long idAluno, Long idCurso) throws SQLException {
        String sql = "SELECT 1 FROM matricula WHERE id_aluno = ? AND id_curso = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idAluno);
            stmt.setLong(2, idCurso);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public List<Matricula> listarPorAluno(Long idAluno) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE id_aluno = ?";
        List<Matricula> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public List<Matricula> listarPorCurso(Long idCurso) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE id_curso = ?";
        List<Matricula> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public List<Matricula> listarTodos() throws SQLException {
        String sql = "SELECT * FROM matricula ORDER BY data_matricula";
        List<Matricula> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Matricula mapear(ResultSet rs) throws SQLException {
        Aluno aluno = alunoRepository.buscarPorId(rs.getLong("id_aluno"))
                .orElseThrow(() -> new SQLException("Aluno não encontrado"));
        Curso curso = cursoRepository.buscarPorId(rs.getLong("id_curso"))
                .orElseThrow(() -> new SQLException("Curso não encontrado"));
        return new Matricula(
                rs.getLong("id"),
                aluno,
                curso,
                rs.getDate("data_matricula").toLocalDate(),
                rs.getBigDecimal("valor")
        );
    }
}

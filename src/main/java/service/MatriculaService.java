package service;

import model.Aluno;
import model.Curso;
import model.Matricula;
import repository.CursoRepository;
import repository.MatriculaRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoRepository     cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository     = cursoRepository;
    }

    public Matricula matricular(Aluno aluno, Curso curso, BigDecimal valor) throws SQLException {

        // Regra 1: valor não pode ser negativo
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da matrícula não pode ser negativo.");
        }

        // Regra 2: não pode haver vagas esgotadas
        if (curso.getVagasDisponiveis() <= 0) {
            throw new IllegalArgumentException(
                    "Não é possível matricular: curso '" + curso.getNome() + "' não possui vagas disponíveis.");
        }

        // Regra 3: não pode matricular o mesmo aluno duas vezes no mesmo curso
        if (matriculaRepository.existeMatricula(aluno.getId(), curso.getId())) {
            throw new IllegalArgumentException(
                    "O aluno '" + aluno.getNome() + "' já está matriculado no curso '" + curso.getNome() + "'.");
        }

        // Persiste a matrícula
        Matricula matricula = new Matricula(aluno, curso, LocalDate.now(), valor);
        Matricula salva = matriculaRepository.salvar(matricula);

        // Decrementa vagas disponíveis no curso
        curso.setVagasDisponiveis(curso.getVagasDisponiveis() - 1);
        cursoRepository.atualizar(curso);

        return salva;
    }

    public List<Matricula> listarPorAluno(Aluno aluno) throws SQLException {
        return matriculaRepository.listarPorAluno(aluno.getId());
    }

    public List<Matricula> listarPorCurso(Curso curso) throws SQLException {
        return matriculaRepository.listarPorCurso(curso.getId());
    }
}

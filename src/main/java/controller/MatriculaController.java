package controller;

import model.Aluno;
import model.Curso;
import model.Matricula;
import service.MatriculaService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    public void realizarMatricula(Aluno aluno, Curso curso, BigDecimal valor) {
        try {
            Matricula matricula = matriculaService.matricular(aluno, curso, valor);
            System.out.println("Matrícula realizada com sucesso! " + matricula);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao salvar matrícula: " + e.getMessage());
        }
    }

    public void listarMatriculasDoAluno(Aluno aluno) {
        try {
            List<Matricula> lista = matriculaService.listarPorAluno(aluno);
            System.out.println("\n── Cursos de " + aluno.getNome() + " ──");
            if (lista.isEmpty()) {
                System.out.println("  Nenhuma matrícula encontrada.");
            } else {
                lista.forEach(m -> System.out.println("  " + m));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar matrículas: " + e.getMessage());
        }
    }

    public void listarAlunosDoCurso(Curso curso) {
        try {
            List<Matricula> lista = matriculaService.listarPorCurso(curso);
            System.out.println("\n── Alunos em " + curso.getNome() + " ──");
            if (lista.isEmpty()) {
                System.out.println("Nenhum aluno matriculado.");
            } else {
                lista.forEach(m -> System.out.println("  " + m.getAluno().getNome() + " — R$" + m.getValor()));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
        }
    }
}

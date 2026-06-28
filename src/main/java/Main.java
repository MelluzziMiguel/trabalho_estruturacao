import controller.MatriculaController;
import model.Aluno;
import model.Curso;
import repository.AlunoRepository;
import repository.CursoRepository;
import repository.MatriculaRepository;
import service.MatriculaService;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        // Instanciando repositórios, services e controller
        AlunoRepository     alunoRepository     = new AlunoRepository();
        CursoRepository     cursoRepository     = new CursoRepository();
        MatriculaRepository matriculaRepository = new MatriculaRepository();
        MatriculaService    matriculaService    = new MatriculaService(matriculaRepository, cursoRepository);
        MatriculaController matriculaController = new MatriculaController(matriculaService);

        try {
            System.out.println("Simulação Escola de Cursos Livres");

            System.out.println(">> Cadastrando alunos...");
            Aluno ana   = alunoRepository.salvar(new Aluno("Ana Lima",   "ana@email.com",   "11-91111-1111"));
            Aluno bruno = alunoRepository.salvar(new Aluno("Bruno Melo", "bruno@email.com", "11-92222-2222"));
            System.out.println("   " + ana);
            System.out.println("   " + bruno);

            System.out.println("\n>> Cadastrando cursos...");
            Curso java   = cursoRepository.salvar(new Curso("Java Básico",  "Introdução ao Java",       40, 1));
            Curso python = cursoRepository.salvar(new Curso("Python Dados", "Python para Data Science",  60, 10));
            System.out.println("   " + java);
            System.out.println("   " + python);

            System.out.println("\n>> Matriculando Ana em Java Básico (1 vaga disponível)...");
            matriculaController.realizarMatricula(ana, java, new BigDecimal("299.90"));

            System.out.println("\n>> Bruno tenta se matricular em Java Básico (sem vagas)...");
            Curso javaAtualizado = cursoRepository.buscarPorId(java.getId()).orElseThrow();
            matriculaController.realizarMatricula(bruno, javaAtualizado, new BigDecimal("299.90"));

            System.out.println("\n>> Ana tenta se matricular em Java Básico novamente (duplicidade)...");
            matriculaController.realizarMatricula(ana, javaAtualizado, new BigDecimal("299.90"));

            System.out.println("\n>> Ana e Bruno se matriculam em Python Dados...");
            matriculaController.realizarMatricula(ana,   python, new BigDecimal("499.90"));
            matriculaController.realizarMatricula(bruno, python, new BigDecimal("499.90"));

            matriculaController.listarMatriculasDoAluno(ana);
            matriculaController.listarAlunosDoCurso(python);

            System.out.println(" Simulação concluída");

        } catch (SQLException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

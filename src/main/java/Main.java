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
            System.out.println("========================================");
            System.out.println(" SIMULAÇÃO – Escola de Cursos Livres");
            System.out.println("========================================\n");

            // 1. Cadastrar alunos
            System.out.println(">> Cadastrando alunos...");
            Aluno ana   = alunoRepository.salvar(new Aluno("Ana Lima",   "ana@email.com",   "11-91111-1111"));
            Aluno bruno = alunoRepository.salvar(new Aluno("Bruno Melo", "bruno@email.com", "11-92222-2222"));
            System.out.println("   " + ana);
            System.out.println("   " + bruno);

            // 2. Cadastrar cursos
            System.out.println("\n>> Cadastrando cursos...");
            // Curso com apenas 1 vaga para testar esgotamento
            Curso java   = cursoRepository.salvar(new Curso("Java Básico",  "Introdução ao Java",       40, 1));
            Curso python = cursoRepository.salvar(new Curso("Python Dados", "Python para Data Science",  60, 10));
            System.out.println("   " + java);
            System.out.println("   " + python);

            // 3. Fluxo normal: Ana se matricula em Java
            System.out.println("\n>> Matriculando Ana em Java Básico (1 vaga disponível)...");
            matriculaController.realizarMatricula(ana, java, new BigDecimal("299.90"));

            // 4. Regra de negócio: Bruno tenta se matricular em Java (sem vagas)
            System.out.println("\n>> Bruno tenta se matricular em Java Básico (sem vagas)...");
            // Recarregamos o curso do banco para refletir vagas atualizadas
            Curso javaAtualizado = cursoRepository.buscarPorId(java.getId()).orElseThrow();
            matriculaController.realizarMatricula(bruno, javaAtualizado, new BigDecimal("299.90"));

            // 5. Regra de negócio: Ana tenta se matricular em Java novamente (duplicidade)
            System.out.println("\n>> Ana tenta se matricular em Java Básico novamente (duplicidade)...");
            matriculaController.realizarMatricula(ana, javaAtualizado, new BigDecimal("299.90"));

            // 6. Ana e Bruno se matriculam em Python
            System.out.println("\n>> Ana e Bruno se matriculam em Python Dados...");
            matriculaController.realizarMatricula(ana,   python, new BigDecimal("499.90"));
            matriculaController.realizarMatricula(bruno, python, new BigDecimal("499.90"));

            // 7. Consultas
            matriculaController.listarMatriculasDoAluno(ana);
            matriculaController.listarAlunosDoCurso(python);

            System.out.println("\n========================================");
            System.out.println(" Simulação concluída.");
            System.out.println("========================================");

        } catch (SQLException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

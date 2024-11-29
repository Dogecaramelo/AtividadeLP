import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Aluno> alunos;
    private Log log;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.log = new Log();
    }

    public void cadastrarAluno(String nome, double nota1, double nota2, double nota3) {
        alunos.add(new Aluno(nome, nota1, nota2, nota3));
    }

    public void exibirRelatorio() {
        System.out.println("Relatório dos Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public void exibirEstatisticas() {
        int aprovados = 0, recuperacao = 0, reprovados = 0;
        for (Aluno aluno : alunos) {
            switch (aluno.getStatus()) {
                case "Aprovado" -> aprovados++;
                case "Recuperação" -> recuperacao++;
                case "Reprovado" -> reprovados++;
            }
        }
        System.out.println("Estatísticas:");
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Em Recuperação: " + recuperacao);
        System.out.println("Reprovados: " + reprovados);
    }

    public void exibirListaRecuperacao() {
        System.out.println("Lista de Recuperação:");
        for (Aluno aluno : alunos) {
            if (aluno.getStatus().equals("Recuperação")) {
                System.out.println(aluno);
            }
        }
    }

    public void exibirListaReprovados() {
        System.out.println("Lista de Reprovados:");
        for (Aluno aluno : alunos) {
            if (aluno.getStatus().equals("Reprovado")) {
                System.out.println(aluno);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Coordenador coordenador = new Coordenador("Luis Araujo");
        Curso curso = new Curso("Algoritmos", "Prof. Luis Araujo", coordenador);
        Sistema sistema = new Sistema();

        curso.exibirInformacoesCurso();

        int numAlunos = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Digite o número de alunos: ");
                numAlunos = scanner.nextInt();
                scanner.nextLine();
                validInput = numAlunos > 0;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro válido.");
                scanner.nextLine();
            }
        }

        for (int i = 0; i < numAlunos; i++) {
            System.out.println("Aluno " + (i + 1) + ":");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            double nota1 = lerNota(scanner, "Nota 1: ");
            double nota2 = lerNota(scanner, "Nota 2: ");
            double nota3 = lerNota(scanner, "Nota 3: ");
            sistema.cadastrarAluno(nome, nota1, nota2, nota3);
        }

        sistema.exibirRelatorio();
        sistema.exibirEstatisticas();
        sistema.exibirListaRecuperacao();
        sistema.exibirListaReprovados();

        System.out.print("Digite o número do aluno para modificar a nota (1 a " + numAlunos + "): ");
        int alunoIndex = scanner.nextInt() - 1;
        if (alunoIndex >= 0 && alunoIndex < numAlunos) {
            double novaNota1 = lerNota(scanner, "Digite a nova nota 1: ");
            double novaNota2 = lerNota(scanner, "Digite a nova nota 2: ");
            double novaNota3 = lerNota(scanner, "Digite a nova nota 3: ");
            coordenador.modificarNota(sistema.alunos.get(alunoIndex), novaNota1, novaNota2, novaNota3, sistema.log);
        }

        sistema.log.exibirLog();
        scanner.close();
    }

    private static double lerNota(Scanner scanner, String prompt) {
        double nota = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(prompt);
                nota = scanner.nextDouble();
                validInput = nota >= 0 && nota <= 10;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido entre 0 e 10.");
                scanner.nextLine();
            }
        }
        return nota;
    }
}

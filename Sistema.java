public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Curso curso = new Curso("Algoritmos");

        // Adicionar Coordenadores
        System.out.print("Digite o número de coordenadores para o curso: ");
        int numCoordenadores = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCoordenadores; i++) {
            System.out.print("Nome do Coordenador " + (i + 1) + ": ");
            String nomeCoordenador = scanner.nextLine();
            curso.adicionarCoordenador(new Coordenador(nomeCoordenador));
        }

        // Adicionar Turmas
        System.out.print("Digite o número de turmas: ");
        int numTurmas = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numTurmas; i++) {
            System.out.print("Nome da Turma " + (i + 1) + ": ");
            String nomeTurma = scanner.nextLine();
            Turma turma = new Turma(nomeTurma);

            // Adicionar Professores à Turma
            System.out.print("Digite o número de professores na turma: ");
            int numProfessores = scanner.nextInt();
            scanner.nextLine();

            for (int j = 0; j < numProfessores; j++) {
                System.out.print("Nome do Professor " + (j + 1) + ": ");
                String nomeProfessor = scanner.nextLine();
                turma.adicionarProfessor(new Professor(nomeProfessor));
            }

            // Adicionar Alunos à Turma
            System.out.print("Digite o número de alunos na turma: ");
            int numAlunos = scanner.nextInt();
            scanner.nextLine();

            for (int k = 0; k < numAlunos; k++) {
                System.out.println("Aluno " + (k + 1) + ":");
                System.out.print("Nome: ");
                String nomeAluno = scanner.nextLine();
                double nota1 = lerNota(scanner, "Nota 1: ");
                double nota2 = lerNota(scanner, "Nota 2: ");
                double nota3 = lerNota(scanner, "Nota 3: ");
                turma.adicionarAluno(new Aluno(nomeAluno, nota1, nota2, nota3));
            }

            curso.adicionarTurma(turma);
        }

        // Exibir informações do curso
        curso.exibirInformacoesCurso();
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

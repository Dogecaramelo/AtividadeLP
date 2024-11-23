import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Aluno {
    String nome;
    double nota1, nota2, nota3;
    double notaRecuperacao = -1;  
    String status;

    public Aluno(String nome, double nota1, double nota2, double nota3) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.status = calcularStatus();
    }

    public double calcularMedia() {
        return (nota1 + nota2 + nota3) / 3;
    }

    public String calcularStatus() {
        double media = calcularMedia();
        if (media >= 7) {
            return "Aprovado";
        } else if (media >= 2.5) {
            return "Recuperação";
        } else {
            return "Reprovado";
        }
    }

    public void atribuirNotaRecuperacao(double notaRecuperacao) {
        this.notaRecuperacao = notaRecuperacao;
        if (notaRecuperacao >= 5) {
            this.status = "Aprovado";
        } else {
            this.status = "Reprovado";
        }
    }

    public String toString() {
        return nome + " - " + nota1 + ", " + nota2 + ", " + nota3 + " - " + status;
    }
}

class Coordenador {
    String nome;

    public Coordenador(String nome) {
        this.nome = nome;
    }

    public void modificarNota(Aluno aluno, double novaNota1, double novaNota2, double novaNota3, Log log) {
        double notaAnterior1 = aluno.nota1;
        double notaAnterior2 = aluno.nota2;
        double notaAnterior3 = aluno.nota3;

        aluno.nota1 = novaNota1;
        aluno.nota2 = novaNota2;
        aluno.nota3 = novaNota3;
        
        log.registrarMudanca("Coordenador", aluno.nome, notaAnterior1, novaNota1);
        log.registrarMudanca("Coordenador", aluno.nome, notaAnterior2, novaNota2);
        log.registrarMudanca("Coordenador", aluno.nome, notaAnterior3, novaNota3);
    }
}

class Log {
    List<String> historico;

    public Log() {
        this.historico = new ArrayList<>();
    }

    public void registrarMudanca(String cargo, String aluno, double notaAnterior, double notaAtual) {
        String log = cargo + " alterou a nota do aluno " + aluno + " de " + notaAnterior + " para " + notaAtual;
        historico.add(log);
    }

    public void exibirLog() {
        for (String log : historico) {
            System.out.println(log);
        }
    }
}

class Curso {
    String nome;
    String professor;
    Coordenador coordenador;

    public Curso(String nome, String professor, Coordenador coordenador) {
        this.nome = nome;
        this.professor = professor;
        this.coordenador = coordenador;
    }

    public void exibirInformacoesCurso() {
        System.out.println("Curso: " + nome);
        System.out.println("Professor: " + professor);
        System.out.println("Coordenador: " + coordenador.nome);
    }
}

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
            if (aluno.status.equals("Aprovado")) {
                aprovados++;
            } else if (aluno.status.equals("Recuperação")) {
                recuperacao++;
            } else {
                reprovados++;
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
            if (aluno.status.equals("Recuperação")) {
                System.out.println(aluno);
            }
        }
    }

    public void exibirListaReprovados() {
        System.out.println("Lista de Reprovados:");
        for (Aluno aluno : alunos) {
            if (aluno.status.equals("Reprovado")) {
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
                if (numAlunos <= 0) {
                    System.out.println("Por favor, insira um número válido maior que 0.");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
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
            System.out.println("Aluno escolhido: " + sistema.alunos.get(alunoIndex).nome);
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
                scanner.nextLine(); 
                if (nota < 0 || nota > 10) {
                    System.out.println("Nota inválida. Deve ser entre 0 e 10.");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.nextLine();  
            }
        }
        return nota;
    }
}

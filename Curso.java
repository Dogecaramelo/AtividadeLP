import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private List<Coordenador> coordenadores;
    private List<Turma> turmas;

    public Curso(String nome) {
        this.nome = nome;
        this.coordenadores = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void adicionarCoordenador(Coordenador coordenador) {
        coordenadores.add(coordenador);
    }

    public void adicionarTurma(Turma turma) {
        turmas.add(turma);
    }

    public void exibirInformacoesCurso() {
        System.out.println("Curso: " + nome);
        System.out.println("Coordenadores:");
        for (Coordenador coordenador : coordenadores) {
            System.out.println(" - " + coordenador.getNome());
        }
        for (Turma turma : turmas) {
            System.out.println("Turma: " + turma.getNome());
            System.out.println("Professores:");
            for (Professor professor : turma.getProfessores()) {
                System.out.println(" - " + professor.getNome());
            }
            System.out.println("Alunos:");
            for (Aluno aluno : turma.getAlunos()) {
                System.out.println(" - " + aluno);
            }
        }
    }
}

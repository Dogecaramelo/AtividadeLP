import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<String> historico;

    public Log() {
        this.historico = new ArrayList<>();
    }

    public void registrarMudanca(String cargo, String aluno, double notaAnterior, double notaAtual) {
        String log = cargo + " alterou a nota do aluno " + aluno + " de " + notaAnterior + " para " + notaAtual;
        historico.add(log);
    }

    public void registrarEvento(String evento) {
        historico.add(evento);
    }

    public void exibirLog() {
        System.out.println("Log do Sistema:");
        for (String log : historico) {
            System.out.println(log);
        }
    }
}

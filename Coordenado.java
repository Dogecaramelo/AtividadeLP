public class Coordenador {
    private String nome;

    public Coordenador(String nome) {
        this.nome = nome;
    }

    public void modificarNota(Aluno aluno, double novaNota1, double novaNota2, double novaNota3, Log log) {
        log.registrarMudanca("Coordenador", aluno.getNome(), aluno.nota1, novaNota1);
        log.registrarMudanca("Coordenador", aluno.getNome(), aluno.nota2, novaNota2);
        log.registrarMudanca("Coordenador", aluno.getNome(), aluno.nota3, novaNota3);

        aluno.nota1 = novaNota1;
        aluno.nota2 = novaNota2;
        aluno.nota3 = novaNota3;
    }

    public String getNome() {
        return nome;
    }
}

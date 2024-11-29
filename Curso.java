public class Curso {
    private String nome;
    private String professor;
    private Coordenador coordenador;

    public Curso(String nome, String professor, Coordenador coordenador) {
        this.nome = nome;
        this.professor = professor;
        this.coordenador = coordenador;
    }

    public void exibirInformacoesCurso() {
        System.out.println("Curso: " + nome);
        System.out.println("Professor: " + professor);
        System.out.println("Coordenador: " + coordenador.getNome());
    }
}

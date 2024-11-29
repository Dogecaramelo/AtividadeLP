public class Aluno {
    private String nome;
    private double nota1, nota2, nota3;
    private double notaRecuperacao = -1;
    private String status;

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
        this.status = notaRecuperacao >= 5 ? "Aprovado" : "Reprovado";
    }

    @Override
    public String toString() {
        return nome + " - " + nota1 + ", " + nota2 + ", " + nota3 + " - " + status;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
}

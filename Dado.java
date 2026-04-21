public class Dado {

    protected String nome;

    public Dado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Valor base do dado (padrão: 3 lados)
    public int rolar() {
        return gerarNumero(3);
    }

    // Método auxiliar reutilizável
    protected int gerarNumero(int lados) {
        return (int) (Math.random() * lados) + 1;
    }

    // Aplica efeito do dado
    public int calcularValor(int valorBase) {
        return valorBase;
    }
}
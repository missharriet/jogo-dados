import java.util.Random;

public class Dado {

    private String nome;
    private Random random;

    // Construtor
    public Dado(String nome) {
        this.nome = nome;
        this.random = new Random();
    }

    // Método rolar (dado de 3 lados)
    public int rolar() {
        return random.nextInt(3) + 1; // gera 1, 2 ou 3
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }
}
public class Carta {

    private String nome;
    private String tipo; // "multiplicador" ou "bonus"

    // Construtor
    public Carta(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getter do nome
    public String getNome() {
        return nome;
    }

    // Getter do tipo
    public String getTipo() {
        return tipo;
    }
}
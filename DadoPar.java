public class DadoPar extends Dado {

    public DadoPar() {
        super("Dado Par");
    }

    @Override
    public int rolar() {
        // gera números pares de 2 a 12
        return (int) (Math.random() * 6 + 1) * 2;
    }
}
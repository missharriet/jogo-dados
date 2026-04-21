public class Dado10 extends Dado {

    public Dado10() {
        super("Dado de 10 lados");
    }

    @Override
    public int rolar() {
        return gerarNumero(10);
    }
}
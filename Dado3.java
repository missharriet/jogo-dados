public class Dado3 extends Dado {

    public Dado3() {
        super("Dado de 3 lados");
    }

    @Override
    public int rolar() {
        return gerarNumero(3);
    }
}
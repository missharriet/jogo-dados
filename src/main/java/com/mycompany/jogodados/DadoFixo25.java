package com.mycompany.jogodados;
public class DadoFixo25 extends Dado {

    public DadoFixo25() {
        super("Dado Fixo 25");
    }

    @Override
    public int rolar() {
        return 25;
    }
}
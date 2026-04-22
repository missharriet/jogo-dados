package com.mycompany.jogodados;
public class Dado6 extends Dado {

    public Dado6() {
        super("Dado de 6 lados");
    }

    @Override
    public int rolar() {
        return gerarNumero(6);
    }
}
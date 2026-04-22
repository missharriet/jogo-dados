package com.mycompany.jogodados;
public class DadoImpar extends Dado {

    public DadoImpar() {
        super("Dado Ímpar");
    }

    @Override
    public int rolar() {
        // gera números ímpares de 1 a 11
        return (int) (Math.random() * 6) * 2 + 1;
    }
}
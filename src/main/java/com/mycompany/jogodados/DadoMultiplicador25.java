package com.mycompany.jogodados;
public class DadoMultiplicador25 extends Dado {

    public DadoMultiplicador25() {
        super("Dado x2.5");
    }

    @Override
    public int rolar() {
        return 1; 
    }

    @Override
    public int calcularValor(int base) {
        return 0; 
    }
}
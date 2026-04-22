package com.mycompany.jogodados;
public class DadoMultiplicador extends Dado {

    public DadoMultiplicador() {
        super("Dado 1.5x");
    }

    @Override
    public int calcularValor(int valorBase) {
        return (int) (valorBase * 1.5);
    }
}
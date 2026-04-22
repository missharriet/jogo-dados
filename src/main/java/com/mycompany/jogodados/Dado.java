package com.mycompany.jogodados;

public class Dado {

    protected String nome;

    public Dado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Valor base do dado 
    public int rolar() {
        return gerarNumero(3);
    }

    // Método auxiliar reutilizável necessário para os outros dados
    protected int gerarNumero(int lados) {
        return (int) (Math.random() * lados) + 1;
    }

    // Aplica efeito do dado
    public int calcularValor(int valorBase) {
        return valorBase;
    }
}
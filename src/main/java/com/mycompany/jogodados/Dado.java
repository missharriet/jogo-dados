<<<<<<< HEAD:Dado.java
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jogodados;
import java.util.Random;

/**
 *
 * @author contm
 */
>>>>>>> caf0e75dc9aa442400f2087801e0cad852232db8:src/main/java/com/mycompany/jogodados/Dado.java
public class Dado {

    protected String nome;

    public Dado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Valor base do dado (padrão: 3 lados)
    public int rolar() {
        return gerarNumero(3);
    }

    // Método auxiliar reutilizável
    protected int gerarNumero(int lados) {
        return (int) (Math.random() * lados) + 1;
    }

    // Aplica efeito do dado
    public int calcularValor(int valorBase) {
        return valorBase;
    }
}
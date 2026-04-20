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
public class Dado {

    private String nome;
    private Random random;

    // Construtor
    public Dado(String nome) {
        this.nome = nome;
        this.random = new Random();
    }

    // Método rolar (dado de 3 lados)
    public int rolar() {
        return random.nextInt(3) + 1; // gera 1, 2 ou 3
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jogodados;

/**
 *
 * @author contm
 */
public class Carta {

    private String nome;
    private String tipo; // multiplicador ou bonus

    // Construtor
    public Carta(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getter do nome
    public String getNome() {
        return nome;
    }

    // Getter do tipo
    public String getTipo() {
        return tipo;
    }
}

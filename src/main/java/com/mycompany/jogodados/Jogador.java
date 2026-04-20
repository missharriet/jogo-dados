/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jogodados;
import java.util.ArrayList;


/**
 *
 * @author contm
 */
public class Jogador {

    private ArrayList<Dado> dados;
    private int pontos;

    // Construtor
    public Jogador() {
        this.dados = new ArrayList<>();
        this.pontos = 0;
    }

    // Adicionar dado
    public void adicionarDado(Dado dado) {
        dados.add(dado);
    }

    // Getter dos dados
    public ArrayList<Dado> getDados() {
        return dados;
    }

    // Getter dos pontos
    public int getPontos() {
        return pontos;
    }

    // Adicionar pontos
    public void adicionarPontos(int valor) {
        pontos += valor;
    }

    // Resetar pontos
    public void resetarPontos() {
        pontos = 0;
    }
}
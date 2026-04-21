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
    private ArrayList<Carta> cartas;
    private int pontos;

    // Construtor
    public Jogador() {
        this.dados = new ArrayList<>();
        this.cartas = new ArrayList<>();
        this.pontos = 0;
    }

    // DADOS

    public void adicionarDado(Dado dado) {
        if (dados.size() < 10) {
            dados.add(dado);
        } else {
            System.out.println("Limite de 10 dados atingido!");
        }
    }

    public void removerDado(int index) {
        if (index >= 0 && index < dados.size()) {
            dados.remove(index);
        }
    }

    public ArrayList<Dado> getDados() {
        return dados;
    }

    // CARTAS

    public void adicionarCarta(Carta carta) {
        if (cartas.size() < 3) {
            cartas.add(carta);
        } else {
            System.out.println("Limite de 3 cartas atingido!");
        }
    }

    public void removerCarta(int index) {
        if (index >= 0 && index < cartas.size()) {
            cartas.remove(index);
        }
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    // PONTOS

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int valor) {
        pontos += valor;
    }

    public boolean gastarPontos(int valor) {
        if (pontos >= valor) {
            pontos -= valor;
            return true;
        }
        return false;
    }

    // Reset
    public void resetarPontos() {
        pontos = 0;
    }
}
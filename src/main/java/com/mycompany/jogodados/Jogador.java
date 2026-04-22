package com.mycompany.jogodados;
import java.util.ArrayList;

public class Jogador {

    private ArrayList<Dado> dados;
    private ArrayList<Carta> cartas; 
    private int pontos;

    public Jogador() {
        this.dados = new ArrayList<>();
        this.cartas = new ArrayList<>();
        this.pontos = 0;
    }

    public void adicionarDado(Dado dado) {
        dados.add(dado);
    }

    public void removerDado(int index) { 
        if (index >= 0 && index < dados.size()) {
            dados.remove(index);
        }
    }

    public ArrayList<Dado> getDados() {
        return dados;
    }

    public void adicionarCarta(Carta carta) { 
        cartas.add(carta);
    }

    public ArrayList<Carta> getCartas() { 
        return cartas;
    }

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

    public void resetarPontos() {
        pontos = 0;
    }
}
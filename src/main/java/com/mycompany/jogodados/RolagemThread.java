package com.mycompany.jogodados;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class RolagemThread extends Thread {

    private boolean rodando = true;
    private JLabel[] labels;
    private int quantidadeDados;

    public RolagemThread(JLabel[] labels, int quantidadeDados) {
        this.labels = labels;
        this.quantidadeDados = quantidadeDados;
    }

    public void parar() {
        rodando = false;
    }

    @Override
    public void run() {
        try {
            while (rodando) {
                // SwingUtilities garante que a UI seja atualizada na thread correta (EDT)
                SwingUtilities.invokeLater(() -> {
                    for (int i = 0; i < 10; i++) {
                        if (i < quantidadeDados) {
                            // Gera um número visual aleatório de 1 a 6 apenas para a animação
                            int valorVisual = (int) (Math.random() * 6) + 1;
                            labels[i].setText(String.valueOf(valorVisual));
                        }
                    }
                });
                
                // Controla a velocidade da "animação" de rolagem
                Thread.sleep(80); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
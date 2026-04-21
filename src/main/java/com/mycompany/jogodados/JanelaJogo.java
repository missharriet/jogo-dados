package com.mycompany.jogodados;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaJogo extends JFrame {

    // Lógica do jogo
    private int[] metas = {20, 30, 40};
    private int rodadaAtual = 0;
    private Jogador jogador;
    private Carta cartaBonus = new Carta("+8 por dado", "bonus");
    private Carta cartaMultiplicador = new Carta("2x Rodada", "multiplicador");

    // Elementos visuais
    private JLabel lblRodada;
    private JLabel lblMeta;
    private JLabel lblStatusGeral;
    private JPanel painelDados;
    private JLabel[] labelsDados;
    private JButton btnRolar;

    public JanelaJogo() {
        // Configurações da janela
        setTitle("Jogo de Dados - Arena");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52)); // Cor de fundo escura 

        // Inicializar jogador e dados
        jogador = new Jogador();
        for (int i = 0; i < 10; i++) {
            jogador.adicionarDado(new Dado("Dado de 3 lados"));
        }

        // --- PAINEL SUPERIOR ---
        JPanel painelTop = new JPanel(new GridLayout(2, 1));
        painelTop.setOpaque(false);
        painelTop.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblRodada = new JLabel("Rodada: 1 de " + metas.length, SwingConstants.CENTER);
        lblRodada.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblRodada.setForeground(Color.WHITE);

        lblMeta = new JLabel("Meta para atingir: " + metas[0], SwingConstants.CENTER);
        lblMeta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblMeta.setForeground(new Color(255, 215, 0)); // Dourado

        painelTop.add(lblRodada);
        painelTop.add(lblMeta);
        add(painelTop, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (Dados Visuais) ---
        painelDados = new JPanel(new GridLayout(2, 5, 10, 10));
        painelDados.setOpaque(false);
        painelDados.setBorder(new EmptyBorder(10, 30, 10, 30));
        labelsDados = new JLabel[10];

        // Criar os quadradinhos dos dados
        for (int i = 0; i < 10; i++) {
            labelsDados[i] = new JLabel("?", SwingConstants.CENTER);
            labelsDados[i].setFont(new Font("SansSerif", Font.BOLD, 36));
            labelsDados[i].setOpaque(true);
            labelsDados[i].setBackground(new Color(240, 240, 240));
            labelsDados[i].setForeground(Color.DARK_GRAY);
            labelsDados[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            painelDados.add(labelsDados[i]);
        }
        add(painelDados, BorderLayout.CENTER);

        // --- PAINEL INFERIOR ---
        JPanel painelBotton = new JPanel(new BorderLayout(10, 10));
        painelBotton.setOpaque(false);
        painelBotton.setBorder(new EmptyBorder(15, 15, 20, 15));

        lblStatusGeral = new JLabel("Clique em Rolar para começar!", SwingConstants.CENTER);
        lblStatusGeral.setFont(new Font("SansSerif", Font.ITALIC, 16));
        lblStatusGeral.setForeground(Color.LIGHT_GRAY);
        painelBotton.add(lblStatusGeral, BorderLayout.CENTER);

        btnRolar = new JButton("ROLAR DADOS");
        btnRolar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnRolar.setBackground(new Color(46, 204, 113)); // Verde chamativo
        btnRolar.setForeground(Color.WHITE);
        btnRolar.setFocusPainted(false);
        btnRolar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarRodada();
            }
        });

        painelBotton.add(btnRolar, BorderLayout.SOUTH);
        add(painelBotton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void executarRodada() {
        if (rodadaAtual >= metas.length) {
            return; // Jogo já acabou
        }

        int soma = 0;

        // Rolar dados e atualizar a interface visual
        for (int i = 0; i < jogador.getDados().size(); i++) {
            Dado d = jogador.getDados().get(i);
            int valor = d.rolar();

            // Lógica de pares x2
            boolean foiDobrado = false;
            if (valor % 2 == 0) {
                valor = valor * 2;
                foiDobrado = true;
            }

            soma += valor;

            // Atualizar o visual do dado
            labelsDados[i].setText(String.valueOf(valor));
            if (foiDobrado) {
                labelsDados[i].setBackground(new Color(173, 216, 230)); // Azul claro se foi dobrado
                labelsDados[i].setToolTipText("Dobrado por ser par!");
            } else {
                labelsDados[i].setBackground(new Color(240, 240, 240));
                labelsDados[i].setToolTipText("Valor normal");
            }
        }

        // Aplicar bônus
        int bonus = jogador.getDados().size() * 8;
        soma += bonus;

        // Aplicar multiplicador
        soma = soma * 2;

        //Verificar Vitória/Derrota
        String resultadoRodada = (soma >= metas[rodadaAtual]) ? "VITÓRIA!" : "DERROTA!";
        
        // Atualizar textos
        String textoStatus = String.format("<html><div style='text-align: center;'>Soma base + Bônus (%s) x Carta (%s)<br><b style='font-size: 18px; color: %s;'>Total Final: %d - %s</b></div></html>", 
                cartaBonus.getNome(), cartaMultiplicador.getNome(), 
                (soma >= metas[rodadaAtual] ? "#2ecc71" : "#e74c3c"), // Verde ou Vermelho
                soma, resultadoRodada);
        
        lblStatusGeral.setText(textoStatus);

        // Avançar rodada
        rodadaAtual++;

        // Atualizar topo para a próxima rodada ou finalizar jogo
        if (rodadaAtual < metas.length) {
            lblRodada.setText("Rodada: " + (rodadaAtual + 1) + " de " + metas.length);
            lblMeta.setText("Meta a atingir: " + metas[rodadaAtual]);
            btnRolar.setText("PRÓXIMA RODADA");
        } else {
            lblRodada.setText("FIM DE JOGO");
            lblMeta.setText("Todas as rodadas concluídas!");
            btnRolar.setText("JOGO FINALIZADO");
            btnRolar.setEnabled(false);
            btnRolar.setBackground(Color.GRAY);
        }
    }

}
package com.mycompany.jogodados;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaJogo extends JFrame {

    // Lógica do jogo
    private int[] metas = {20, 30, 40, 100, 350}; 
    private int rodadaAtual = 0;
    private Jogador jogador;
    private boolean jogoTerminado = false; // Flag para controlar se o jogo acabou

    // Elementos visuais
    private JLabel lblRodada;
    private JLabel lblMeta;
    private JLabel lblPontos;
    private JLabel lblStatusGeral;
    private JPanel painelDados;
    private JLabel[] labelsDados;
    private JButton btnRolar;
    private JButton btnLoja;

    public JanelaJogo() {
        setTitle("Jogo de Dados - Arena");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52));

        jogador = new Jogador();
        
        // Iniciar com os dados
        for (int i = 0; i < 10; i++) {
            jogador.adicionarDado(new Dado3());
        }

        // --- PAINEL SUPERIOR ---
        JPanel painelTop = new JPanel(new GridLayout(3, 1));
        painelTop.setOpaque(false);
        painelTop.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblRodada = new JLabel("Rodada: 1 de " + metas.length, SwingConstants.CENTER);
        lblRodada.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblRodada.setForeground(Color.WHITE);

        lblMeta = new JLabel("Meta a atingir: " + metas[0], SwingConstants.CENTER);
        lblMeta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblMeta.setForeground(new Color(255, 215, 0));

        lblPontos = new JLabel("Pontos: " + jogador.getPontos(), SwingConstants.CENTER);
        lblPontos.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPontos.setForeground(new Color(52, 152, 219)); // Azul

        painelTop.add(lblRodada);
        painelTop.add(lblMeta);
        painelTop.add(lblPontos);
        add(painelTop, BorderLayout.NORTH);

       
        painelDados = new JPanel(new GridLayout(2, 5, 10, 10));
        painelDados.setOpaque(false);
        painelDados.setBorder(new EmptyBorder(10, 30, 10, 30));
        labelsDados = new JLabel[10];

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
        painelBotton.add(lblStatusGeral, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelBotoes.setOpaque(false);

        btnLoja = new JButton("🛒 ABRIR LOJA");
        btnLoja.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnLoja.setBackground(new Color(241, 196, 15));
        btnLoja.setForeground(Color.BLACK);
        btnLoja.setFocusPainted(false);
        btnLoja.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoja.addActionListener(e -> abrirLoja());

        btnRolar = new JButton("🎲 ROLAR DADOS");
        btnRolar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnRolar.setBackground(new Color(46, 204, 113));
        btnRolar.setForeground(Color.WHITE);
        btnRolar.setFocusPainted(false);
        btnRolar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRolar.addActionListener(e -> executarRodada());

        painelBotoes.add(btnLoja);
        painelBotoes.add(btnRolar);
        
        painelBotton.add(painelBotoes, BorderLayout.SOUTH);
        add(painelBotton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void executarRodada() {
        // botão para reiniciar
        if (jogoTerminado) {
            reiniciarJogo();
            return;
        }

        if (rodadaAtual >= metas.length) return;

        int soma = 0;

        // Limpar visual anterior e rolar dados
        for (int i = 0; i < 10; i++) {
            if (i < jogador.getDados().size()) {
                Dado d = jogador.getDados().get(i);
                int base = d.rolar();
                int valor = d.calcularValor(base);

                // Aplicar efeitos de cartas condicionais
                for (Carta c : jogador.getCartas()) {
                    if (c.getNome().equals("2x Pares") && valor % 2 == 0) {
                        valor *= 2;
                    }
                    if (c.getNome().equals("2x Ímpares") && valor % 2 != 0) {
                        valor *= 2;
                    }
                }

                soma += valor;
                labelsDados[i].setText(String.valueOf(valor));
                labelsDados[i].setBackground(new Color(240, 240, 240));
                labelsDados[i].setToolTipText(d.getNome());
            } else {
                labelsDados[i].setText("");
                labelsDados[i].setBackground(Color.DARK_GRAY);
            }
        }

        // Bónus gerais (Cartas)
        for (Carta c : jogador.getCartas()) {
            if (c.getTipo().equals("bonus")) {
                soma += jogador.getDados().size() * 8;
            }
        }

        // Multiplicadores de Cartas
        for (Carta c : jogador.getCartas()) {
            if (c.getTipo().equals("multiplicador")) {
                soma *= 2;
            }
        }

        // Multiplicadores de Dados Especiais
        double multiExtra = 1.0;
        for (Dado d : jogador.getDados()) {
            if (d instanceof DadoMultiplicador25) {
                multiExtra *= 2.5;
            }
        }
        soma = (int)(soma * multiExtra);

        // Verificar resultado
        boolean vitoria = soma >= metas[rodadaAtual];
        String resultadoRodada = vitoria ? "VITÓRIA!" : "DERROTA!";
        int recompensa = vitoria ? (soma / 2) : 0;
        
        if (vitoria) jogador.adicionarPontos(recompensa);
        lblPontos.setText("Pontos: " + jogador.getPontos());

        String textoStatus = String.format("<html><div style='text-align: center;'><b style='font-size: 18px; color: %s;'>Total Final: %d - %s</b><br>Recompensa: +%d pontos</div></html>", 
                (vitoria ? "#2ecc71" : "#e74c3c"), soma, resultadoRodada, recompensa);
        lblStatusGeral.setText(textoStatus);

        // Derrota 
        if (!vitoria) {
            jogoTerminado = true;
            btnRolar.setText("🔄 JOGAR NOVAMENTE");
            btnRolar.setBackground(new Color(52, 152, 219)); // Botão azul
            btnLoja.setEnabled(false);
            return;
        }

        rodadaAtual++;

        if (rodadaAtual < metas.length) {
            lblRodada.setText("Rodada: " + (rodadaAtual + 1) + " de " + metas.length);
            lblMeta.setText("Meta: " + metas[rodadaAtual]);
        } else {
            // Vitória final 
            jogoTerminado = true;
            lblRodada.setText("JOGO CONCLUÍDO");
            lblMeta.setText("Parabéns, sobreviveu à Arena!");
            btnRolar.setText("🔄 JOGAR NOVAMENTE");
            btnRolar.setBackground(new Color(52, 152, 219)); // Botão azul
            btnLoja.setEnabled(false);
        }
    }

    private void reiniciarJogo() {
        // Resetar variáveis
        rodadaAtual = 0;
        jogoTerminado = false;
        jogador = new Jogador();
        
        for (int i = 0; i < 10; i++) {
            jogador.adicionarDado(new Dado3());
        }

        // Resetar interface visuais
        lblRodada.setText("Rodada: 1 de " + metas.length);
        lblMeta.setText("Meta a atingir: " + metas[0]);
        lblPontos.setText("Pontos: 0");
        lblStatusGeral.setText("Clique em Rolar para começar!");
        
        btnRolar.setText("🎲 ROLAR DADOS");
        btnRolar.setBackground(new Color(46, 204, 113)); // Volta ao verde original
        btnLoja.setEnabled(true);

        // Limpar dados visuais
        for (int i = 0; i < 10; i++) {
            labelsDados[i].setText("?");
            labelsDados[i].setBackground(new Color(240, 240, 240));
            labelsDados[i].setToolTipText(null);
        }
    }

    private void abrirLoja() {
        JDialog dialogLoja = new JDialog(this, "Loja de Dados e Cartas", true);
        dialogLoja.setSize(400, 400);
        dialogLoja.setLayout(new GridLayout(6, 1, 5, 5));
        dialogLoja.setLocationRelativeTo(this);

        JLabel lblInfo = new JLabel("Pontos disponíveis: " + jogador.getPontos(), SwingConstants.CENTER);
        lblInfo.setFont(new Font("SansSerif", Font.BOLD, 16));
        dialogLoja.add(lblInfo);

        adicionarBotaoLoja(dialogLoja, "Dado de 6 Lados (10 pts)", 10, new Dado6(), null, lblInfo);
        adicionarBotaoLoja(dialogLoja, "Dado Par (12 pts)", 12, new DadoPar(), null, lblInfo);
        adicionarBotaoLoja(dialogLoja, "Dado Fixo 25 (20 pts)", 20, new DadoFixo25(), null, lblInfo);
        adicionarBotaoLoja(dialogLoja, "Carta 2x Rodada (25 pts)", 25, null, new Carta("2x Rodada", "multiplicador"), lblInfo);
        adicionarBotaoLoja(dialogLoja, "Carta +8 Por Dado (20 pts)", 20, null, new Carta("+8 por dado", "bonus"), lblInfo);

        dialogLoja.setVisible(true);
    }

    private void adicionarBotaoLoja(JDialog dialog, String texto, int custo, Dado dado, Carta carta, JLabel lblInfo) {
        JButton btn = new JButton(texto);
        btn.addActionListener(e -> {
            
            // 1. Verifica se o jogador tem os pontos 
            if (jogador.getPontos() >= custo) {
                
                if (dado != null) {
                    if (jogador.getDados().size() >= 10) {
                        // 2. Prepara a lista de opções com os dados atuais
                        String[] opcoes = new String[jogador.getDados().size()];
                        for (int i = 0; i < jogador.getDados().size(); i++) {
                            opcoes[i] = "Posição " + (i + 1) + " - " + jogador.getDados().get(i).getNome();
                        }

                        // 3. Mostra o diálogo para o usuário escolher qual dado substituir
                        String escolha = (String) JOptionPane.showInputDialog(
                                dialog,
                                "Limite de 10 dados atingido!\nEscolha qual dado você deseja substituir:",
                                "Substituir dado",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opcoes,
                                opcoes[0]
                        );

                        // 4. Se o usuário fez uma escolha 
                        if (escolha != null) {
                            // Encontra o índice da escolha
                            int index = -1;
                            for (int i = 0; i < opcoes.length; i++) {
                                if (opcoes[i].equals(escolha)) {
                                    index = i;
                                    break;
                                }
                            }

                            // 5. Gasta os pontos e faz a substituição
                            if (index != -1) {
                                jogador.gastarPontos(custo);
                                jogador.removerDado(index);
                                jogador.adicionarDado(dado);
                                
                                lblInfo.setText("Pontos disponíveis: " + jogador.getPontos());
                                lblPontos.setText("Pontos: " + jogador.getPontos());
                                JOptionPane.showMessageDialog(dialog, "Compra efetuada com sucesso! Dado substituído.");
                            }
                        } else {
                            // Usuário fechou a janela ou clicou em cancelar
                            JOptionPane.showMessageDialog(dialog, "Compra cancelada.");
                        }
                        
                    } else {
                        // Tem menos de 10 dados, pode comprar direto
                        jogador.gastarPontos(custo);
                        jogador.adicionarDado(dado);
                        
                        lblInfo.setText("Pontos disponíveis: " + jogador.getPontos());
                        lblPontos.setText("Pontos: " + jogador.getPontos());
                        JOptionPane.showMessageDialog(dialog, "Compra efetuada com sucesso!");
                    }
                    
                } else if (carta != null) {
                    if (jogador.getCartas().size() < 3) {
                        jogador.gastarPontos(custo);
                        jogador.adicionarCarta(carta);
                        
                        lblInfo.setText("Pontos disponíveis: " + jogador.getPontos());
                        lblPontos.setText("Pontos: " + jogador.getPontos());
                        JOptionPane.showMessageDialog(dialog, "Compra efetuada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Limite de 3 cartas atingido!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Pontos insuficientes!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btn);
    }
}
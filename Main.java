import java.util.Scanner;

public class Main {

    // GERENCIAMENTO DE DADOS
    public static void gerenciarDados(Jogador jogador, Dado novoDado, Scanner sc) {

        System.out.println("\n🎲 Seus dados atuais:");

        for (int i = 0; i < jogador.getDados().size(); i++) {
            System.out.println(i + " - " + jogador.getDados().get(i).getNome());
        }

        System.out.println("\nVocê ganhou: " + novoDado.getNome());
        System.out.println("1 - Substituir um dado");
        System.out.println("2 - Descartar novo dado");

        int escolha = sc.nextInt();

        if (escolha == 1) {
            System.out.println("Escolha o índice:");
            int indice = sc.nextInt();

            if (indice >= 0 && indice < jogador.getDados().size()) {
                jogador.removerDado(indice);
                jogador.adicionarDado(novoDado);
                System.out.println("Dado substituído!");
            } else {
                System.out.println("Índice inválido.");
            }
        } else {
            System.out.println("Dado descartado.");
        }
    }

    // LOJA
    public static void loja(Jogador jogador, Scanner sc) {

        while (true) {

            System.out.println("\n🛒 LOJA");
            System.out.println("Pontos: " + jogador.getPontos());

            System.out.println("\n--- DADOS ---");
            System.out.println("1 - Dado 6 lados (10)");
            System.out.println("2 - Dado 10 lados (15)");
            System.out.println("3 - Dado Par (12)");
            System.out.println("4 - Dado Ímpar (12)");
            System.out.println("5 - Dado Fixo 25 (20)");
            System.out.println("6 - Dado x2.5 (40)");

            System.out.println("\n--- CARTAS ---");
            System.out.println("7 - 2x Rodada (25)");
            System.out.println("8 - +8 por dado (20)");
            System.out.println("9 - 2x Pares (15)");
            System.out.println("10 - 2x Ímpares (15)");

            System.out.println("0 - Sair");

            int op = sc.nextInt();

            Dado novoDado = null;
            Carta novaCarta = null;
            int custo = 0;

            switch (op) {
                case 1: novoDado = new Dado6(); custo = 10; break;
                case 2: novoDado = new Dado10(); custo = 15; break;
                case 3: novoDado = new DadoPar(); custo = 12; break;
                case 4: novoDado = new DadoImpar(); custo = 12; break;
                case 5: novoDado = new DadoFixo25(); custo = 20; break;
                case 6: novoDado = new DadoMultiplicador25(); custo = 40; break;

                case 7: novaCarta = new Carta("2x Rodada", "multiplicador"); custo = 25; break;
                case 8: novaCarta = new Carta("+8 por dado", "bonus"); custo = 20; break;
                case 9: novaCarta = new Carta("2x Pares", "condicional"); custo = 15; break;
                case 10: novaCarta = new Carta("2x Ímpares", "condicional"); custo = 15; break;

                case 0: return;

                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            if (!jogador.gastarPontos(custo)) {
                System.out.println("❌ Pontos insuficientes!");
                continue;
            }

            if (novoDado != null) {

                System.out.println("Você comprou: " + novoDado.getNome());

                if (jogador.getDados().size() < 10) {
                    jogador.adicionarDado(novoDado);
                } else {
                    gerenciarDados(jogador, novoDado, sc);
                }
            }

            if (novaCarta != null) {

                if (jogador.getCartas().size() >= 3) {
                    System.out.println("Você já tem o máximo de cartas!");
                } else {
                    jogador.adicionarCarta(novaCarta);
                    System.out.println("Você comprou: " + novaCarta.getNome());
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] metas = {10, 20, 30};

        Jogador jogador = new Jogador();

        // dados iniciais
        for (int i = 0; i < 10; i++) {
            jogador.adicionarDado(new Dado3());
        }

        // RODADAS
        for (int rodada = 0; rodada < metas.length; rodada++) {

            System.out.println("\n=======================");
            System.out.println("Rodada " + (rodada + 1));
            System.out.println("Meta: " + metas[rodada]);
            System.out.println("=======================\n");

            int soma = 0;

            System.out.println("Rolando dados");

            // THREAD
            RolagemThread t = new RolagemThread();
            t.start();

            for (Dado d : jogador.getDados()) {

                int base = d.rolar();
                int valor = d.calcularValor(base);

                for (Carta c : jogador.getCartas()) {

                    if (c.getNome().equals("2x Pares") && valor % 2 == 0) {
                        valor *= 2;
                    }

                    if (c.getNome().equals("2x Ímpares") && valor % 2 != 0) {
                        valor *= 2;
                    }
                }

                System.out.println(d.getNome() + ": " + valor);
                soma += valor;
            }

            t.parar();
            System.out.println();

            System.out.println("\nSoma base: " + soma);

            // bônus
            for (Carta c : jogador.getCartas()) {
                if (c.getTipo().equals("bonus")) {
                    soma += jogador.getDados().size() * 8;
                }
            }

            System.out.println("Após bônus: " + soma);

            // multiplicador cartas
            for (Carta c : jogador.getCartas()) {
                if (c.getTipo().equals("multiplicador")) {
                    soma *= 2;
                }
            }

            // multiplicador dados
            double multiExtra = 1.0;

            for (Dado d : jogador.getDados()) {
                if (d instanceof DadoMultiplicador25) {
                    multiExtra *= 2.5;
                }
            }

            soma = (int)(soma * multiExtra);

            System.out.println("Total final: " + soma);

            if (soma < metas[rodada]) {
                System.out.println("💀 Derrota!");
                return;
            }

            int recompensa = soma / 2;
            jogador.adicionarPontos(recompensa);

            System.out.println("Você ganhou " + recompensa + " pontos.");
            System.out.println("Total: " + jogador.getPontos());

            if (rodada < metas.length - 1) {
                loja(jogador, sc);
            }
        }

        // CHEFE FINAL
        System.out.println("\n CHEFE FINAL: Chefe Par (ímpares = 0)");

        int soma = 0;

        for (Dado d : jogador.getDados()) {

            int valor = d.calcularValor(d.rolar());

            if (valor % 2 != 0) {
                valor = 0;
            }

            soma += valor;
        }

        soma += jogador.getDados().size() * 8;
        soma *= 2;

        System.out.println("Total final: " + soma);

        if (soma >= 350) {
            System.out.println("🏆 Vitória!");
        } else {
            System.out.println("💀 Derrota!");
        }

        System.out.println("\n🏁 Fim do jogo!");
    }
}
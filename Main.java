public class Main {

    public static void main(String[] args) {

        // Metas das rodadas
        int[] metas = {20, 30, 40};

        // Criar jogador
        Jogador jogador = new Jogador();

        // Adicionar 10 dados de 3 lados
        for (int i = 0; i < 10; i++) {
            jogador.adicionarDado(new Dado("Dado de 3 lados"));
        }

        // Cartas (simples)
        Carta cartaBonus = new Carta("+8 por dado", "bonus");
        Carta cartaMultiplicador = new Carta("2x Rodada", "multiplicador");
        Carta cartaPares = new Carta("2x Pares", "condicional");

        // Loop de rodadas
        for (int rodada = 0; rodada < metas.length; rodada++) {

            System.out.println("\n=======================");
            System.out.println("Rodada " + (rodada + 1));
            System.out.println("Meta: " + metas[rodada]);
            System.out.println("=======================\n");

            int soma = 0;

            // 1. Rolar dados + aplicar 2x pares
            for (Dado d : jogador.getDados()) {
                int valor = d.rolar();

                if (valor % 2 == 0) {
                    valor = valor * 2;
                }

                System.out.println(d.getNome() + ": " + valor);
                soma += valor;
            }

            System.out.println("\nSoma dos dados: " + soma);

            // 2. Aplicar bônus (+8 por dado)
            int bonus = jogador.getDados().size() * 8;
            soma += bonus;

            System.out.println("Carta usada: " + cartaBonus.getNome());
            System.out.println("Bônus aplicado: +" + bonus);
            System.out.println("Total após bônus: " + soma);

            // 3. Aplicar multiplicador (2x rodada)
            soma = soma * 2;

            System.out.println("Carta usada: " + cartaMultiplicador.getNome());
            System.out.println("Total final: " + soma);

            // Resultado da rodada
            if (soma >= metas[rodada]) {
                System.out.println("Resultado: VITÓRIA!");
            } else {
                System.out.println("Resultado: DERROTA!");
            }
        }

        System.out.println("\n Fim do jogo!");
    }
}
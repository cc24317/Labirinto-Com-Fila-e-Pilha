public class Main {
    public static void main(String[] args) {
        try {
            // Caminho do arquivo de teste (deixe apenas o nome se estiver na mesma pasta)
            String caminhoArquivo = "labirinto1.txt";

            // Criar o objeto Labirinto
            Labirinto labirinto = new Labirinto(caminhoArquivo);

            // Tentar percorrer o labirinto usando busca em profundidade (DFS)
            boolean encontrou = labirinto.percorrerLabirinto();

            // Exibir resultado
            if (labirinto.isSaidaEncontrada()) {
                // Exibir o caminho percorrido da entrada até a saída
                System.out.println("\nCaminho percorrido:");

                // Criar uma pilha auxiliar para inverter a ordem do caminho
                Pilha<Coordenadas> inverso = new Pilha<>(labirinto.getMatriz().getLinhas() * labirinto.getMatriz().getColunas());

                // Passar todos os elementos da pilha original para a pilha inversa
                Pilha<Coordenadas> caminho = labirinto.getCaminho();

                try {
                    while (!caminho.isVazia()) {
                        Coordenadas coord = caminho.recupereUmItem();
                        caminho.removaUmItem();
                        inverso.guardeUmItem(coord);
                    }

                    // Agora desempilha do inverso imprimindo na mesma linha
                    while (!inverso.isVazia()) {
                        Coordenadas coord = inverso.recupereUmItem();
                        inverso.removaUmItem();
                        System.out.print("(" + coord.getLinha() + "," + coord.getColuna() + ") ");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao imprimir o caminho: " + e.getMessage());
                }

                // Quebra linha no final
                System.out.println();
            } else {
                System.out.println("\nSaída não encontrada!");
            }

        } catch (Exception e) {
            // Trata qualquer erro que ocorra durante a execução
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            String caminhoArquivo = "C:\\Users\\NatalyJessica\\Documents\\labirinto1.txt";
            // Criar o objeto Labirinto
            Labirinto labirinto = new Labirinto(caminhoArquivo);
            boolean encontrou = labirinto.percorrerLabirinto();

            // Exibir resultado
            if (labirinto.isSaidaEncontrada()) {
                System.out.println("\nCaminho percorrido:");
                
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

                System.out.println();
            } else {
                System.out.println("\nSaída não encontrada!");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

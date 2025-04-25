public class Main {
    public static void main(String[] args) {
        try {
            // Caminho do arquivo de teste
            String caminhoArquivo = "C:\\Users\\NatalyJessica\\Documents\\labirinto1.txt";
            
            // Criar o objeto Labirinto
            Labirinto labirinto = new Labirinto(caminhoArquivo);
            
            // Exibir informações iniciais do labirinto
            System.out.println("Labirinto carregado com sucesso!");
            System.out.println("Dimensões: " + labirinto.getMatriz().getLinhas() + "x" + labirinto.getMatriz().getColunas());
            System.out.println("Posição da Entrada (E): " + labirinto.getMatriz().getEntrada());
            System.out.println("Posição da Saída (S): " + labirinto.getMatriz().getSaida());
            System.out.println("\nLabirinto inicial:");
            System.out.println(labirinto.getMatriz().toString());
            
            // Tentar percorrer o labirinto
            System.out.println("\nPercorrendo o labirinto...");
            boolean encontrou = labirinto.percorrerLabirinto();
            
            // Exibir resultado
            if (labirinto.isSaidaEncontrada()) {
                System.out.println("\nSaída encontrada!");
                System.out.println("\nLabirinto após percorrer (caminho marcado com '*'):");
                System.out.println(labirinto.getMatriz().toString());
                
                // Exibir o caminho encontrado
                System.out.println("\nCaminho percorrido:");
                Pilha<Coordenadas> caminho = labirinto.getCaminho();
                while (!caminho.isVazia()) {
                    try {
                        Coordenadas coord = caminho.recupereUmItem();
                        caminho.removaUmItem();
                        System.out.println("(" + coord.getLinha() + "," + coord.getColuna() + ")");
                    } catch (Exception e) {
                        break;
                    }
                }
            } else {
                System.out.println("\nSaída não encontrada!");
            }
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



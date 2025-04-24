public class Main {
    public static void main(String[] args) {
        try {
            // Caminho do arquivo de teste
            String caminhoArquivo = "C:\\Users\\NatalyJessica\\Documents\\labirinto3.txt";
            
            // Criar objeto para ler o arquivo
            LerArquivoTXT arquivo = new LerArquivoTXT(caminhoArquivo);
            
            // Criar a matriz do labirinto
            Matriz labirinto = new Matriz(arquivo);
            
            // Exibir informações do labirinto
            System.out.println("Labirinto carregado com sucesso!");
            System.out.println("Dimensões: " + labirinto.getLinhas() + "x" + labirinto.getColunas());
            System.out.println("Posição da Entrada (E): " + labirinto.getEntrada());
            System.out.println("Posição da Saída (S): " + labirinto.getSaida());
            System.out.println("\nLabirinto:");
            System.out.println(labirinto.toString());
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}



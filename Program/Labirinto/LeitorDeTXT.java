   /*
     * - # parede
     * - E entrada
     * - S saida
     * - " " caminho
     */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeTXT {
    private char[][] matriz;
    private int linhas;
    private int colunas;

    // Métodos getters para acessar as informações do labirinto
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public char[][] lerLabirinto(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));

        try {
            // Lê as dimensões do labirinto
            this.linhas = Integer.parseInt(reader.readLine().trim());
            this.colunas = Integer.parseInt(reader.readLine().trim());

            // Inicializa a matriz
            this.matriz = new char[linhas][colunas];

            // Lê o labirinto
            String linha;
            int i = 0;

            while ((linha = reader.readLine()) != null && i < linhas) {
                for (int j = 0; j < colunas && j < linha.length(); j++) {
                    matriz[i][j] = linha.charAt(j);
                }
                // Preencher o restante da linha com espaços se necessário
                for (int j = linha.length(); j < colunas; j++) {
                    matriz[i][j] = ' ';
                }
                i++;
            }

            return matriz;

        } finally {
            reader.close();
        }
    }

}

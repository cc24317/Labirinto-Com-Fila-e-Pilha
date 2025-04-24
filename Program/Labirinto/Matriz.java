import java.util.Objects;
import java.util.Arrays;

public class Matriz {
    private char[][] labirinto;
    private int linhas;
    private int colunas;
    private Coordenadas entrada;
    private Coordenadas saida;

    public Matriz(LerArquivoTXT arquivo) throws Exception {
        if (arquivo == null) {
            throw new Exception("Arquivo não pode ser nulo");
        }

        this.linhas = arquivo.getQtdLinhas();
        this.colunas = arquivo.getQtdColunas();
        this.labirinto = new char[linhas][colunas];

        String[] conteudo = arquivo.getConteudo();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                labirinto[i][j] = conteudo[i].charAt(j);
            }
        }

        // Verifica se o labirinto tem entrada e saída
        if (!verificarEntradaSaida()) {
            throw new Exception("Labirinto deve conter exatamente uma entrada (E) e uma saída (S)");
        }

        // Verifica se as dimensões são válidas
        if (!verificarDimensoes()) {
            throw new Exception("Dimensões do labirinto inválidas");
        }
    }

    private boolean verificarEntradaSaida() {
        int contadorEntrada = 0;
        int contadorSaida = 0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (labirinto[i][j] == 'E') {
                    contadorEntrada++;
                    entrada = new Coordenadas(i, j);
                } else if (labirinto[i][j] == 'S') {
                    contadorSaida++;
                    saida = new Coordenadas(i, j);
                }
            }
        }

        return contadorEntrada == 1 && contadorSaida == 1;
    }

    private boolean verificarDimensoes() {
        return linhas > 0 && colunas > 0 && linhas <= 100 && colunas <= 100;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public char getElemento(int linha, int coluna) throws Exception {
        if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
            throw new Exception("Posição inválida");
        }
        return labirinto[linha][coluna];
    }

    public void setElemento(int linha, int coluna, char valor) throws Exception {
        if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
            throw new Exception("Posição inválida");
        }
        labirinto[linha][coluna] = valor;
    }

    public Coordenadas getEntrada() {
        return entrada;
    }

    public Coordenadas getSaida() {
        return saida;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                sb.append(labirinto[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // construtor de copia
    public Matriz(Matriz outra) throws Exception {
        if (outra == null) {
            throw new Exception("Matriz original não pode ser nula");
        }

        this.linhas = outra.linhas;
        this.colunas = outra.colunas;
        this.labirinto = new char[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            System.arraycopy(outra.labirinto[i], 0, this.labirinto[i], 0, colunas);
        }

        this.entrada = new Coordenadas(outra.entrada.getLinha(), outra.entrada.getColuna());
        this.saida = new Coordenadas(outra.saida.getLinha(), outra.saida.getColuna());
    }

    @Override
    public Matriz clone() {
        try {
            return new Matriz(this);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao clonar a matriz", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Matriz other = (Matriz) obj;

        return linhas == other.linhas &&
                colunas == other.colunas &&
                Objects.equals(entrada, other.entrada) &&
                Objects.equals(saida, other.saida) &&
                Arrays.deepEquals(labirinto, other.labirinto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(linhas, colunas, entrada, saida);
        result = 31 * result + Arrays.deepHashCode(labirinto);
        return result;
    }
}

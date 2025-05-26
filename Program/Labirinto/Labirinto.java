import java.util.*;

public class Labirinto {
    private Matriz matriz;
    private Pilha<Coordenadas> caminho;
    private Pilha<Fila<Coordenadas>> possibilidades;
    private Coordenadas atual;
    private boolean saidaEncontrada;

    // Construtor: carrega o labirinto de um arquivo e inicializa as estruturas
    public Labirinto(String caminhoArquivo) throws Exception {
        LerArquivoTXT arquivo = new LerArquivoTXT(caminhoArquivo);
        this.matriz = new Matriz(arquivo);

        int capacidade = matriz.getLinhas() * matriz.getColunas();
        this.caminho = new Pilha<>(capacidade);
        this.possibilidades = new Pilha<>(capacidade);
        this.saidaEncontrada = false;

        encontrarEntrada(); 
    }

    // Método que localiza a entrada no labirinto
    private void encontrarEntrada() throws Exception {
        Coordenadas entrada = matriz.getEntrada();
        if (entrada == null)
            throw new Exception("ERRO: Entrada do labirinto não encontrada! Verifique se o arquivo contém o caractere 'E' para marcar a entrada.");

        this.atual = entrada;
    }

    // Método para percorrer o labirinto usando busca em profundidade (DFS)
    public boolean percorrerLabirinto() throws Exception {
        matriz.setElemento(atual.getLinha(), atual.getColuna(), '*'); // Marca posição atual
        caminho.guardeUmItem(atual); // Guarda a posição atual

        // Verifica se chegou à saída
        if (atual.equals(matriz.getSaida())) {
            this.saidaEncontrada = true;
            return true;
        }

        // Obtém as direções possíveis a partir da posição atual
        Fila<Coordenadas> direcoes = obterDirecoesPossiveis();

        // Se não há opções de movimento, tenta retroceder
        if (direcoes.isVazia()) {
            if (possibilidades.isVazia()) {
                // Verifica se a saída existe
                if (matriz.getSaida() == null) {
                    throw new Exception("ERRO: Saída do labirinto não encontrada! Verifique se o arquivo contém o caractere 'S' para marcar a saída.");
                }
                // Verifica se a saída está bloqueada
                if (matriz.getElemento(matriz.getSaida().getLinha(), matriz.getSaida().getColuna()) == '#') {
                    throw new Exception("ERRO: A saída do labirinto está bloqueada por paredes! Verifique se há um caminho livre até a saída.");
                }
                // Se chegou aqui, não há caminho possível
                throw new Exception("ERRO: Não foi possível encontrar um caminho até a saída! O labirinto está completamente bloqueado ou não há conexão entre a entrada e a saída.");
            }

            // Marca como espaço vazio onde estava
            Coordenadas ultima = caminho.recupereUmItem();
            matriz.setElemento(ultima.getLinha(), ultima.getColuna(), ' ');
            caminho.removaUmItem();

            // Atualiza a posição atual para o novo topo
            if (!caminho.isVazia())
                atual = caminho.recupereUmItem();

            direcoes = possibilidades.recupereUmItem();
            possibilidades.removaUmItem();
        } else {
            possibilidades.guardeUmItem(direcoes); // Salva possibilidades
        }

        // Tenta cada direção possível
        while (!direcoes.isVazia()) {
            Coordenadas proxima = direcoes.recupereUmItem();
            direcoes.removaUmItem();
            atual = proxima;

            if (percorrerLabirinto())
                return true; // Caminho encontrado
        }

        return false; // Caminho não encontrado
    }

    // Método que retorna as direções possíveis a partir da posição atual
    private Fila<Coordenadas> obterDirecoesPossiveis() throws Exception {
        Fila<Coordenadas> direcoes = new Fila<>(4); // Máximo 4 direções (cima, direita, baixo, esquerda)
        int linha = atual.getLinha();
        int coluna = atual.getColuna();

        int[][] movimentos = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] mov : movimentos) {
            int novaLinha = linha + mov[0];
            int novaColuna = coluna + mov[1];

            if (novaLinha >= 0 && novaLinha < matriz.getLinhas() &&
                novaColuna >= 0 && novaColuna < matriz.getColunas()) {

                char elemento = matriz.getElemento(novaLinha, novaColuna);
                if (elemento == ' ' || elemento == 'S')
                    direcoes.guardeUmItem(new Coordenadas(novaLinha, novaColuna));
            }
        }

        return direcoes;
    }

   

    // Getters
    public boolean isSaidaEncontrada() { return saidaEncontrada; }
    public Matriz getMatriz() { return matriz; }
    public Pilha<Coordenadas> getCaminho() { return caminho; }
    public Pilha<Fila<Coordenadas>> getPossibilidades() { return possibilidades; }
    public Coordenadas getAtual() { return atual; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < matriz.getLinhas(); i++) {
            for (int j = 0; j < matriz.getColunas(); j++) {
                try {
                    sb.append(matriz.getElemento(i, j));
                } catch (Exception e) {
                    sb.append('?'); // Se der erro, coloca um caractere qualquer
                }
            }
            sb.append("\n");
        }
    
        return sb.toString();
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Labirinto outro = (Labirinto) obj;
        return matriz.equals(outro.matriz) && caminho.equals(outro.caminho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matriz, caminho);
    }
}

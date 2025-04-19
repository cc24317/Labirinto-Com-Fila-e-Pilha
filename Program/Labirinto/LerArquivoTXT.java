import java.io.*;

public class LerArquivoTXT implements Cloneable {
    private int qtdLinhas;
    private int qtdColunas;
    private String[] conteudo;

    public LerArquivoTXT(String nomeArquivo) throws Exception {
        lerArquivo(nomeArquivo);
    }

    // Construtor de cópia
    public LerArquivoTXT(LerArquivoTXT modelo) throws Exception {
        if (modelo == null) {
            throw new Exception("Modelo ausente");
        }
        this.qtdLinhas = modelo.qtdLinhas;
        this.qtdColunas = modelo.qtdColunas;
        this.conteudo = modelo.conteudo.clone();
    }

    private void lerArquivo(String nomeArquivo) throws Exception {
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            // Lê a primeira linha (quantidade de linhas)
            qtdLinhas = Integer.parseInt(leitor.readLine());
            
            // Lê a segunda linha (quantidade de colunas)
            qtdColunas = Integer.parseInt(leitor.readLine());
            
            // Lê o conteúdo do labirinto
            conteudo = new String[qtdLinhas];
            for (int i = 0; i < qtdLinhas; i++) {
                String linha = leitor.readLine();
                if (linha == null || linha.length() != qtdColunas) {
                    throw new Exception("Formato do labirinto inválido");
                }
                conteudo[i] = linha;
            }
        } catch (IOException e) {
            throw new Exception("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public int getQtdColunas() {
        return qtdColunas;
    }

    public String[] getConteudo() {
        return conteudo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quantidade de Linhas: ").append(qtdLinhas).append("\n");
        sb.append("Quantidade de Colunas: ").append(qtdColunas).append("\n");
        sb.append("Conteúdo do Labirinto:\n");
        for (String linha : conteudo) {
            sb.append(linha).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        LerArquivoTXT outro = (LerArquivoTXT) obj;
        if (qtdLinhas != outro.qtdLinhas || qtdColunas != outro.qtdColunas) {
            return false;
        }
        
        for (int i = 0; i < qtdLinhas; i++) {
            if (!conteudo[i].equals(outro.conteudo[i])) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int result = qtdLinhas;
        result = 31 * result + qtdColunas;
        for (String linha : conteudo) {
            result = 31 * result + linha.hashCode();
        }
        return result;
    }

    @Override
    public Object clone() {
        LerArquivoTXT ret = null;
        try {
            ret = new LerArquivoTXT(this);
        } catch (Exception e) {
            // Não deve ocorrer pois this nunca é null
        }
        return ret;
    }
}
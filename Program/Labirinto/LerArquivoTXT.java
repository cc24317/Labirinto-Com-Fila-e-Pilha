import java.io.*;

public class LerArquivoTXT implements Cloneable
{
    //Inicialização dos atributos
    private BufferedReader lendoArq;
    private FileReader     arquivo;
    private String        nomeDoArquivo;
    private String[]      conteudo;
    private String        arquivoLido;
    private int           qtdLinhas;
    private int           qtdColunas;
    private boolean       linhasErradas;
    private boolean       colunasErradas;

    //Lendo o arquivo
    public LerArquivoTXT(String arq) throws Exception {
        this.nomeDoArquivo = arq;
        this.arquivo = new FileReader(arq);
        this.lendoArq = new BufferedReader(this.arquivo);

        lerArquivo();
    }

    private void lerArquivo() throws Exception {
        try {
            boolean primeiraLinha = true;
            boolean segundaLinha = true;

            String teste = "";

            String linhaAtual = lendoArq.readLine();


            if (linhaAtual == null)
                throw new Exception("O arquivo está vazio!");
            else {
                // A primeira linha do arquivo eh a quantidade de linha
                this.qtdLinhas = Integer.parseInt(linhaAtual);
                primeiraLinha = false;
            }

            String[] linhas = new String[qtdLinhas]; // Criando um vetor de linhas obtidas

            int tamanhoColuna = 0;

            while ((linhaAtual = lendoArq.readLine()) != null) {
                if (primeiraLinha || segundaLinha) {
                    if (primeiraLinha == false) {
                        this.qtdColunas = Integer.parseInt(linhaAtual);
                        segundaLinha = false;
                    }
                    primeiraLinha = false;
                } else {
                    if (linhaAtual.length() != qtdColunas)
                        linhasErradas = true;

                    linhas[tamanhoColuna] = linhaAtual;
                    tamanhoColuna++;

                    teste += linhaAtual;
                    teste += "\n";
                }
            }

            if (tamanhoColuna != this.qtdLinhas)
                colunasErradas = true;

            conteudo = linhas;
            arquivoLido = teste;
        } catch (IOException fnfe) {
            throw new Exception("Nao foi possivel abrir o arquivo");
        } finally {
            fecharArquivo();
        }
    }

    //Getters e Setters
    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public void setQtdLinhas(int qtdLinhas) {
        this.qtdLinhas = qtdLinhas;
    }

    public int getQtdColunas() {
        return qtdColunas;
    }

    public void setQtdColunas(int qtdColunas) {
        this.qtdColunas = qtdColunas;
    }

    public boolean getLinhasErradas() {
        return linhasErradas;
    }

    public void setLinhasErradas(boolean linhasErradas) {
        this.linhasErradas = linhasErradas;
    }

    public boolean getColunasErradas() {
        return colunasErradas;
    }

    public void setColunasErradas(boolean colunasErradas) {
        this.colunasErradas = colunasErradas;
    }

    public String arquivoLido() throws Exception{
        return arquivoLido;
    }

    public int pegaUmInt() throws Exception {
        int ret = 0;

        try{
            ret = Integer.parseInt(lendoArq.readLine()); // le a linha que tem o numLinhas, converte para int e guarda
        }
        catch (IOException erro){}
        catch (NumberFormatException erro){
            throw new Exception ("Int invalido!");
        }
        return ret;
    }

    public String lerLinha(int numLinhas) throws Exception{
        String linha = "";
        linha = conteudo[numLinhas-1];
        return linha;
    }

    public void fecharArquivo() throws Exception{
        lendoArq.close();
    }

    // Métodos Obrigatórios
    @Override
    public String toString (){
        return arquivoLido;
    }

    @Override
    public boolean equals (Object obj){
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

            LerArquivoTXT arq = (LerArquivoTXT) obj;

        if(this.nomeDoArquivo != arq.nomeDoArquivo)
            return false;

        if(this.lendoArq != arq.lendoArq)
            return false;

        if(this.arquivo != arq.arquivo)
            return false;

        return true;
    }

    @Override
    public int hashCode (){
        int ret = 1;

        ret = 7  * ret + this.lendoArq.hashCode();
        ret = 13 * ret + this.nomeDoArquivo.hashCode();
        ret = 11 * ret + this.arquivo.hashCode();

        if (ret < 0) ret =-ret;

        return ret;
    }

    public LerArquivoTXT (LerArquivoTXT modelo) throws Exception{
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.lendoArq          = modelo.lendoArq;
        this.nomeDoArquivo   = modelo.nomeDoArquivo;
        this.arquivo         = modelo.arquivo;
    }

    @Override
    public Object clone (){
        LerArquivoTXT ret = null;

        try{
            ret = new LerArquivoTXT(this);
        }
        catch (Exception erro){}

        return ret;
    }
}
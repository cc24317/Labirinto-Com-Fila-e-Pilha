import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeTXT {
    /*
     * - # parede
     * - E entrada
     * - S saida
     * - " " caminho
     */
    
    public List<String> lerArquivo(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
            return linhas;
        } finally {
            reader.close();
        }
    }
} 
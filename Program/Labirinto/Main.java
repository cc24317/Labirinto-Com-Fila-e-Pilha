import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		LeitorDeTXT leitor = new LeitorDeTXT();
		
		try {
			// Lê o arquivo
			List<String> linhas = leitor.lerArquivo("C:\\Users\\NatalyJessica\\Documents\\Labirinto1.txt");
			
			// Imprime as linhas lidas
			System.out.println("Conteúdo do arquivo:");
			for (String linha : linhas) {
				System.out.println(linha);
			}
			
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}
}

/*public class Main
{
	public static void main(String[] args)
	{
	    Pilha<String> pil = new Pilha<String> ();
	    
	    try
	    {
    	    pil.guardeUmItem("PYTHON");
    	    pil.guardeUmItem("C");
    	    pil.guardeUmItem("JAVA");
    	    pil.guardeUmItem("C++");
    	    pil.guardeUmItem("HTML");
    	    
    	    System.out.println (pil.recupereUmItem());
    	    pil.removaUmItem   ();
    	    System.out.println (pil.recupereUmItem());
    	    pil.removaUmItem   ();
    	    System.out.println (pil.recupereUmItem());
    	    pil.removaUmItem   ();
	    }
	    catch (Exception erro)
	    {
	        System.err.println (erro.getMessage());
	    }
	}
}*/

/* 
public class Main
{
	public static void main(String[] args)
	{
	    Fila<String> fil = new Fila<String> ();
	    
	    try
	    {
    	    fil.guardeUmItem("PYTHON");
    	    fil.guardeUmItem("C");
    	    fil.guardeUmItem("JAVA");
    	    fil.guardeUmItem("C++");
    	    fil.guardeUmItem("HTML");
    	    
    	    System.out.println (fil.recupereUmItem());
    	    fil.removaUmItem   ();
    	    System.out.println (fil.recupereUmItem());
    	    fil.removaUmItem   ();
    	    System.out.println (fil.recupereUmItem());
    	    fil.removaUmItem   ();
	    }
	    catch (Exception erro)
	    {
	        System.err.println (erro.getMessage());
	    }
	}
}
*/



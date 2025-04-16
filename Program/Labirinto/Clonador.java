import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Clonador <X>
{
    public X clone (X x) throws Exception
    {        
        if (x == null)
            throw new Exception("Objeto a ser clonado é nulo");
            
        // assim obtemos a classe da instancia no objeto
        // x, que, conforme sabemos, é a classe X, e a
        // armazenamos no objeto chamado classe
        Class<?> classe = x.getClass();
        
        // null porque chamaremos um metodo sem parametros
        Class<?>[] tpsParmsForms = null;
        
        // assim obtemos o metodo chamado clone, sem parametros,
        // da Class<?> armazenada no objeto classe (sabemos que
        // a classe é a classe X)
        Method metodo=null;
        try
        {
            metodo = classe.getMethod ("clone",tpsParmsForms);
        }
        catch (NoSuchMethodException erro)
        {
            throw new Exception("Objeto não possui método clone");
        }
        
        // null porque chamaremos um metodo sem parametros
        Object[] parmsReais = null;
                
        // assim chamamos o metodo armazenado no objeto chamado
        // metodo, fazendo com que o objeto chamado x seja para
        // ele o objeto chamante (o this) e fazendo com que
        // receba nao receba parametros reais (por isso o vetor
        // nulo chamado parmsReais é fornecido; o resultado da
        // chamada, que certamente é da classe X, mas que está
        // sendo retornado como Object, tem seu tipo mudado
        // para X e, então, é atribuido ao objeto chamado ret
        X ret=null;
        try
        {
            ret = (X)metodo.invoke(x,parmsReais);
        }
        catch (InvocationTargetException erro)
        {
            throw new Exception("Erro ao invocar método clone: " + erro.getMessage());
        }
        catch (IllegalAccessException erro)
        {
            throw new Exception("Acesso ilegal ao método clone: " + erro.getMessage());
        }

        // ret = (X)x.clone();
        
        return ret;
    }
}

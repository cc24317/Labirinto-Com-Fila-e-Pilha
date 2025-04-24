import java.util.Objects;

public class Coordenadas implements Cloneable {
    private int linha;
    private int coluna;

    public Coordenadas(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    // Método para mover a coordenada atual por um deslocamento (dx, dy)
    public Coordenadas moverPara(int dx, int dy) {
        // Retorna uma nova coordenada com o novo valor de x e y após o movimento
        return new Coordenadas(this.linha + dx, this.coluna + dy);
    }

    // Método que calcula a distância de Manhattan entre a coordenada atual e outra coordenada
    public int distanciaPara(Coordenadas outra) {
        // A distância de Manhattan é a soma das diferenças absolutas de x e y
        //Manhattan: a soma da distância horizontal + vertical (sem diagonais).
        return Math.abs(this.linha - outra.linha) + Math.abs(this.coluna - outra.coluna); // Distância de Manhattan
    }

    @Override
    public String toString() {
        return "[" + linha + "," + coluna + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenadas other = (Coordenadas) obj;
        return linha == other.linha && coluna == other.coluna;
    }

    @Override
    public int hashCode() {
        return 31 * linha + coluna;
    }

    public Coordenadas(Coordenadas modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo não pode ser nulo");

        this.linha = modelo.linha;
        this.coluna = modelo.coluna;
    }

    @Override
    public Coordenadas clone() {
        Coordenadas ret = null;
        try {
            return new Coordenadas(this);
        } catch (Exception e) {
        }
        return ret;
    }
}

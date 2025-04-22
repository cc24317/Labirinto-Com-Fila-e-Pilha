import java.util.Objects;

public class Coordenada implements Cloneable {
    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

     // Método para mover a coordenada atual por um deslocamento (dx, dy)
    public Coordenada moverPara(int dx, int dy) {
         // Retorna uma nova coordenada com o novo valor de x e y após o movimento
        return new Coordenada(this.x + dx, this.y + dy);
    }

    // Método que calcula a distância de Manhattan entre a coordenada atual e outra coordenada
    public int distanciaPara(Coordenada outra) {
        // A distância de Manhattan é a soma das diferenças absolutas de x e y
        //Manhattan: a soma da distância horizontal + vertical (sem diagonais).
        return Math.abs(this.x - outra.x) + Math.abs(this.y - outra.y); // Distância de Manhattan
    }

    @Override
    public String toString() {
        return "Coordenada: (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Coordenada outra = (Coordenada) obj;
        return this.x == outra.x && this.y == outra.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Coordenada(Coordenada modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo não pode ser nulo");

        this.x = modelo.x;
        this.y = modelo.y;
    }

    @Override
    public Coordenada clone() {
        Coordenada ret = null;
        try {
            return new Coordenada(this);
        } catch (Exception e) {
        }
        return ret;
    }
}

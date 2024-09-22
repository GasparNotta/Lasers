package laser;

public class Coordenada {
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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Método para verificar si la coordenada es impar/impar (para bloques)
    public boolean esBloque() {
        return x % 2 != 0 && y % 2 != 0;
    }

    // Método para verificar si la coordenada es par/impar o impar/par (para emisores/objetivos)
    public boolean esBorde() {
        return (x % 2 == 0 && y % 2 != 0) || (x % 2 != 0 && y % 2 == 0);
    }
}

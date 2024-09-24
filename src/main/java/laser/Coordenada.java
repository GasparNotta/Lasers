package laser;

public class Coordenada {
    private final int x;
    private final int y;
    private final boolean es_celda;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
        this.es_celda = (x % 2 == 1 && y % 2 == 1);
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public boolean esCelda() {
        return es_celda;
    }

    public void imprimir() {
        System.out.println("Coordenada: (" + x + ", " + y + ")");
    }
}

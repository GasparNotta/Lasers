package laser;

public class Coordenada {
    private final int x;
    private final int y;

    private boolean esCelda;
    private boolean esBorde;
    private boolean esLaser;
    private boolean esObjetivo;
    private Bloque bloque;
    
    // Constructor
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
        this.esCelda = false;
        this.esLaser = false;
        this.esObjetivo = false;
        this.esBorde = false;
        this.bloque = null;
    }

    public int obtenerX() {
        return x;
    }

    public int obtenerY() {
        return y;
    }

    public boolean esCelda() {
        return esCelda;
    }

    public boolean esLaser() {
        return esLaser;
    }

    public boolean esObjetivo() {
        return esObjetivo;
    }

    public boolean esBorde() {
        return esBorde;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void establecerCelda() {
        esCelda = true;
    }

    public void establecerBorde(boolean esBorde) {
        this.esBorde = esBorde; 
    }

    public void establecerLaser() {
        esLaser = true;
    }

    public void establecerObjetivo() {
        esObjetivo = true;
    }

    public void establecerBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public void eliminarBloque() {
        this.bloque = null;
    }

    // ---------------------------ELIMINAR---------------------------
    public String imprimir() {
        return "Coordenada: (" + x + ", " + y + ")";
    }
}
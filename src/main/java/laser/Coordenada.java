package laser;

public class Coordenada {
    private final int x;
    private final int y;
    private boolean es_celda;
    private boolean es_borde;
    private boolean es_laser;
    private boolean es_objetivo;
    private boolean pasa_laser;
    private Bloque bloque;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
        this.es_celda = false;
        this.es_laser = false;
        this.es_objetivo = false;
        this.pasa_laser = false;
        this.es_borde = false;
        this.bloque = null;
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

    public boolean esLaser() {
        return es_laser;
    }

    public boolean esObjetivo() {
        return es_objetivo;
    }

    public boolean esBorde() {
        return es_borde;
    }

    public boolean pasaLaser() {
        return pasa_laser;
    }

    public void establecerCelda() {
        es_celda = true;
    }
    
    public void establecerBorde() {
        es_borde = true;
    }

    public void establecerNoBorde() {
        es_borde = false;
    }   

    public void establecerLaser() {
        es_laser = true;
    }

    public void establecerObjetivo() {
        es_objetivo = true;
    }

    public void imprimir() {
        System.out.println("Coordenada: (" + x + ", " + y + ")");
    }

    public void establecerBloque(Bloque bloque) {
        this.bloque = bloque;
    }

 
    public Bloque getBloque() {
        return bloque;
    }
}




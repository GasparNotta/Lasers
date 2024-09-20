package laser;

public class Celda {
    private boolean tienePiso;
    private Bloque bloque;

    public Celda(boolean tienePiso) {
        this.tienePiso = tienePiso;
        this.bloque = null; // Celda vacía inicialmente
    }

    public boolean tienePiso() {
        return tienePiso;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public boolean estaVacia() {
        return tienePiso && bloque == null;
    }
}


package laser;

public abstract class Bloque {
    protected int x;  // Coordenada X
    protected int y;  // Coordenada Y

    public Bloque(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para recibir un rayo láser. Este será implementado por cada bloque concreto.
    public abstract void interactuarConLaser(Laser laser);

    // Getters y setters de las coordenadas
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


package laser;

public abstract class Bloque {
    private String tipo; // Ejemplo de atributo para identificar el tipo de bloque

    // Constructor
    public Bloque(String tipo) {
        this.tipo = tipo;
    }



    // Método para recibir un rayo láser. Este será implementado por cada bloque concreto.
    public abstract void interactuarConLaser(Laser laser);


    protected int x;  // Coordenada X
    protected int y;  // Coordenada Y

    public Bloque(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
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

    public String getTipoDeBloque() {
        return tipo;
    }
}


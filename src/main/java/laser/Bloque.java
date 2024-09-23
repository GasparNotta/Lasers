package laser;

public abstract class Bloque {
    private String tipo; // Atributo para identificar el tipo de bloque

    // Constructor
    public Bloque(String tipo) {
        this.tipo = tipo;
    }



    // Método para recibir un rayo láser. Debe ser implementado por las subclases 
    public abstract void interactuarConLaser(Laser laser);

    // Método para obtener el tipo de bloque
    public String tipoDeBloque() {
        return tipo;
    }






    // ----------------- No se si esto es necesario quiza seria mejor relacionarlo con la clase Coordenada-----------------


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

}


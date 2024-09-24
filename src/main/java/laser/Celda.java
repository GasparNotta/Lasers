package laser;

public class Celda {
    // Atributos
    private boolean tiene_piso;
    private Bloque bloque;
    private final Coordenada coordenada;

    // Constructor
    public Celda(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.tiene_piso = false;
        this.bloque = null;
        
    }

    // Métodos 

    public void establecerPiso(){
        this.tiene_piso = true;
    }

    // Método para colocar un bloque en la celda
    public void establecerBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public boolean tienePiso() {
        return tiene_piso;
    }
 
    // Método para obtener el bloque de la celda
    public Bloque getBloque() {
        return bloque;
    }

    public Coordenada getCoordenada() {
        return coordenada; // Método para acceder a la coordenada
    }
}


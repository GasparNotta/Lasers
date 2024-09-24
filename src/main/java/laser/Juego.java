package laser;

public class Juego {
    private final Tablero tablero;
    private final Laser laser;
    private final Objetivo objetivo;

    public Juego(Tablero tablero, Laser laser, Objetivo objetivo) {
        this.tablero = tablero;
        this.laser = laser;
        this.objetivo = objetivo;
    }

    public void jugar() {
        // Lógica del juego
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Laser getLaser() {
        return laser;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }
    
}

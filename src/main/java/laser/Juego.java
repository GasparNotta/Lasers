package laser;

public class Juego {
    private Tablero tablero;
    private Laser laser;
    private Objetivo objetivo;
    private Nivel nivel;
    private Coordenada coordenada_actual;

    public Juego(Nivel nivel) {
        this.nivel = nivel;
        this.tablero = new Tablero(nivel);
        this.laser = tablero.getLaser();
        this.objetivo = tablero.getObjetivo();
        this.coordenada_actual = laser.getCoordenadaInicial();
    }

    public void jugar() {
        while(coordenada_actual.esBorde() == false) {
            String direccion = laser.getDireccion();
            int cordenada_actual_x = coordenada_actual.obtenerX();
            int cordenada_actual_y = coordenada_actual.obtenerY();

                System.out.println("Ahora estoy en:");
                System.out.println(cordenada_actual_x + " " + cordenada_actual_y);
                switch (direccion) {
                    case "SW":
                        coordenada_actual = tablero.getCoordenada(cordenada_actual_x - 1, cordenada_actual_y + 1);
                        coordenada_actual.pasaLaser();  
                        break; 
                    case "SE":
                        coordenada_actual = tablero.getCoordenada(cordenada_actual_x + 1, cordenada_actual_y + 1);
                        coordenada_actual.pasaLaser();
                        break; 
                    case "NW":
                        coordenada_actual = tablero.getCoordenada(cordenada_actual_x - 1, cordenada_actual_y - 1);
                        coordenada_actual.pasaLaser();
                        break; 
                    case "NE":
                        coordenada_actual = tablero.getCoordenada(cordenada_actual_x + 1, cordenada_actual_y - 1);
                        coordenada_actual.pasaLaser();
                        break; 
                }
            
                
        }
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

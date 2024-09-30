package laser;

public class Juego {
    private Tablero tablero;
    private Laser laser;
    private Objetivo objetivo;
    private Coordenada coordenada_actual;

    public Juego(Nivel nivel) {
        this.tablero = new Tablero(nivel);
        this.laser = tablero.getLaser();
        this.objetivo = tablero.getObjetivo();
        this.coordenada_actual = laser.getCoordenadaInicial();
    }

    public void jugar() {
        actualizarTablero();
    }

    public void actualizarTablero() {
        while (!coordenada_actual.esBorde()) {  // Continúa hasta que el láser alcance el borde.
            String direccion = laser.getDireccion();
            int coordenada_actual_fila = coordenada_actual.obtenerX();
            int coordenada_actual_columna = coordenada_actual.obtenerY();

            System.out.println("Ahora estoy en:");
            System.out.println(coordenada_actual_fila + " " + coordenada_actual_columna);

            switch (direccion) {
                case "SW":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna - 1);
                    coordenada_actual.pasaLaser();
                    break;

                case "SE":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna + 1);
                    coordenada_actual.pasaLaser();
                    break;

                case "NW":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna - 1);
                    coordenada_actual.pasaLaser();
                    break;

                case "NE":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                        if (!coordenada.tieneBloque()) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
                    coordenada_actual.pasaLaser();
                    break;

                default:
                    System.out.println("Dirección desconocida.");
                    break;
            }
        }

        if (coordenada_actual.esBorde()) {
            System.out.println("El láser alcanzó el borde del tablero.");
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

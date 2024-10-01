package laser;

public class Juego {
    private Tablero tablero;
    private boolean nivel_completado;

    private Laser laser;
    private Objetivo objetivo;
    private Coordenada coordenada_actual;

    public Juego() {
        this.nivel_completado = false;

        this.tablero = null;
        this.laser = null;
        this.objetivo = null;
        coordenada_actual = null;
    }

    public void jugar() {
        laser = tablero.getLaser();
        coordenada_actual = laser.getCoordenadaInicial();
        objetivo = tablero.getObjetivo();
        System.out.println("------------------Nuevo Nivel------------------");
        actualizarTablero();
        System.out.println("El laser empieza en:" + laser.getCoordenadaInicial().obtenerX() + ' ' + laser.getCoordenadaInicial().obtenerY());
        System.out.println("El objetivo está en:" + objetivo.getCoordenada().obtenerX() + ' ' + objetivo.getCoordenada().obtenerY());

        if (objetivo.getAlcanzado()) {
            System.out.println("¡Nivel completado!");
        } else {
            System.out.println("¡Nivel no completado!");
        }
    }

    public void cargarNivel(String numero_nivel) {
        try {
            // Leer el nivel
            Nivel nivel = new Nivel("src/main/resources/levels/level" + numero_nivel + ".dat");
            nivel.leerArchivo();

            tablero = new Tablero(nivel);

        } catch (Exception e) {
            e.printStackTrace(); // Maneja la excepción según sea necesario
        }
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
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque());
                            coordenada.imprimir();
                            System.out.println();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna - 1);
                    
                    break;

                case "SE":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna + 1);
            
                    break;

                case "NW":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna - 1);
                
                    break;

                case "NE":
                    if (coordenada_actual_fila % 2 == 0) {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    } else {
                        Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                        if (coordenada.getBloque() == null) {
                            System.out.println("No hay bloque en las coordenadas: ");
                            coordenada.imprimir();
                            System.out.println();
                        } else {
                            System.out.println("Hay bloque en las coordenadas: ");
                            System.out.println(coordenada.getBloque().tipoDeBloque()); 
                            coordenada.imprimir();
                            System.out.println();
                        }
                    }
                    coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
                    
                    break;

                default:
                    System.out.println("Dirección desconocida.");
                    break;
            }
        }

        if (coordenada_actual.esObjetivo()) {
            objetivo.setAlcanzado();
        } else{
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

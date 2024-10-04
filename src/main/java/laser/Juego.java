package laser;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private boolean nivel_completado;

    private Laser laser;
    private Objetivo objetivo;
    private Coordenada coordenada_actual;
    private ArrayList<String> recorrido_laser;

    public Juego() {
        nivel_completado = false;
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

    public void jugar() {
        laser = tablero.getLaser();
        coordenada_actual = laser.getCoordenadaInicial();
        objetivo = tablero.getObjetivo();

        System.out.println("------------------Nuevo Nivel------------------");
        actualizarTablero();
        System.out.println("El laser empieza en:" + laser.getCoordenadaInicial().obtenerX() + ' ' + laser.getCoordenadaInicial().obtenerY());
        System.out.println("El objetivo está en:" + objetivo.getCoordenada().obtenerX() + ' ' + objetivo.getCoordenada().obtenerY());

        for (String recorrido : recorrido_laser) {
            System.out.println(recorrido);
        }

        if (objetivo.getAlcanzado()) {
            System.out.println("¡Nivel completado!");
        } else {
            System.out.println("¡Nivel no completado!");
        }
    }

    public void actualizarTablero() {
    objetivo.setAlcanzado(false);
    recorrido_laser = new ArrayList<String>();
    boolean detener_trazado = false;

    while (!detener_trazado) {
        String direccion = laser.getDireccion();
        int coordenada_actual_fila = coordenada_actual.obtenerX();
        int coordenada_actual_columna = coordenada_actual.obtenerY();
        String posicionImpacto = "ninguna";  // Inicializamos el impacto como 'ninguno'

        System.out.println("Ahora estoy en:");
        System.out.println(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);

        // Comprobar si alcanzó el objetivo
        if (coordenada_actual.esObjetivo()) {
            objetivo.setAlcanzado(true);
            detener_trazado = true;
            break;
        }

        // Comprobar si alcanzó el borde del tablero
        if (coordenada_actual.esBorde()) {
            System.out.println("El láser alcanzó el borde del tablero.");
            detener_trazado = true;
            break;
        }

        // Comprobar si el láser fue absorbido
        if (direccion.equals(" ")) {
            System.out.println("El láser fue absorbido.");
            detener_trazado = true;
            break;
        }
        
        
        switch (direccion) {
            case "SW":
                if (coordenada_actual_fila % 2 == 0) {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                    if (coordenada.getBloque() != null) {
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        posicionImpacto = "debajo";  // Impacta desde arriba
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                } else {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "costado";  // Impacta desde el lado
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                }
                recorrido_laser.add(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
                coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna - 1);
                break;

            case "SE":
                if (coordenada_actual_fila % 2 == 0) {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "debajo";  // Impacta desde arriba
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                } else {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "costado";  // Impacta desde el lado
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                }
                recorrido_laser.add(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
                coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna + 1);
                break;

            case "NW":
                if (coordenada_actual_fila % 2 == 0) {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "arriba";  // Impacta desde abajo
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                } else {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "costado";  // Impacta desde el lado
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                }
                recorrido_laser.add(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
                coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna - 1);
                break;

            case "NE":
                if (coordenada_actual_fila % 2 == 0) {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "arriba";  // Impacta desde abajo
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                } else {
                    Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                    if (coordenada.getBloque() != null) {
                        posicionImpacto = "costado";  // Impacta desde el lado
                        System.out.println("Bloque encontrado. " + posicionImpacto);
                        coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                        break;
                    }
                }
                recorrido_laser.add(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
                coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
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

    public ArrayList<String> getRecorridoLaser() {
        return recorrido_laser;
    }
}

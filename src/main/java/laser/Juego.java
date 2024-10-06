package laser;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private boolean nivel_completado;

    private ArrayList<Laser> lasers;
    private ArrayList<Objetivo> objetivos;

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
        lasers = tablero.getLasers();
        objetivos = tablero.getObjetivos();

        System.out.println("------------------Nuevo Nivel------------------");
        for (Laser laser : lasers){
            System.out.println("Laser en: " + laser.getCoordenadaInicial().obtenerX() + ' ' + laser.getCoordenadaInicial().obtenerY());
            
        }
        for (Objetivo objetivo : objetivos){
            System.out.println("El objetivo está en:" + objetivo.getCoordenada().obtenerX() + ' ' + objetivo.getCoordenada().obtenerY());
        }


        
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();



        
        

        /* 
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();

        System.out.println("------------------Cambiando bloque------------------");
        tablero.cambiarBloque(tablero.getCoordenada(5, 3), tablero.getCoordenada(5, 5));
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();

        System.out.println("------------------Cambiando bloque------------------");
        tablero.cambiarBloque(tablero.getCoordenada(1, 5), tablero.getCoordenada(7, 1));
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();

        System.out.println("------------------Cambiando bloque------------------");
        tablero.cambiarBloque(tablero.getCoordenada(11, 3), tablero.getCoordenada(11, 5));
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();

        System.out.println("------------------Cambiando bloque------------------");
        tablero.cambiarBloque(tablero.getCoordenada(5, 7), tablero.getCoordenada(7, 7));
        tablero.imprimirTablero();
        actualizarTrazado();
        verificarVictoria();
        */

        System.out.println("El nivel fue completado: " + nivel_completado);
    }

    public void actualizarTrazado() {
        for (Objetivo objetivo : objetivos) {
            objetivo.setAlcanzado(false);
        }
        for (Laser laser : lasers) {
            
        
            laser.setDireccion(laser.getDireccionInicial());
            
            laser.deleteRecorridoLaser();
            
            boolean detener_trazado = false;
            Coordenada coordenada_actual = laser.getCoordenadaInicial();

            while (!detener_trazado) {
                String direccion = laser.getDireccion();
                int coordenada_actual_fila = coordenada_actual.obtenerX();
                int coordenada_actual_columna = coordenada_actual.obtenerY();
                String posicionImpacto = "ninguna";  // Inicializamos el impacto como 'ninguno'

                System.out.println("Ahora estoy en:");
                System.out.println(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);

                // Comprobar si alcanzó el objetivo
                if (coordenada_actual.esObjetivo()) {
                    System.out.println("El láser alcanzó el objetivo.");
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
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
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
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna + 1);
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
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna - 1);
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
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
                        break;
                }
            }
        }
    }

    public void verificarVictoria(){
        for (Objetivo objetivo : objetivos){
            if (!objetivo.getAlcanzado()){
                nivel_completado = false;
                return;
            }
        }
        nivel_completado = true;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

}

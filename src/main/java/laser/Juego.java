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
        actualizarTrazado();
        verificarVictoria();
    }

    public void actualizarTrazado() {
        for (Objetivo objetivo : objetivos) {
            objetivo.setAlcanzado(false);
        }
        for (Laser laser : lasers) {
            
            laser.setDireccion(laser.getDireccionInicial());
            laser.deleteRecorridoLaser();
            laser.getCoordenadaInicial().establecerBorde(false);

            boolean detener_trazado = false;
            Coordenada coordenada_actual = laser.getCoordenadaInicial();
            String direccion_anterior = " ";
            
            while (!detener_trazado) {
                String direccion = laser.getDireccion();
                int coordenada_actual_fila = coordenada_actual.obtenerX();
                int coordenada_actual_columna = coordenada_actual.obtenerY();
                String posicionImpacto = "ninguna";  // Inicializamos el impacto como 'ninguno'


                // Comprobar si alcanzó el objetivo
                if (coordenada_actual.esObjetivo()) {
                    System.out.println("El láser alcanzó el objetivo.");
                    tablero.getObjetivo(coordenada_actual_fila, coordenada_actual_columna).setAlcanzado(true);
                    
                }

                // Comprobar si alcanzó el borde del tablero
                if (coordenada_actual.esBorde() ) {
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

                if (coordenada_actual == laser.getCoordenadaInicial()){
                    coordenada_actual.establecerBorde(true);
                }

                System.out.println("Coordenada actual: " + coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);
                
               
                switch (direccion) {
                    case "SW":
                        if (coordenada_actual_fila % 2 == 0) {
                            Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                            if (coordenada.getBloque() != null) {
                                posicionImpacto = "debajo";  // Impacta desde arriba
                                System.out.println("Bloque encontrado. " + posicionImpacto);
                                coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                                break;
                            }
                        } else {
                            Coordenada coordenada = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                            if (coordenada.getBloque() != null) {
                                posicionImpacto = "costado_izquierda";  // Impacta desde el lado
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
                                posicionImpacto = "costado_derecha";  // Impacta desde el lado
                                System.out.println("Bloque encontrado. " + posicionImpacto);
                                coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                                break;
                            }
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                        
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
                                posicionImpacto = "costado_izquierda";  // Impacta desde el lado
                                System.out.println("Bloque encontrado. " + posicionImpacto);
                                coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                                break;
                            }
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                       
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
                                posicionImpacto = "costado_derecha";  // Impacta desde el lado
                                System.out.println("Bloque encontrado. " + posicionImpacto);
                                coordenada.getBloque().interactuarConLaser(laser, posicionImpacto);
                                break;
                            }
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);                        
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
                        break;
                    
                    // Nuevas posibilidades para el bloqueCristal
                    case "N":
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 2, coordenada_actual_columna );
                        laser.setDireccion(direccion_anterior);
                        break;
                    case "S":
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 2, coordenada_actual_columna);
                        laser.setDireccion(direccion_anterior);
                        break;
                    case "W":
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 2);
                        laser.setDireccion(direccion_anterior);
                        break;
                    case "E":
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + direccion);  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 2);
                        laser.setDireccion(direccion_anterior);
                        break;
                    }

                    direccion_anterior = direccion;
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

    public void cambiarBloque(int fila1, int columna1, int fila2, int columna2) {
        Bloque bloque = tablero.getCoordenada(fila1, columna1).getBloque();
        if (bloque == null) {
            return;
        } else if (bloque.tipoDeBloque().equals("BloqueFijo")) {
            return;
        } else if(tablero.getCoordenada(fila2, columna2).getBloque() != null){
            return;
        }
        tablero.cambiarBloque(tablero.getCoordenada(fila1, columna1), tablero.getCoordenada(fila2, columna2));
    }

    public boolean nivelCompletado() {
        return nivel_completado;
    }
}

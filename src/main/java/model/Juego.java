package model;
import java.util.ArrayList;

public class Juego {
    private Tablero tablero;
    private boolean nivel_completado;

    private ArrayList<Laser> lasers_del_tablero;
    private ArrayList<Objetivo> objetivos;
    private ArrayList<Laser> lasers_totales = new ArrayList<Laser>();

    public Juego(String numero_nivel) {
        nivel_completado = false;
        cargarNivel(numero_nivel);
        lasers_del_tablero = tablero.getLasers();
        objetivos = tablero.getObjetivos();
    }

    public void cargarNivel(String numero_nivel) {
        try {
            Nivel nivel = new Nivel("src/main/resources/levels/level" + numero_nivel + ".dat");
            nivel.leerArchivo();
            tablero = new Tablero(nivel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        actualizarTrazado();
        verificarVictoria();
    }

    public void actualizarTrazado() {
        for (Objetivo objetivo : objetivos) { objetivo.setAlcanzado(false); }
        lasers_totales.clear();
        lasers_totales.addAll(lasers_del_tablero);

        for(int i = 0; i < lasers_totales.size(); i++) {
            Laser laser = lasers_totales.get(i);
            laser.setDireccion(laser.getDireccionInicial());
            laser.deleteRecorridoLaser();
            boolean detener_trazado = false;
            Coordenada coordenada_actual = laser.getCoordenadaInicial();
            TipoDireccion direccion_anterior = TipoDireccion.SIN_DIRECCION;
            boolean era_borde = false;
            int iteracciones = 0;
            
            if (laser.getCoordenadaInicial().esBorde()){
                era_borde = true;
                laser.getCoordenadaInicial().establecerBorde(false);
            }

            // Calcular el trazado de cada laser
            while (!detener_trazado) {
                TipoDireccion direccion = laser.getDireccion();
                int coordenada_actual_fila = coordenada_actual.obtenerX();
                int coordenada_actual_columna = coordenada_actual.obtenerY();
                TipoImpacto impacto = TipoImpacto.NINGUNO;  // Inicializamos el impacto como 'ninguno'
                Coordenada coordenada_siguiente = null;
                iteracciones++;
                
                // Comprobar si alcanzó el objetivo
                if (coordenada_actual.esObjetivo()) {tablero.getObjetivo(coordenada_actual_fila, coordenada_actual_columna).setAlcanzado(true);}
                // Comprobar si alcanzó el borde del tablero
                if (coordenada_actual.esBorde() ) {detener_trazado = true;break;}
                // Comprobar donde empieza el laser era borde
                if (era_borde){
                    coordenada_actual.establecerBorde(true);
                    era_borde = false;
                }
                
                // Calcular la siguiente coordenada
                switch (direccion) {
                    case SIN_DIRECCION:
                        detener_trazado = true;
                        break;
                    case SW:
                        // Calcular la siguiente coordenada
                        coordenada_siguiente = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna - 1);
                        // Comprobar si la fila es par
                        if (coordenada_actual_fila % 2 == 0) {
                            // Calcular la coordenada del posible bloque mas cercano
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                            // Establecer la posicion del impacto del laser con el bloque
                            impacto = TipoImpacto.DEBAJO;
                            // Comprobar si el laser impacta con un bloque y esta funcion maneje el impacto si lo hay
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        } else {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                            impacto = TipoImpacto.COSTADO_IZQUIERDA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        }
                        // En el caso que no hay bloque, sigue en la misma direccion y se agrega la coordenada al recorrido
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "SW");
                        coordenada_actual = coordenada_siguiente;
                        break;

                    case SE:
                        coordenada_siguiente = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna + 1);
                        if (coordenada_actual_fila % 2 == 0) {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila + 1, coordenada_actual_columna);
                            impacto = TipoImpacto.DEBAJO;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        } else {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                            impacto = TipoImpacto.COSTADO_DERECHA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "SE");                        
                        coordenada_actual = coordenada_siguiente;
                        break;

                    case NW:
                        coordenada_siguiente = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna - 1);
                        if (coordenada_actual_fila % 2 == 0) {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                            impacto = TipoImpacto.ARRIBA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }   
                        } else {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 1);
                            impacto = TipoImpacto.COSTADO_IZQUIERDA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }    
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "NW");                       
                        coordenada_actual = coordenada_siguiente;
                        break;

                    case NE:
                        coordenada_siguiente = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna + 1);
                        if (coordenada_actual_fila % 2 == 0) {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila - 1, coordenada_actual_columna);
                            impacto = TipoImpacto.ARRIBA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        } else {
                            Coordenada coordenada_con_piso = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 1);
                            impacto = TipoImpacto.COSTADO_DERECHA;
                            if(manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)){
                                break;
                            }
                        }
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "NE");                        
                        coordenada_actual = coordenada_siguiente;
                        break;
                    
                    // Posibilidades exclusivas para el bloqueCristal
                    case N:
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "N");  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila - 2, coordenada_actual_columna );
                        laser.setDireccion(direccion_anterior);
                        break;
                    case S:
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "S");  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila + 2, coordenada_actual_columna);
                        laser.setDireccion(direccion_anterior);
                        break;
                    case W:
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "W");  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna - 2);
                        laser.setDireccion(direccion_anterior);
                        break;
                    case E:
                        laser.agregarCoordenadaRecorrido(coordenada_actual_fila + " " + coordenada_actual_columna + " " + "E");  
                        coordenada_actual = tablero.getCoordenada(coordenada_actual_fila, coordenada_actual_columna + 2);
                        laser.setDireccion(direccion_anterior);
                        break;
                    }
                    direccion_anterior = direccion;
                     
            } 
        }
    }

    private void verificarVictoria(){
        nivel_completado = objetivos.stream().allMatch(Objetivo::isAlcanzado);
    }

    private boolean manejarImpactoEnBloque(Coordenada coordenada_con_piso, Coordenada coordenada_actual, Coordenada coordenada_siguiente, Laser laser, TipoDireccion direccion, TipoImpacto impacto, TipoDireccion direccion_anterior, int iteracciones){
        if (coordenada_con_piso.getBloque() != null) {
            if(iteracciones == 1){laser.setDireccion(TipoDireccion.SIN_DIRECCION);}

            if(coordenada_con_piso.getBloque().tipoDeBloque() == TipoBloque.VIDRIO){
                agregarLazerDifractado(coordenada_siguiente, direccion);
                laser.agregarCoordenadaRecorrido(coordenada_actual.obtenerX() + " " + coordenada_actual.obtenerY()  + " " + direccion);   
            }
            if(coordenada_con_piso.getBloque().tipoDeBloque() == TipoBloque.FIJO || coordenada_con_piso.getBloque().tipoDeBloque() == TipoBloque.MOVIL){
                coordenada_con_piso.getBloque().interactuarConLaser(laser);
            } else{
                coordenada_con_piso.getBloque().interactuarConLaser(laser, impacto);
            }
            return true;
        }
        return false;
    }

    private void agregarLazerDifractado(Coordenada coordenada, TipoDireccion direccion){
        if (coordenada.esBorde()) return;
        lasers_totales.add(new Laser(coordenada, direccion));
    }   

    public void cambiarBloque(int fila1, int columna1, int fila2, int columna2) {
        Bloque bloque = tablero.getCoordenada(fila1, columna1).getBloque();
        if (bloque == null || bloque.tipoDeBloque() == TipoBloque.FIJO || tablero.getCoordenada(fila2, columna2).getBloque() != null) {
            return;
        }
        tablero.cambiarBloque(tablero.getCoordenada(fila1, columna1), tablero.getCoordenada(fila2, columna2));
    }

    public boolean nivelCompletado() {
        return nivel_completado;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Laser> getLasers() {
        return lasers_totales;
    }
}

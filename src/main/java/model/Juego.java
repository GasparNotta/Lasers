package model;
import java.lang.reflect.AccessFlag;
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
        prepararLasers();

        for(int i = 0; i < lasers_totales.size(); i++) {
            Laser laser = lasers_totales.get(i);
            laser.setDireccion(laser.getDireccionInicial());
            laser.deleteRecorridoLaser();
            boolean detener_trazado = false;
            Coordenada coordenada_actual = laser.getCoordenadaInicial();
            TipoDireccion direccion_anterior = TipoDireccion.SIN_DIRECCION;
            boolean era_borde = false;
            int iteracciones = 0;
            // Verificar si el laser esta en un borde, para poder realizar el trazado y despues volver a establecerlo como borde
            if (laser.getCoordenadaInicial().esBorde()){era_borde = true; laser.getCoordenadaInicial().establecerBorde(false);}

            while (!detener_trazado) {
                TipoDireccion direccion = laser.getDireccion();
                TipoImpacto impacto = TipoImpacto.NINGUNO;  // Inicializamos el impacto como 'ninguno'
                Coordenada coordenada_siguiente = null;
                Coordenada coordenada_con_piso = null;
                iteracciones++;
                
                if(verificarObjetivosYBordes(laser, coordenada_actual)){detener_trazado = true; break;}
                if (era_borde){coordenada_actual.establecerBorde(true); era_borde = false;}
                if (direccion == TipoDireccion.SIN_DIRECCION) {detener_trazado = true; continue;}

                coordenada_siguiente = calcularSiguienteCoordenada(coordenada_actual, direccion);
                coordenada_con_piso = calcularBloqueMasCercano(coordenada_actual, direccion);
                impacto = calcularImpactoConBloque(coordenada_actual, direccion);

                
                if (manejarImpactoEnBloque(coordenada_con_piso, coordenada_actual, coordenada_siguiente, laser, direccion, impacto, direccion_anterior, iteracciones)) { continue;}
                
                actualiazarRecorrido(laser, coordenada_actual, direccion);
                direccion_anterior = direccion;   
                coordenada_actual = coordenada_siguiente;
            } 
        }
    }

    private void prepararLasers() {
        lasers_totales.clear();
        lasers_totales.addAll(lasers_del_tablero);
    }

    private boolean verificarObjetivosYBordes(Laser laser, Coordenada coordenadaActual) {
        if (coordenadaActual.esObjetivo()) {tablero.getObjetivo(coordenadaActual.obtenerX(), coordenadaActual.obtenerY()).setAlcanzado(true);}
        if (coordenadaActual.esBorde()) {return true;}
        return false;
    }

    private Coordenada calcularSiguienteCoordenada(Coordenada coordenadaActual, TipoDireccion direccion) {
        Coordenada coordenadaSiguiente = null;
        switch (direccion) {
            case SW:case SE:case NW:case NE: coordenadaSiguiente = manejarMovimientosDiagonales(coordenadaActual, direccion); break;
            case N:case S:case W:case E: coordenadaSiguiente = manejarMovimientosRectos(coordenadaActual, direccion); break;
            case SIN_DIRECCION: return null;
        }
        return coordenadaSiguiente;
    }

    private Coordenada manejarMovimientosDiagonales(Coordenada coordenadaActual, TipoDireccion direccion) {
        int fila = coordenadaActual.obtenerX();
        int columna = coordenadaActual.obtenerY();
        Coordenada coordenadaSiguiente = null;
        switch (direccion) {
            case SW: coordenadaSiguiente = tablero.getCoordenada(fila + 1, columna - 1); break;
            case SE: coordenadaSiguiente = tablero.getCoordenada(fila + 1, columna + 1); break;
            case NW: coordenadaSiguiente = tablero.getCoordenada(fila - 1, columna - 1); break;
            case NE: coordenadaSiguiente = tablero.getCoordenada(fila - 1, columna + 1); break;
        }
        return coordenadaSiguiente;
    }
    
    private Coordenada manejarMovimientosRectos(Coordenada coordenadaActual, TipoDireccion direccion) {
        Coordenada coordenadaSiguiente = null;
        switch (direccion) {
            case N: coordenadaSiguiente = tablero.getCoordenada(coordenadaActual.obtenerX() - 2, coordenadaActual.obtenerY()); break;
            case S: coordenadaSiguiente = tablero.getCoordenada(coordenadaActual.obtenerX() + 2, coordenadaActual.obtenerY()); break;
            case W: coordenadaSiguiente = tablero.getCoordenada(coordenadaActual.obtenerX(), coordenadaActual.obtenerY() - 2); break;
            case E: coordenadaSiguiente = tablero.getCoordenada(coordenadaActual.obtenerX(), coordenadaActual.obtenerY() + 2); break;
        }
        return coordenadaSiguiente;
    }

    private void actualiazarRecorrido(Laser laser, Coordenada coordenada_actual, TipoDireccion direccion){
        laser.agregarCoordenadaRecorrido(coordenada_actual.obtenerX() + " " + coordenada_actual.obtenerY()  + " " + direccion.toString());
    }

    private Coordenada calcularBloqueMasCercano(Coordenada coordenada_actual, TipoDireccion direccion){
        int fila = coordenada_actual.obtenerX();
        int columna = coordenada_actual.obtenerY();
        switch (direccion) {
            case NE:
                if (fila % 2 == 0) { fila--;}
                else{columna++;}
                break;
            case NW:
                if (fila % 2 == 0) { fila--;}
                else{columna--;}
                break;
            case SE:
                if (fila % 2 == 0) { fila++;}
                else{columna++;}
                break;
            case SW:
                if (fila % 2 == 0) { fila++;}
                else{columna--;}
                break;
        }
        Coordenada coordenada_con_piso = tablero.getCoordenada(fila, columna);
        return coordenada_con_piso;
    }

    private TipoImpacto calcularImpactoConBloque(Coordenada coordenada_actual, TipoDireccion direccion){
        int fila = coordenada_actual.obtenerX();
        TipoImpacto impacto = TipoImpacto.NINGUNO;
        switch (direccion) {
            case NE:
                if (fila % 2 == 0) {impacto=TipoImpacto.ARRIBA;}
                else{impacto=TipoImpacto.COSTADO_DERECHA;}
                break;
            case NW:
                if (fila % 2 == 0) {impacto=TipoImpacto.ARRIBA;}
                else{impacto=TipoImpacto.COSTADO_IZQUIERDA;}
                break;
            case SE:
                if (fila % 2 == 0) {impacto=TipoImpacto.DEBAJO;}
                else{impacto=TipoImpacto.COSTADO_DERECHA;}
                break;
            case SW:
                if (fila % 2 == 0) {impacto=TipoImpacto.DEBAJO;}
                else{impacto=TipoImpacto.COSTADO_IZQUIERDA;}
                break;
        }
        return impacto;
    }

    private boolean manejarImpactoEnBloque(Coordenada coordenada_con_piso, Coordenada coordenada_actual, Coordenada coordenada_siguiente, Laser laser, TipoDireccion direccion, TipoImpacto impacto, TipoDireccion direccion_anterior, int iteracciones){
        if (direccion == TipoDireccion.N || direccion == TipoDireccion.S || direccion == TipoDireccion.W || direccion == TipoDireccion.E) {
            laser.setDireccion(direccion_anterior);
        }

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

    private void verificarVictoria(){
        nivel_completado = objetivos.stream().allMatch(Objetivo::isAlcanzado);
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

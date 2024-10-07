package laser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private String nombre_archivo;
    private int filas;
    private int columnas;
    private List<String> configuracion_bloques;  // Guardará la primera parte del archivo (bloques)
    private List<String> configuracion_elementos; // Guardará la segunda parte (emisores y objetivos)

    //Constructor
    public Nivel(String nombreArchivo) {
        this.nombre_archivo = nombreArchivo;
        this.configuracion_bloques = new ArrayList<>();
        this.configuracion_elementos = new ArrayList<>();
    }

    // Método para leer el archivo y procesar filas y columnas
    public void leerArchivo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nombre_archivo));
        String linea;
        boolean leyendo_bloques = true;

        while ((linea = reader.readLine()) != null) {
            if (linea.trim().isEmpty()) { 
                leyendo_bloques = false; 
                continue;
            }
            if (leyendo_bloques) {
                configuracion_bloques.add(linea);
            } else {
                configuracion_elementos.add(linea);
            }
        }
        reader.close();
        
        // Procesar filas y columnas
        this.filas = configuracion_bloques.size(); 
        this.columnas = configuracion_bloques.get(0).length();

        // Ajustar la longitud de las líneas si alguna quedo de otro formato(se considera sin piso)
        for (int i = 0; i < configuracion_bloques.size(); i++) {
            String linea_actual = configuracion_bloques.get(i);
            if (linea_actual.length() != columnas) {
                while (linea_actual.length() < columnas) {
                    linea_actual += " ";  
                }
                configuracion_bloques.set(i, linea_actual);
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public List<String> getConfiguracionBloques() {
        return configuracion_bloques;
    }

    public List<String> getConfiguracionElementos() {
        return configuracion_elementos;
    }
}

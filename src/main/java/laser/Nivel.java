package laser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Nivel {
    private String nombreArchivo;
    private int filas;
    private int columnas;
    private List<String> configuracionBloques;  // Guardará la primera parte del archivo (bloques)
    private List<String> configuracionElementos; // Guardará la segunda parte (emisores y objetivos)

    public Nivel(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.configuracionBloques = new ArrayList<>();
        this.configuracionElementos = new ArrayList<>();
    }

    // Método para leer el archivo y procesar filas y columnas
    public void leerArchivo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        String linea;
        boolean leyendoBloques = true;  // Bandera para separar la primera y segunda sección

        while ((linea = reader.readLine()) != null) {
            if (linea.trim().isEmpty()) { 
                leyendoBloques = false;  // Llegamos a la línea en blanco, ahora leeremos emisores y objetivos
                continue;
            }
            if (leyendoBloques) {
                configuracionBloques.add(linea);
            } else {
                configuracionElementos.add(linea);
            }
        }
        reader.close();
        
        // Procesar filas y columnas
        this.filas = configuracionBloques.size();  // El número de filas es el número de líneas en la primera sección
        this.columnas = configuracionBloques.get(0).length();  // Las columnas se basan en la longitud de la primera línea
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public List<String> getConfiguracionBloques() {
        return configuracionBloques;
    }

    public List<String> getConfiguracionElementos() {
        return configuracionElementos;
    }
}

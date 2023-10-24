package pokemonadivinaquien;

import java.io.File;
import java.io.IOException;

public class CrearArchivo {
 {
        File archivo = new File("estadisticasdeljuego.txt");

        if (!archivo.exists()) {
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado: " + archivo.getName());
                } else {
                    System.out.println("No se pudo crear el archivo.");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al crear el archivo.");
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo ya existe.");
        }
    }
}


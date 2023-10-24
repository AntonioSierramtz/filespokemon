package pokemonadivinaquien;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivo {
    public void guardarEstadisticas(List<Jugador> jugadores) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("estadisticas.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(jugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Jugador> cargarEstadisticas() {
        List<Jugador> jugadores = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("estadisticas.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            jugadores = (List<Jugador>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}

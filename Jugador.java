package pokemonadivinaquien;

import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private int juegosJugados;
    private int juegosGanados;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.juegosJugados = 0;
        this.juegosGanados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJuegosJugados() {
        return juegosJugados;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void incrementarJuegosJugados() {
        juegosJugados++;
    }

    public void incrementarJuegosGanados() {
        juegosGanados++;
    }
}


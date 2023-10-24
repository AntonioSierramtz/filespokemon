package pokemonadivinaquien;

import java.util.List;
import java.util.Objects;

abstract class Pokemon {
    private String nombre;
    private String tipo;
    private List<String> ataques;

    public Pokemon(String nombre, String tipo, List<String> ataques) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataques = ataques;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getAtaques() {
        return ataques;
    }

    public void agregarAtaque(String ataque) {
        ataques.add(ataque);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pokemon other = (Pokemon) obj;
        return Objects.equals(nombre, other.nombre);
    }

}
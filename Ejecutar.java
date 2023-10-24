package pokemonadivinaquien;

import java.util.*;
import java.util.stream.Collectors;

public class Ejecutar {
	private GestorArchivo gestorArchivo;

    private Scanner scanner;
    private List<String> ataquesPosibles;
    private List<Jugador> jugadores;

    public Ejecutar(Scanner scanner) {
        this.scanner = scanner;
        this.ataquesPosibles = Arrays.asList("Hydro pump", "Solar Beam", "Eruption", "Flamethrower", "Aqua Jet", "Petal Dance", "Dragon Dance", "Razor Leaf", "Vine Whip", "Seed Bomb", "Giga Drain");
        this.jugadores = new ArrayList<>();
        this.gestorArchivo = new GestorArchivo();
    }
    public void iniciarPrograma() {
        System.out.println("Bienvenido al juego de Adivina el Pokémon.");
        System.out.println("1. Jugar");
        System.out.println("2. Cargar perfil");
        System.out.println("3. Salir");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                nuevoJuego();
                break;
            case 2:
                cargarPerfil();
                break;
            case 3:
                System.out.println("Saliendo del juego. Hasta pronto.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
        }
    }
    private void cargarPerfil() {
    	System.out.print("Ingresa el nombre del jugador cuyo perfil deseas cargar: ");
        String nombreJugador = scanner.next();  // Cambia de nextLine() a next()

        List<Jugador> jugadoresCargados = gestorArchivo.cargarEstadisticas();

        Jugador jugadorCargado = null;
        for (Jugador jugador : jugadoresCargados) {
            if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                jugadorCargado = jugador;
                break; 
            }
        }

        if (jugadorCargado != null) {
            System.out.println("¡Perfil de " + nombreJugador + " cargado!");
            continuarJuegoConPerfilCargado(jugadorCargado);
        } else {
            System.out.println("El perfil de " + nombreJugador + " no fue encontrado.");
        }
    }

    
    private Jugador obtenerJugadorPorNombre(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
    }
    private void continuarJuegoConPerfilCargado(Jugador jugador) {
        System.out.println("Continuando el juego con el perfil de " + jugador.getNombre());
        jugador.incrementarJuegosJugados();
        List<Pokemon> pokemon = new ArrayList<>();

        pokemon.add(new Planta("Bulbasaur", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Chikorita", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Treecko", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Rowlet", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Sprigatio", generarAtaquesAleatorios()));

        pokemon.add(new Agua("Squirtle", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Totodile", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Mudkip", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Froakie", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Quaxly", generarAtaquesAleatorios()));

        pokemon.add(new Fuego("Charmander", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Cyndaquil", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Torchic", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Litten", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Fuecoco", generarAtaquesAleatorios()));

        System.out.println("Bienvenido al juego de Adivina el Pokémon.");
        System.out.println("Antonio Martinez Sierra 169599");
        System.out.println("La dificultad del juego es alta");
        System.out.println("Unicamente tendras 3 preguntas para adivinar");
        System.out.println("Estos son los Pokémon disponibles:");

        for (int i = 0; i < pokemon.size(); i++) {
            Pokemon p = pokemon.get(i);
            String ataques = String.join(", ", p.getAtaques());
            System.out.println(i + 1 + ". " + p.getNombre() + " : " + p.getTipo() + " : " + ataques);
        }

        Random random = new Random();
        int indicePokemonAdivinar = random.nextInt(pokemon.size());
        Pokemon pokemonAdivinar = pokemon.get(indicePokemonAdivinar);

        int preguntasRestantes = 3;
        List<Pokemon> opciones = new ArrayList<>(pokemon);

        while (preguntasRestantes > 0 && !opciones.isEmpty()) {
            System.out.println("\nEjemplo, '¿Es de tipo agua?:");
            String pregunta = scanner.nextLine().toLowerCase();

            boolean respuesta = responderPregunta(pregunta, opciones, pokemonAdivinar);

            if (respuesta) {
                System.out.println("\nSí");
            } else {
                System.out.println("\nNo");
            }

            preguntasRestantes--;
        }

        System.out.println("\n¡Es hora de adivinar el Pokémon!");
        System.out.print("Ingresa el número del Pokémon que quieres adivinar: ");
        int eleccionPokemon = Integer.parseInt(scanner.nextLine());

        if (eleccionPokemon >= 1 && eleccionPokemon <= opciones.size()) {
            Pokemon pokemonElegido = opciones.get(eleccionPokemon - 1);

            if (pokemonElegido.equals(pokemonAdivinar)) {
                System.out.println("\n¡Felicidades! Has adivinado el Pokémon. El Pokémon era: " + pokemonAdivinar.getNombre());
            } else {
                System.out.println("\nLo siento, no has adivinado el Pokémon. El Pokémon correcto era: " + pokemonAdivinar.getNombre());
            }
        } else {
            System.out.println("Número de Pokémon elegido no válido. Por favor, elige un número válido.");
        }
        System.out.println("Estadísticas de " + jugador.getNombre() + ":");
        System.out.println("Juegos jugados: " + jugador.getJuegosJugados());
        System.out.println("Juegos ganados: " + jugador.getJuegosGanados());
        gestorArchivo.guardarEstadisticas(jugadores);

    }
    private void nuevoJuego() {
    	scanner.nextLine();

        System.out.print("Ingresa tu nombre: ");
        String nombreJugador = scanner.nextLine();

        Jugador jugador = obtenerJugadorPorNombre(nombreJugador);

        if (jugador == null) {
            jugador = new Jugador(nombreJugador);
            jugadores.add(jugador);
        }

        jugador.incrementarJuegosJugados();
        
        List<Pokemon> pokemon = new ArrayList<>();

        pokemon.add(new Planta("Bulbasaur", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Chikorita", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Treecko", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Rowlet", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Sprigatio", generarAtaquesAleatorios()));

        pokemon.add(new Agua("Squirtle", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Totodile", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Mudkip", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Froakie", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Quaxly", generarAtaquesAleatorios()));

        pokemon.add(new Fuego("Charmander", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Cyndaquil", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Torchic", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Litten", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Fuecoco", generarAtaquesAleatorios()));

        System.out.println("Bienvenido al juego de Adivina el Pokémon.");
        System.out.println("Antonio Martinez Sierra 169599");
        System.out.println("La dificultad del juego es alta");
        System.out.println("Unicamente tendras 3 preguntas para adivinar");
        System.out.println("Estos son los Pokémon disponibles:");

        for (int i = 0; i < pokemon.size(); i++) {
            Pokemon p = pokemon.get(i);
            String ataques = String.join(", ", p.getAtaques());
            System.out.println(i + 1 + ". " + p.getNombre() + " : " + p.getTipo() + " : " + ataques);
        }

        Random random = new Random();
        int indicePokemonAdivinar = random.nextInt(pokemon.size());
        Pokemon pokemonAdivinar = pokemon.get(indicePokemonAdivinar);

        int preguntasRestantes = 3;
        List<Pokemon> opciones = new ArrayList<>(pokemon);

        while (preguntasRestantes > 0 && !opciones.isEmpty()) {
            System.out.println("\nEjemplo, '¿Es de tipo agua?:");
            String pregunta = scanner.nextLine().toLowerCase();

            boolean respuesta = responderPregunta(pregunta, opciones, pokemonAdivinar);

            if (respuesta) {
                System.out.println("\nSí");
            } else {
                System.out.println("\nNo");
            }

            preguntasRestantes--;
        }

        System.out.println("\n¡Es hora de adivinar el Pokémon!");
        System.out.print("Ingresa el número del Pokémon que quieres adivinar: ");
        int eleccionPokemon = Integer.parseInt(scanner.nextLine());

        if (eleccionPokemon >= 1 && eleccionPokemon <= opciones.size()) {
            Pokemon pokemonElegido = opciones.get(eleccionPokemon - 1);

            if (pokemonElegido.equals(pokemonAdivinar)) {
                System.out.println("\n¡Felicidades! Has adivinado el Pokémon. El Pokémon era: " + pokemonAdivinar.getNombre());
            } else {
                System.out.println("\nLo siento, no has adivinado el Pokémon. El Pokémon correcto era: " + pokemonAdivinar.getNombre());
            }
        } else {
            System.out.println("Número de Pokémon elegido no válido. Por favor, elige un número válido.");
        }
        System.out.println("Estadísticas de " + jugador.getNombre() + ":");
        System.out.println("Juegos jugados: " + jugador.getJuegosJugados());
        System.out.println("Juegos ganados: " + jugador.getJuegosGanados());
        gestorArchivo.guardarEstadisticas(jugadores);
    }
    
    public void continuarJuegoConPerfilCargado() {
    	
    	System.out.print("Ingresa tu nombre: ");
        String nombreJugador = scanner.nextLine();

        Jugador jugador = obtenerJugadorPorNombre(nombreJugador);

        if (jugador == null) {
            jugador = new Jugador(nombreJugador);
            jugadores.add(jugador);
        }

        jugador.incrementarJuegosJugados();
        List<Pokemon> pokemon = new ArrayList<>();

        pokemon.add(new Planta("Bulbasaur", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Chikorita", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Treecko", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Rowlet", generarAtaquesAleatorios()));
        pokemon.add(new Planta("Sprigatio", generarAtaquesAleatorios()));

        pokemon.add(new Agua("Squirtle", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Totodile", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Mudkip", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Froakie", generarAtaquesAleatorios()));
        pokemon.add(new Agua("Quaxly", generarAtaquesAleatorios()));

        pokemon.add(new Fuego("Charmander", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Cyndaquil", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Torchic", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Litten", generarAtaquesAleatorios()));
        pokemon.add(new Fuego("Fuecoco", generarAtaquesAleatorios()));

        System.out.println("Bienvenido al juego de Adivina el Pokémon.");
        System.out.println("Antonio Martinez Sierra 169599");
        System.out.println("La dificultad del juego es alta");
        System.out.println("Unicamente tendras 3 preguntas para adivinar");
        System.out.println("Estos son los Pokémon disponibles:");

        for (int i = 0; i < pokemon.size(); i++) {
            Pokemon p = pokemon.get(i);
            String ataques = String.join(", ", p.getAtaques());
            System.out.println(i + 1 + ". " + p.getNombre() + " : " + p.getTipo() + " : " + ataques);
        }

        Random random = new Random();
        int indicePokemonAdivinar = random.nextInt(pokemon.size());
        Pokemon pokemonAdivinar = pokemon.get(indicePokemonAdivinar);

        int preguntasRestantes = 3;
        List<Pokemon> opciones = new ArrayList<>(pokemon);

        while (preguntasRestantes > 0 && !opciones.isEmpty()) {
            System.out.println("\nEjemplo, '¿Es de tipo agua?:");
            String pregunta = scanner.nextLine().toLowerCase();

            boolean respuesta = responderPregunta(pregunta, opciones, pokemonAdivinar);

            if (respuesta) {
                System.out.println("\nSí");
            } else {
                System.out.println("\nNo");
            }

            preguntasRestantes--;
        }

        System.out.println("\n¡Es hora de adivinar el Pokémon!");
        System.out.print("Ingresa el número del Pokémon que quieres adivinar: ");
        int eleccionPokemon = Integer.parseInt(scanner.nextLine());

        if (eleccionPokemon >= 1 && eleccionPokemon <= opciones.size()) {
            Pokemon pokemonElegido = opciones.get(eleccionPokemon - 1);

            if (pokemonElegido.equals(pokemonAdivinar)) {
                System.out.println("\n¡Felicidades! Has adivinado el Pokémon. El Pokémon era: " + pokemonAdivinar.getNombre());
            } else {
                System.out.println("\nLo siento, no has adivinado el Pokémon. El Pokémon correcto era: " + pokemonAdivinar.getNombre());
            }
        } else {
            System.out.println("Número de Pokémon elegido no válido. Por favor, elige un número válido.");
        }
        System.out.println("Estadísticas de " + jugador.getNombre() + ":");
        System.out.println("Juegos jugados: " + jugador.getJuegosJugados());
        System.out.println("Juegos ganados: " + jugador.getJuegosGanados());
        gestorArchivo.guardarEstadisticas(jugadores);
    }

    public List<String> generarAtaquesAleatorios() {
        Random random = new Random();
        List<String> ataquesAleatorios = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int indiceAtaque = random.nextInt(ataquesPosibles.size());
            ataquesAleatorios.add(ataquesPosibles.get(indiceAtaque));
        }

        return ataquesAleatorios;
    }

    public boolean responderPregunta(String pregunta, List<Pokemon> opciones, Pokemon pokemonAdivinar) {
        if (pregunta.contains("tipo agua")) {
            return opciones.stream().anyMatch(p -> p instanceof Agua && p.equals(pokemonAdivinar));
        } else if (pregunta.contains("tipo fuego")) {
            return opciones.stream().anyMatch(p -> p instanceof Fuego && p.equals(pokemonAdivinar));
        } else if (pregunta.contains("tipo planta")) {
            return opciones.stream().anyMatch(p -> p instanceof Planta && p.equals(pokemonAdivinar));
        } else if (pregunta.contains("utiliza")) {
            String[] palabras = pregunta.split(" ");
            for (String palabra : palabras) {
                if (ataquesPosibles.contains(palabra)) {
                    return opciones.stream().anyMatch(p -> p.getAtaques().contains(palabra) && p.equals(pokemonAdivinar));
                }
            }
        }
        return false;
    }
}

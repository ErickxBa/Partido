import java.util.Random;

public class Partido {
    private Equipo[] equipos;
    private Jugador jugadorEnPosesión = null;
    private Equipo equipoEnPosesión = null;
    private Random rand = new Random();

    public Partido() {
        equipos = new Equipo[2];
        String[] nombresBrighton = new String[] {"Robert Sanchéz", "Adam Webster",
                "Lewis Dunk", "Pervis Estupiñan", "Tariq Lamptey", "Moisés Caicedo",
                "Alexis Mac Allister", "Jeremy Sarmiento", "Kaoru Mitoma", "Danny Welbeck", "Deniz Undav"};
        String[] nombresLiverpool = new String[] {"Alisson", "Virgil van Dijk",
                "Ibrahima Konaté", "Andrew Robertson", "Trent Alexander-Arnold", "Thiago", "Jordan Henderson",
        "Roberto Firmino","Mohamed Salah","Luis Díaz", "Cody Gakpo"};
        this.equipos[0] = new Equipo("Brigthon", nombresBrighton);
        this.equipos[1] = new Equipo("Liverpool", nombresLiverpool);
    }

    public Partido(Equipo equipo1, Equipo equipo2) {
        this.equipos[0] = equipo1;
        this.equipos[1] = equipo2;
    }

    public void iniciar() {
        int eleccion = rand.nextInt(2);
        int pases = 0;
        while (pases < 15) {
            if (pases == 0) {
                equipoEnPosesión = this.equipos[eleccion++];
                jugadorEnPosesión = pasarJugador(equipoEnPosesión, equipoEnPosesión.obtenerJugador(10));
            }
            else {
                jugadorEnPosesión = pasarJugador(equipoEnPosesión, jugadorEnPosesión);
                tirarAPortería(equipoEnPosesión, jugadorEnPosesión);
            }
            pases++;
        }
        presentarMarcador();
    }

    private void presentarMarcador() {
        System.out.println("\n" + equipos[0] + " " + equipos[0].getGoles() + " - " + equipos[1] + " " + equipos[1].getGoles());
    }

    private void tirarAPortería(Equipo equipo, Jugador jugador) {
        if (equipo.getPasesConsecutivos() < 5) {
            return;
        }
        else if (esPosibleGol()) {
            equipo.anotarGol();
            System.out.println("Gol de " + jugador);
        }
        else {
            System.out.println(jugador + " tira a la portería pero falla");
        }
        equipo.reiniciarPases();
        cambiarEquipoEnPosesión();
        jugadorEnPosesión = setJugadorEnPosesión(equipoEnPosesión);
    }

    private Jugador pasarJugador(Equipo equipo, Jugador jugador) {
        Jugador jugadorObjetivo = null;
        while (true) {
            jugadorObjetivo = equipo.obtenerJugador(rand.nextInt(1,11));
            if (jugadorObjetivo != jugador) {
                break;
            }
        }
        if (elBalonEsRobado()) {
            equipo.reiniciarPases();
            cambiarEquipoEnPosesión();
            Jugador jugadorRival = equipoEnPosesión.obtenerJugador(rand.nextInt(1,11));
            System.out.println(jugador + " le pasa el balón a " + jugadorObjetivo + ", pero es interceptado por " + jugadorRival);
            return jugadorRival;
        }
        equipo.aumentarPases();
        System.out.println(jugador + " le pasa el balón a " + jugadorObjetivo);
        return jugadorObjetivo;
    }

    private void cambiarEquipoEnPosesión() {
        for (Equipo equipo : equipos) {
            if (equipoEnPosesión != equipo) {
                equipoEnPosesión = equipo;
                return;
            }
        }
    }

    private Jugador setJugadorEnPosesión(Equipo equipo) {
        return equipo.obtenerJugador(rand.nextInt(1, 11));
    }

    private boolean elBalonEsRobado() {
        return rand.nextInt(0, 101) <= 20;
    }

    private boolean esPosibleGol() {
        return rand.nextInt(0, 101) <= 50;
    }
}

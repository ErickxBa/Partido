import java.util.Scanner;

public class Equipo {
    private String nombre;
    private Jugador[] jugadores;
    private int goles;
    private int pasesConsecutivos;

    public Equipo(String nombre, String[] nombresJugadores) {
        this.nombre = nombre;
        this.jugadores = new Jugador[11];
        this.goles = 0;
        this.pasesConsecutivos = 0;
        for (int i = 0; i < 11; i++) {
            this.jugadores[i] = new Jugador(nombresJugadores[i]);
        }
    }
    public Equipo() {
        Scanner input = new Scanner(System.in);
        this.jugadores = new Jugador[11];
        this.goles = 0;
        this.pasesConsecutivos = 0;
        System.out.print("Nombre del equipo: ");
        this.nombre = input.nextLine();
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < 11; i++) {
            System.out.print("Jugador " + (i + 1) + ": ");
            this.jugadores[i] = new Jugador(input.nextLine().trim());
            System.out.print("\033[H\033[2J");
        }
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public void presentarJugadores() {
        System.out.println("Jugadores del Equipo " + this.nombre + ":" );
        for (Jugador jugador : jugadores) {
            System.out.println("- " + jugador);
        }
    }

    public Jugador obtenerJugador(int posicion) {
        return this.jugadores[posicion];
    }

    public int getPasesConsecutivos() {
        return pasesConsecutivos;
    }

    public void reiniciarPases() {
        this.pasesConsecutivos = 0;
    }

    public void aumentarPases() {
        this.pasesConsecutivos++;
    }

    public int getGoles() {
        return goles;
    }

    public void anotarGol() {
        this.goles++;
    }
}

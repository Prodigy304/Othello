package Logica;

public class Turno {
    private int numeroTurno; // Variable para rastrear el número de turno

    public Turno() {
        numeroTurno = 1; // Comienza en el turno 1
    }

    public void cambiarTurno() {
        if (numeroTurno == 1) {
            numeroTurno = 2;
        } else {
            numeroTurno = 1;
        }
    }

    public int obtenerTurno() {
        return numeroTurno;
    }
    
}
package Puzzle24;


public class NodoPuzzle {
    String tablero;
    NodoPuzzle padre;
    String movimiento;
    int profundidad;
    int costo;

    public NodoPuzzle(String tablero, NodoPuzzle padre, String movimiento, int profundidad, int costo) {
        this.tablero = tablero;
        this.padre = padre;
        this.movimiento = movimiento;
        this.profundidad = profundidad;
        this.costo = costo;
    }
}
package Puzzle24;
public class Main {
    public static void main(String[] args) {
        String estadoInicial = "12345678 9ABCDEFGHIJKLMNO";
        String estadoMeta = "123456789ABCDEFGHIJKLMNO ";

        System.out.println("=== PUZZLE 5x5 ===");
        Puzzle24.imprimirTablero(estadoInicial);
        System.out.println("Meta:");
        Puzzle24.imprimirTablero(estadoMeta);

        System.out.println("\nESTADOS en FACIL");
        long startTime = System.nanoTime();
        Puzzle24.busquedaIDAStar(estadoInicial, estadoMeta, 1);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + executionTime / 1_000_000 + " ms");

        long startTime2 = System.nanoTime();
        Puzzle24.busquedaIDAStar(estadoInicial, estadoMeta, 2);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Tiempo de ejecución: " + executionTime2 / 1_000_000 + " ms");
    }
} 

package Puzzle8;
public class Main {

    public static void main(String[] args) {

        /*
          1234 5678
          1234567 8 
          
          7245 6831
          12345678 
        */
        String estadoInicial = "7245 6831";
        String estadoMeta = "12345678 ";

        System.out.println("=== PUZZLE ===");
        System.out.println("Estado inicial: "+estadoInicial);
        Puzzle8.imprimirTablero(estadoInicial);
        System.out.println("Estado meta: "+estadoMeta);
        Puzzle8.imprimirTablero(estadoMeta);

        System.out.println();
        long startTime = System.nanoTime();
        Puzzle8.busquedaPrimeroEnAnchura(estadoInicial, estadoMeta);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + executionTime / 1_000_000 + " ms");
        long startTime1 = System.nanoTime();
        Puzzle8.busquedaPrimeroEnProfundidad(estadoInicial, estadoMeta);
        long endTime1 = System.nanoTime();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("Tiempo de ejecución: " + executionTime1 / 1_000_000 + " ms");
        long startTime2 = System.nanoTime();
        Puzzle8.busquedaCostoUniforme(estadoInicial, estadoMeta);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Tiempo de ejecución: " + executionTime2 / 1_000_000 + " ms");
        long startTime3 = System.nanoTime();    
        Puzzle8.busquedaAEstrellaHeuristicaProia(estadoInicial, estadoMeta);
        long endTime3 = System.nanoTime();
        long executionTime3 = endTime3 - startTime3;
        System.out.println("Tiempo de ejecución: " + executionTime3 / 1_000_000 + " ms");
    }
}

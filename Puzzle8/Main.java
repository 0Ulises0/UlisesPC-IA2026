package Puzzle8;

public class Main {

    public static void main(String[] args) {

        String estadoInicial = "1234 5678";
        String estadoMeta = "1234567 8";

        String estadoInicialDificil = "627351 84";
        String estadoMetaDificil = "12345678 ";

        System.out.println("=== PUZZLE ===");
        System.out.println("Estado inicial: "+estadoInicial);
        Puzzle8.imprimirTablero(estadoInicial);
        System.out.println("Estado meta: "+estadoMeta);
        Puzzle8.imprimirTablero(estadoMeta);

        System.out.println();
        System.out.println("ESTADOS en FACIL");
        Puzzle8.busquedaPrimeroEnAnchura(estadoInicial, estadoMeta);
        Puzzle8.busquedaPrimeroEnProfundidad(estadoInicial, estadoMeta);
        Puzzle8.busquedaCostoUniforme(estadoInicial, estadoMeta);
        Puzzle8.busquedaAEstrellaHeuristicaProia(estadoInicial, estadoMeta);


        System.out.println();
        System.out.println("ESTADOS en DIFICIL");
        Puzzle8.busquedaPrimeroEnAnchura(estadoInicialDificil, estadoMetaDificil);
        Puzzle8.busquedaPrimeroEnProfundidad(estadoInicialDificil, estadoMetaDificil);
        Puzzle8.busquedaCostoUniforme(estadoInicialDificil, estadoMetaDificil);
        Puzzle8.busquedaAEstrellaHeuristicaProia(estadoInicialDificil, estadoMetaDificil);
    }
}

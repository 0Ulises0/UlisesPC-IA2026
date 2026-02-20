package Puzzle25;
public class Main {
    public static void main(String[] args) {
        String estadoInicial     = "12345678 9ABCDEFGHIJKLMNO";
        String estadoMeta        = "123456789ABCDEFGHIJKLMNO ";

        /* String estadoInicialDificil = "GBA12EF 39HIJKLMNOCD45678"; // 25 chars
        String estadoMetaDificil    = "123456789ABCDEFGHIJKLMNO "; // 25 chars */

        System.out.println("=== PUZZLE 5x5 ===");
        Puzzle25.imprimirTablero(estadoInicial);
        System.out.println("Meta:");
        Puzzle25.imprimirTablero(estadoMeta);

        System.out.println("\nESTADOS en FACIL");
        Puzzle25.busquedaIDAStar(estadoInicial, estadoMeta);

        //Demasiado tiempo de ejecucion
        /* System.out.println("\nESTADOS en DIFICL");
        Puzzle25.busquedaIDAStar(estadoInicialDificil, estadoMetaDificil); */

    }
} 

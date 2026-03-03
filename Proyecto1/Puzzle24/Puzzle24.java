package Puzzle24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Puzzle24 {
    public static List<NodoPuzzle> generarHijos(NodoPuzzle nodo) {
        List<NodoPuzzle> hijos = new ArrayList<>();
        String t = nodo.tablero;
        int p = t.indexOf(' ');

        int[][] adyacentes = {
            {1, 5},
            {0, 2, 6},
            {1, 3, 7},
            {2, 4, 8},
            {3, 9},
            {0, 6, 10},
            {1, 5, 7, 11},
            {2, 6, 8, 12},      
            {3, 7, 9, 13},   
            {4, 8, 14},   
            {5, 11, 15}, 
            {6, 10, 12, 16},
            {7, 11, 13, 17},
            {8, 12, 14, 18},
            {9, 13, 19},  
            {10, 16, 20},  
            {11, 15, 17, 21}, 
            {12, 16, 18, 22},
            {13, 17, 19, 23},
            {14, 18, 24}, 
            {15, 21}, 
            {16, 20, 22}, 
            {17, 21, 23},
            {18, 22, 24}, 
            {19, 23}  
        };

        for (int dest : adyacentes[p]) {
            hijos.add(crearHijo(nodo, t, p, dest));
        }
        return hijos;
    }
    private static NodoPuzzle crearHijo(NodoPuzzle padre, String t, int iOrigen, int iDestino) {
        char[] chars = t.toCharArray();
        char temp = chars[iOrigen];
        chars[iOrigen] = chars[iDestino];
        chars[iDestino] = temp;
        String nuevoTablero = new String(chars);
        return new NodoPuzzle(nuevoTablero, padre, iOrigen + "->" + iDestino, padre.profundidad + 1, padre.costo + 1);
    }

    //METODO PARA IMPRIMIR UN TABLERO
    public static void imprimirTablero(String t) {
        System.out.println("  +-----------+");
        for (int i = 0; i < 25; i++) {
            if (i % 5 == 0) System.out.print("  | ");
            char c = t.charAt(i);
            System.out.print((c == ' ' ? "_" : c) + " ");
            if (i % 5 == 4) System.out.println("|");
        }
        System.out.println("  +-----------+");
    }

    //METODO PARA MOSTRAR EL CAMINO A LA SOLUCION
    public static void mostrarSolucion(NodoPuzzle nodo) {
        List<NodoPuzzle> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(nodo);
            nodo = nodo.padre;
        }
        Collections.reverse(camino);
        System.out.println("  Pasos para resolver: " + (camino.size() - 1));
        for (int i = 0; i < camino.size(); i++) {
            NodoPuzzle n = camino.get(i);
            if (i == 0) {
                System.out.println("[INICIO]");
            } 
            else {
                System.out.println("\nPaso " + i + ": mover " + n.movimiento);
            }        
            imprimirTablero(n.tablero);
        }
    }


    //BUSQUEDA IDA*
    public static void busquedaIDAStar(String estadoInicial, String meta, int tipoHeuristica) { // declaracion del metodo
        System.out.println("============================");
        System.out.println("BUSQUEDA IDA*");
        System.out.println("============================");

        int[] nodosExplorados = {0}; // se usa un contador de nodos
        int limite = calcularHeuristica(estadoInicial, meta, tipoHeuristica); //limite inicial de ida, no puede empezar en 0, por lo menos en el valor heuristico del estado inicial
        NodoPuzzle raiz = new NodoPuzzle(estadoInicial, null, null, 0, 0); // se crea el nodo raiz

        while (true) {
            NodoPuzzle[] solucion = {null}; //contenedor para la solucion encontrada, se resetea en cada iteracion 
            int resultado = idaStarRecursivo(raiz, 0, limite, meta, nodosExplorados, solucion, tipoHeuristica);// se llama a ida star recursivo

            if (solucion[0] != null) {
                System.out.println("SOLUCION ENCONTRADA");
                System.out.println("Nodos explorados: " + nodosExplorados[0]);
                System.out.println("Movimientos: " + contarMovimientos(solucion[0]));
                return;
            }
            if (resultado == Integer.MAX_VALUE) {
                System.out.println("NO SE ENCONTRO SOLUCION");
                return;
            }
            System.out.println("Aumentando limite: " + limite + " -> " + resultado);
            limite = resultado;
        }
    }

    private static int idaStarRecursivo(NodoPuzzle nodo, int g, int limite, String meta, int[] nodosExplorados, NodoPuzzle[] solucion, int tipoHeuristica) {
        int f = g + calcularHeuristica(nodo.tablero, meta, tipoHeuristica);
        if (f > limite) return f;

        nodosExplorados[0]++;

        if (nodo.tablero.equals(meta)) {
            solucion[0] = nodo;
            return -1;
        }

        int minimo = Integer.MAX_VALUE;
        for (NodoPuzzle hijo : generarHijos(nodo)) {
            if (nodo.padre != null && hijo.tablero.equals(nodo.padre.tablero)) continue;
            int resultado = idaStarRecursivo(hijo, g + 1, limite, meta, nodosExplorados, solucion, tipoHeuristica);
            if (resultado == -1) return -1;
            if (resultado < minimo) minimo = resultado;
        }
        return minimo;
    }

    // 1 = solo Manhattan | 2 = Manhattan + Conflicto Lineal
    private static int calcularHeuristica(String tablero, String meta, int tipo) {
        if (tipo == 1) {
            return manhattanDistance(tablero, meta);
        } 

        return manhattanDistance(tablero, meta) + linearConflict(tablero, meta);
    }

    // Distancia Manhattan: suma de pasos horizontales + verticales
    // que necesita cada pieza para llegar a su posición meta
    private static int manhattanDistance(String tablero, String meta) {
        int distancia = 0;
        for (int i = 0; i < 25; i++) {
            char pieza = tablero.charAt(i);
            if (pieza == ' ') continue; // ignorar el espacio vacío

            int posicionMeta = meta.indexOf(pieza);

            int filaActual = i / 5;
            int colActual  = i % 5;
            int filaMeta   = posicionMeta / 5;
            int colMeta    = posicionMeta % 5;

            distancia += Math.abs(filaActual - filaMeta) + Math.abs(colActual - colMeta);
        }
        return distancia;
    }

    // Linear Conflict: detecta piezas en conflicto en filas y columnas
    // Agrega 2 por cada par en conflicto (porque una debe moverse fuera y volver)
    private static int linearConflict(String tablero, String meta) {
        int conflicto = 0;
        for (int fila = 0; fila < 5; fila++) {
            for (int col1 = 0; col1 < 4; col1++) {
                char pieza1 = tablero.charAt(fila * 5 + col1);
                if (pieza1 == ' ') continue;

                int metaPos1 = meta.indexOf(pieza1);
                if (metaPos1 / 5 != fila) continue;

                for (int col2 = col1 + 1; col2 < 5; col2++) {
                    char pieza2 = tablero.charAt(fila * 5 + col2);
                    if (pieza2 == ' ') continue;

                    int metaPos2 = meta.indexOf(pieza2);
                    if (metaPos2 / 5 != fila) continue;

                    if (metaPos1 % 5 > metaPos2 % 5) {
                        conflicto += 2;
                    }
                }
            }
        }
        for (int col = 0; col < 5; col++) {
            for (int fila1 = 0; fila1 < 4; fila1++) {
                char pieza1 = tablero.charAt(fila1 * 5 + col);
                if (pieza1 == ' ') continue;

                int metaPos1 = meta.indexOf(pieza1);
                if (metaPos1 % 5 != col) continue;

                for (int fila2 = fila1 + 1; fila2 < 5; fila2++) {
                    char pieza2 = tablero.charAt(fila2 * 5 + col);
                    if (pieza2 == ' ') continue;

                    int metaPos2 = meta.indexOf(pieza2);
                    if (metaPos2 % 5 != col) continue;

                    if (metaPos1 / 5 > metaPos2 / 5) {
                        conflicto += 2;
                    }
                }
            }
        }
        return conflicto;
    }


    public static int contarMovimientos(NodoPuzzle nodo) {
        int movimientos = 0;
        while (nodo.padre != null) {
            movimientos++;
            nodo = nodo.padre;
        }
        return movimientos;
    }
}
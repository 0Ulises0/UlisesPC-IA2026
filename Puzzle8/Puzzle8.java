package Puzzle8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Puzzle8 {
    public static List<NodoPuzzle> generarHijos(NodoPuzzle nodo) {
        List<NodoPuzzle> hijos = new ArrayList<>();
        String t = nodo.tablero;
        int posicion_vacia = t.indexOf(' ');

        switch (posicion_vacia) {
            case 0:
                hijos.add(crearHijo(nodo, t, 0, 1));
                hijos.add(crearHijo(nodo, t, 0, 3));
                break;
            case 1:
                hijos.add(crearHijo(nodo, t, 1, 0));
                hijos.add(crearHijo(nodo, t, 1, 2));
                hijos.add(crearHijo(nodo, t, 1, 4));
                break;
            case 2:
                hijos.add(crearHijo(nodo, t, 2, 1));
                hijos.add(crearHijo(nodo, t, 2, 5));
                break;
            case 3:
                hijos.add(crearHijo(nodo, t, 3, 0));
                hijos.add(crearHijo(nodo, t, 3, 4));
                hijos.add(crearHijo(nodo, t, 3, 6));
                break;
            case 4:
                hijos.add(crearHijo(nodo, t, 4, 1));
                hijos.add(crearHijo(nodo, t, 4, 3));
                hijos.add(crearHijo(nodo, t, 4, 5));
                hijos.add(crearHijo(nodo, t, 4, 7));
                break;
            case 5:
                hijos.add(crearHijo(nodo, t, 5, 2));
                hijos.add(crearHijo(nodo, t, 5, 4));
                hijos.add(crearHijo(nodo, t, 5, 8));
                break;
            case 6:
                hijos.add(crearHijo(nodo, t, 6, 3));
                hijos.add(crearHijo(nodo, t, 6, 7));
                break;
            case 7:
                hijos.add(crearHijo(nodo, t, 7, 6));
                hijos.add(crearHijo(nodo, t, 7, 4));
                hijos.add(crearHijo(nodo, t, 7, 8));
                break;
            case 8:
                hijos.add(crearHijo(nodo, t, 8, 5));
                hijos.add(crearHijo(nodo, t, 8, 7));
                break;
            default:
                System.out.println("error, no hay espacio vacio");
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
        System.out.println("  +-------+");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("  | ");
            } 
            char c = t.charAt(i);
            System.out.print((c == ' ' ? "_" : c) + " ");
            if (i % 3 == 2){
                System.out.println("|");
            }
        }
        System.out.println("  +-------+");
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

    public static int contarMovimientos(NodoPuzzle nodo) {
        int movimientos = 0;
        while (nodo.padre != null) {
            movimientos++;
            nodo = nodo.padre;
        }
        return movimientos;
    }

    //BUSQUEDA PRIMERO EN ANCHURA
    public static void busquedaPrimeroEnAnchura(String estadoInicial, String meta) {
        System.out.println("============================");
        System.out.println("BUSQUEDA PRIMERO EN ANCHURA");
        System.out.println("============================");

        Queue<NodoPuzzle> cola = new LinkedList<>();
        Set<String> visitados  = new HashSet<>();

        NodoPuzzle raiz = new NodoPuzzle(estadoInicial, null, null, 0, 0);
        cola.add(raiz);
        visitados.add(raiz.tablero);
        int nodosExplorados = 0;

        while (!cola.isEmpty()) {
            NodoPuzzle actual = cola.poll();
            nodosExplorados++;

            if (actual.tablero.equals(meta)) {
                System.out.println("SOLUCION ENCONTRADA");
                System.out.println("Nodos explorados: " + nodosExplorados);
                System.out.println("Movimientos: " + contarMovimientos(actual));
                return;
            }

            for (NodoPuzzle hijo : generarHijos(actual)) {
                if (!visitados.contains(hijo.tablero)) {
                    visitados.add(hijo.tablero);
                    cola.add(hijo);
                }
            }
        }
        System.out.println("NO SE ENCONTRO UNA SOLUCION.");
    }

    public static void busquedaPrimeroEnProfundidad(String estadoInicial, String meta) {
        System.out.println("================================");
        System.out.println("BUSQUEDA PRIMERO EN PROFUNDIDAD");
        System.out.println("================================");

        Stack<NodoPuzzle> pila = new Stack<>();
        Set<String> visitados  = new HashSet<>();

        NodoPuzzle raiz = new NodoPuzzle(estadoInicial, null, null, 0, 0);
        pila.push(raiz);
        int nodosExplorados = 0;

        while (!pila.isEmpty()) {
            NodoPuzzle actual = pila.pop();
            nodosExplorados++;

            if (visitados.contains(actual.tablero)) {
                continue;
            }
            visitados.add(actual.tablero);

            if (actual.tablero.equals(meta)) {
                System.out.println("SOLUCION ENCONTRADA");
                System.out.println("Nodos explorados: " + nodosExplorados);
                System.out.println("Movimientos: " + contarMovimientos(actual));
                return;
            }

            for (NodoPuzzle hijo : generarHijos(actual)) {
                if (!visitados.contains(hijo.tablero)) {
                    pila.push(hijo);
                }
            }
        }
        System.out.println("NO SE ENCONTRO UNA SOLUCION");
    }


    public static void busquedaCostoUniforme(String estadoInicial, String meta) {
        System.out.println("============================");
        System.out.println("BUSQUEDA COSTO UNIFORME");
        System.out.println("============================");

        PriorityQueue<NodoPuzzle> cola = new PriorityQueue<>(
            Comparator.comparingInt(n -> n.costo)
        );
        Set<String> visitados  = new HashSet<>();

        NodoPuzzle raiz = new NodoPuzzle(estadoInicial, null, null, 0, 0);
        cola.add(raiz);
        int nodosExplorados = 0;

        while (!cola.isEmpty()) {
            NodoPuzzle actual = cola.poll();
            if (visitados.contains(actual.tablero)) {
                continue;
            }
            visitados.add(actual.tablero);
            nodosExplorados++;

            if (actual.tablero.equals(meta)) {
                System.out.println("SOLUCION ENCONTRADA");
                System.out.println("Nodos explorados: " + nodosExplorados);
                System.out.println("Movimientos: " + contarMovimientos(actual));
                return;
            }

            for (NodoPuzzle hijo : generarHijos(actual)) {
                if (!visitados.contains(hijo.tablero)) {
                    cola.add(hijo);
                }
            }
        }
        System.out.println("NO SE ENCONTRO UNA SOLUCION.");
    }

    public static void busquedaAEstrellaHeuristicaProia(String estadoInicial, String meta) {
        System.out.println("============================");
        System.out.println("BUSQUEDA CON HEURISTICA");
        System.out.println("============================");

        PriorityQueue<NodoPuzzle> cola = new PriorityQueue<>(
            Comparator.comparingInt(n -> n.costo + heuristicaPropia(n.tablero, meta))
        );
        Set<String> visitados  = new HashSet<>();

        NodoPuzzle raiz = new NodoPuzzle(estadoInicial, null, null, 0, 0);
        cola.add(raiz);
        int nodosExplorados = 0;

        while (!cola.isEmpty()) {
            NodoPuzzle actual = cola.poll();
            if (visitados.contains(actual.tablero)) {
                continue;
            }
            visitados.add(actual.tablero);
            nodosExplorados++;

            if (actual.tablero.equals(meta)) {
                System.out.println("SOLUCION ENCONTRADA");
                System.out.println("Nodos explorados: " + nodosExplorados);
                System.out.println("Movimientos: " + contarMovimientos(actual));
                return;
            }

            for (NodoPuzzle hijo : generarHijos(actual)) {
                if (!visitados.contains(hijo.tablero)) {
                    cola.add(hijo);
                }
            }
        }
        System.out.println("NO SE ENCONTRO UNA SOLUCION.");
    }
    
    
    private static int heuristicaPropia(String tablero, String meta) {
        int[] posicionesClave = {0, 2, 4, 6, 8}; //Valores de las esquinas y la pieza central
        int costo = 0;
        for (int i : posicionesClave) {
            if (tablero.charAt(i) != meta.charAt(i)) {
                costo++;
            }
        }
        return costo;
    }
}
package ArbolBinarioBusqueda;
class ArbolBinarioBusqueda {
    Nodo raiz;
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }
    public void Insertar(String dato) {
        raiz = insertarRecursivo(raiz, dato);
    }
    private Nodo insertarRecursivo(Nodo nodoActual, String dato) {
        if (nodoActual == null) {
            return new Nodo(dato);
        }
        int comparacion = dato.compareToIgnoreCase(nodoActual.dato);
        if (comparacion < 0) {
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, dato);

        } else if (comparacion > 0) {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, dato);

        } else {
            System.out.println("ya existe el dato:" + dato);
        }
        return nodoActual;
    }
    public void ImprimirArbol() {
        if (raiz == null) {
            System.out.println("El arbol está vacio");
            return;
        }
        System.out.println("InOrden:");
        imprimirInOrden(raiz);
        System.out.println();
    }

    private void imprimirInOrden(Nodo nodoActual) {
        if (nodoActual == null) return;

        imprimirInOrden(nodoActual.izquierdo);
        System.out.println("  " + nodoActual.dato);
        imprimirInOrden(nodoActual.derecho);           
    }
}

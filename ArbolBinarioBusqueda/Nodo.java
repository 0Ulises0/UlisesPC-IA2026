package ArbolBinarioBusqueda;
class Nodo {
    String dato;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(String dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}
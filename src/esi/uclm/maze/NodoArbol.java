/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

/**
 *
 * @author pikac
 */
public class NodoArbol implements Comparable<NodoArbol> {
    
    private int id;
    private double coste;
    private Estado estado;
    private NodoArbol padre;
    private Accion accion;
    private int p;
    private double f;
    
    //ATRIBUTOS PARA PROBAR EN LA FRONTERA
    private static int contadorId = 0;
    private static int fila = 0;
    private static int columna = 0;
    
    //CONSTRUCTOR
    public NodoArbol (NodoArbol padre, Estado estado, double coste, Accion accion, int p, double f) {
        this.padre = padre;
        this.estado = estado;
        this.coste = coste;
        this.accion = accion;
        this.p = p;
        this.f = f;
    }
    
    //CONSTRUCTORES PARA REALIZAR LA PRUEBA EN LA FRONTERA
    public NodoArbol () {
        this.id = ++contadorId;
        
        this.padre = new NodoArbol(-1);
        
        //int fila, int columna, int num_vecinos, char[] id_movimientos, int[][] movimientos, int value, boolean[] vecinos
        char[] id_mov = new char[]{'N','E','S','O'}; int[][] movimientos = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        boolean[] vecinos = new boolean[]{false, false, false, false};
        
        if ((columna % 49) == 0) {
            columna = 0;
            this.estado = new Estado (fila++, columna, 4, id_mov, movimientos, 0, vecinos);
        } else {
            this.estado = new Estado (fila, columna++, 4, id_mov, movimientos, 0, vecinos);
        }
        
        
        this.coste = 1;
        this.accion = new Accion ('N',1,-1,0);
        this.p = 0;
        this.f = Math.floor(Math.random() * 5000000) + 1;  
    }
    
    public NodoArbol (int id) {
        this.id = id;
    }

    //GETTER Y SETTER DE LA CLASE NODO ARBOL
    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
    
    //MÉTODO COMPARE TO PARA LA PRIORITY QUEUE
    @Override
    public int compareTo (NodoArbol nodo) {
        int r = 0;
        
        if (nodo.getF() < getF()) {
            r = +1;
        } else if (nodo.getF() > getF()){
            r = -1;
        } else {
            if (nodo.getEstado().getFila() < getEstado().getFila()) {
                r = +1;
            } else if (nodo.getEstado().getFila() > getEstado().getFila()) {
                r = -1;
            } else {
                if (nodo.getEstado().getColumna() < getEstado().getColumna()) {
                    r = +1;
                } else if (nodo.getEstado().getColumna() > getEstado().getColumna()) {
                    r = -1;
                }
            }
        } 
        
        return r;
    }

    @Override
    public String toString() {
        // [<ID>][<COSTO>,<ID_ESTADO>,<ID_PADRE>,<ACCIÓN>,<PROFUNDIDAD>,<HEURISTICA>,<VALOR>]        
        return "[<" + id + ">] [<" + coste + ">, <(" + estado.getFila() + ", " + estado.getColumna() + ")>, <" + padre.getId() + ">, <" + accion.getMov() + ">, <" + p + ">, <" + f + ">]";
    }
}

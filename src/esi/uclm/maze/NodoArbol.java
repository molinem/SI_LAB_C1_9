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
    private Laberinto estado;
    private NodoArbol padre;
    private Accion accion;
    private int p;
    private double f;
    
    public NodoArbol (NodoArbol padre, Laberinto estado, double coste, Accion accion, int p, double f) {
        this.padre = padre;
        this.estado = estado;
        this.coste = coste;
        this.accion = accion;
        this.p = p;
        this.f = f;
    }

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

    public Laberinto getEstado() {
        return estado;
    }

    public void setEstado(Laberinto estado) {
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
    
    //REVISAR COMPARACION
    @Override
    public int compareTo (NodoArbol nodo) {
        int r;
        
        if (nodo.getF() < getF()) {
            r = +1;
        } else if (nodo.getF() > getF()){
            r = -1;
        } else {
            if (nodo.getId() < getId()) {
                r = +1;
            } else {
                r = -1;
            }
        }
         
        return r;
    }

    @Override
    public String toString() {
        return "NodoArbol{" + "padre=" + padre + ", id=" + id + ", estado=" + estado + ", coste=" + coste + ", accion=" + accion + ", p=" + p + ", f=" + f + '}';
    }
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.mazegen;

/**
 *
 * @author pikac
 */
public class ElementoDecorado<T> {
    private T elemento;
    private boolean visitado;
    private ElementoDecorado<T> antecesor;
    
    public ElementoDecorado (T element) {
        this.elemento = element;
        this.visitado = false;
        this.antecesor = null;
    }
    
    public T elemento() {
        return this.elemento;
    }
    
    public ElementoDecorado<T> getAntecesor () {
        return antecesor;
    }
    
    public void setAntecesor (ElementoDecorado<T> antecesor){
        this.antecesor = antecesor;
    }   
    
    public boolean visitado() {
        return this.visitado;
    }
    
    public void setVisitado (boolean t) {
        this.visitado = t;
    }
    
    public boolean equals (ElementoDecorado n) {
        return elemento.equals(n.elemento());
    }
    
    @Override
    public String toString() {
        return elemento.toString();
    }
    
}

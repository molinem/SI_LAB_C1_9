/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

import java.util.PriorityQueue;

/**
 *
 * @author pikac
 */
public class Frontera {
    
    private PriorityQueue<NodoArbol> colaNodos;
    
    public Frontera () {
        this.colaNodos = new PriorityQueue<>();
    }
    
    public void crearFronteraVacia() {
        colaNodos.clear();
    }
    
    public void insertar (NodoArbol n) {
        colaNodos.add(n);
    }
    
    public NodoArbol eliminar() {
        return colaNodos.remove();
    }
    
    public boolean estaVacia() {
        return colaNodos.isEmpty();
    }   
}

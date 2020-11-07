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
public class Sucesor {
    
    private Accion accion;
    private Laberinto estado;
    private double coste;
    
    public Sucesor (Accion accion, Laberinto estado, double coste) {
        this.accion = accion;
        this.estado = estado;
        this.coste = coste;
    }

    public Accion getAccion() {
        return accion;
    }

    public Laberinto getEstado() {
        return estado;
    }

    public double getCoste() {
        return coste;
    }   
}

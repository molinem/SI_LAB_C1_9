/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

public class Sucesor {
    
    private Accion accion;
    private Estado estado;
    private double coste;
    
    public Sucesor (Accion accion, Estado estado, double coste) {
        this.accion = accion;
        this.estado = estado;
        this.coste = coste;
    }

    public Accion getAccion() {
        return accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getCoste() {
        return coste;
    }

    @Override
    public String toString() {
        //"['N',(2,3),1]"
        return "['"+ getAccion().getMov() + "',(" + getEstado().getFila() + "," + getEstado().getColumna() + ")," + getCoste() + "]";
    }
}

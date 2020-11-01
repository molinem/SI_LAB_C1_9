/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.tree;

/**
 *
 * @author pikac
 */
public class Estado {
    
    /*** Identificador del Estado ***/
    private int fila;
    private int columna;
    
    /*** Atributo para el valor del Estado ***/
    private int value;
    
    /*** Vecinos del estado ***/
    private boolean[] vecinos;
    
    public Estado (int fila, int columna, int value, boolean[] vecinos) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
        this.vecinos = vecinos;
    }

    /*** GETTER Y SETTER DE LA CLASE ESTADO ***/
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getValue() {
        return value;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /*** MÉTODO EQUALS DE LA CLASE ESTADO ***/   
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean esIgual = true;
        
        if (obj == null) {
            esIgual = false;
        } else {
            if (getClass() != obj.getClass()) {
                esIgual = false;
            } else {
                final Estado other = (Estado) obj;
                if (this.fila != other.fila) {
                    esIgual = false;
                } else {
                    if (this.columna != other.columna) {
                        esIgual = false;
                    }
                }   
            }
        }
        
        return esIgual;
    }
    
    
    
}

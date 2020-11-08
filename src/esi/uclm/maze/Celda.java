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
public class Celda {
    
    /*** IDENTIFICADOR FORMADO POR FILA Y COLUMNA ***/
    private int fila;
    private int columna;
    
    /*** VALUE DE LA CELDA ***/
    private int value;
    
    /*** VECINOS DE LA CELDA ***/
    private boolean[] vecinos;
    
    
    //CONSTRUCTORES DE LA CLASE CELDA
    public Celda (int fila, int columna, int num_vecinos) {
        this.fila = fila;
        this.columna = columna;
        
        this.value = 0;
        this.vecinos = new boolean[num_vecinos];
        
        for (int i = 0; i < this.vecinos.length; i++) {
            this.vecinos[i] = false;
        }
    }
    
    public Celda (int fila, int columna, int num_vecinos, int value, boolean[] vecinos) {
        this(fila, columna, num_vecinos);
        this.value = 0;
        
        //Se copia el array de vecinos
        this.vecinos = new boolean[vecinos.length];
        System.arraycopy(vecinos, 0, this.vecinos, 0, vecinos.length);  
    }
    
    
    //GETTER Y SETTER DE LA CLASE CELDA
    public void setPared (int vecino, boolean pared) {
        this.vecinos[vecino] = pared;
    }  
    
    public void setValue (int value) {
        this.value = value;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getValue() {
        return value;
    }

    public boolean[] getVecinos() {
        return vecinos;
    }
    
    
    //MÉTODO EQUALS Y HASHCODE
    /**
     * Método para comparar dos objetos
     * @param obj Objeto objeto de una clase Java
     * @return Verdadero si son objetos de la clase Celda con el mismo id, falso en otro caso
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        Celda other = (Celda) obj;
        return ((this.fila == other.fila) && (this.columna == other.columna));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.fila;
        hash = 71 * hash + this.columna;
        return hash;
    }   
}

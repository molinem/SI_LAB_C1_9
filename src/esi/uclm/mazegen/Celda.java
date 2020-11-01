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
public class Celda {
    
    private static int contadorId = 0;
    
    private int id;
    
    private int fila;
    private int columna;
    
    private int value;
    private boolean[] vecinos;
    
    public Celda (int fila, int columna, int num_vecinos) {
        this.fila = fila;
        this.columna = columna;
        
        this.id = contadorId;
        contadorId++;
        
        this.value = 0;
        this.vecinos = new boolean[num_vecinos];
        
        for (int i = 0; i < this.vecinos.length; i++) {
            this.vecinos[i] = false;
        }
    }
    
    public void setPared (int vecino, boolean pared) {
        this.vecinos[vecino] = pared;
    }  
    
    public void setValue (int value) {
        this.value = value;
    }

    public int getId() {
        return id;
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
        return this.id == other.id;
    }
    
}

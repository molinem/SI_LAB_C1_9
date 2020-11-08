/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pikac
 */
public class Estado {
    
    private int fila;
    private int columna;
    private int value;
    private int num_vecinos;
    private char[] id_movimientos;
    private int[][] movimientos;
    private boolean[] vecinos;
    
    
    //CONSTRUCTOR
    public Estado (int fila, int columna, int num_vecinos, char[] id_movimientos, int[][] movimientos, int value, boolean[] vecinos) {
        this.fila = fila;
        this.columna = columna;
        this.value = value;
        this.num_vecinos = num_vecinos;
        
        this.id_movimientos = new char [id_movimientos.length];
        System.arraycopy(id_movimientos, 0, this.id_movimientos, 0, this.id_movimientos.length);
        
        this.movimientos = new int [movimientos.length][movimientos[0].length];
        for (int i = 0; i < this.movimientos.length; i++) {
            System.arraycopy(movimientos[i], 0, this.movimientos[i], 0, this.movimientos[i].length);
        }
        
        this.vecinos = new boolean[vecinos.length];
        System.arraycopy(vecinos, 0, this.vecinos, 0, this.vecinos.length);
    }
    
    //MÉTODOS NECESARIOS PARA REALIZAR EL PROBLEMA DE BUSQUEDA
    public List<Accion> getAcciones () {
        List<Accion> acciones = new ArrayList<>();
        
        for (int i = 0; i< this.id_movimientos.length; i++) {
            acciones.add(new Accion(this.id_movimientos[i], 1, this.movimientos[i][0], this.movimientos[i][1]));
        }
        
        return acciones;
    }
    
    public void move (int inc_x, int inc_y) {
        this.fila = this.fila + inc_x;
        this.columna = this.columna + inc_y;
    }
    
    public Estado getEstado (Accion accion) {
        Estado estado = (Estado) this.clone();
        estado.move(accion.getInc_x(), accion.getInc_y());
            
        return estado;
    }
    
    /**
     * Método para clonar el laberinto
     * @return Object con el laberinto clonado
     */
    @Override
    public Object clone() {
        Estado obj = null;
        
        try {
            obj = (Estado) super.clone();
            
            //Clonamos el array de celdas vecinas
            obj.vecinos = obj.vecinos.clone();

            //Clonamos los identificadores de los movimientos posibles
            obj.id_movimientos = obj.id_movimientos.clone();

            //Clonamos los posibles movimientos
            obj.movimientos = obj.movimientos.clone();
            for (int i = 0; i < obj.movimientos.length; i++) {
                obj.movimientos[i] = obj.movimientos[i].clone();
            }
            
        } catch (CloneNotSupportedException ex) {
            System.out.println("El laberinto no se ha podido duplicar");
        }
 
        return obj;
    }
    
    public boolean esObjetivo (int f, int c) {
        return ((fila == f) && (columna == c));
    }

    //GETTER Y SETTER DE LA CLASE ESTADO
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNum_vecinos() {
        return num_vecinos;
    }

    public void setNum_vecinos(int num_vecinos) {
        this.num_vecinos = num_vecinos;
    }

    public char[] getId_movimientos() {
        return id_movimientos;
    }

    public void setId_movimientos(char[] id_movimientos) {
        this.id_movimientos = id_movimientos;
    }

    public int[][] getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int[][] movimientos) {
        this.movimientos = movimientos;
    }

    public boolean[] getVecinos() {
        return vecinos;
    }

    public void setVecinos(boolean[] vecinos) {
        this.vecinos = vecinos;
    }
}

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
public class Accion {
    
    //ATRIBUTOS DE LA CLASE ACCION
    private char mov;
    private int costo_mov;
    private int inc_x;
    private int inc_y;
    
    //CONSTRUCTOR DE LA CLASE ACCION
    public Accion (char mov, int costo_mov, int inc_y, int inc_x) {
        this.mov = mov;
        this.costo_mov = costo_mov;
        this.inc_y = inc_y;
        this.inc_x = inc_x;
    }

    //GETTER Y SETTER DE LA CLASE ACCION
    public char getMov() {
        return mov;
    }

    public void setMov(char mov) {
        this.mov = mov;
    }

    public int getCosto_mov() {
        return costo_mov;
    }

    public void setCosto_mov(int costo_mov) {
        this.costo_mov = costo_mov;
    }

    public int getInc_x() {
        return inc_x;
    }

    public void setInc_x(int inc_x) {
        this.inc_x = inc_x;
    }

    public int getInc_y() {
        return inc_y;
    }

    public void setInc_y(int inc_y) {
        this.inc_y = inc_y;
    }
}

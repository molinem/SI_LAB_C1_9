/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.mazegen;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author pikac
 */
public class Laberinto{
    
    /*** Atributos de la clase ***/
    private int filas;
    private int columnas;
    
    private int num_vecinos;
    
    private int[][] movimientos;
    private char[] id_mov; 
    
    private Celda[][] cells;
    
    /*** Atributos auxiliares para generar el laberinto ***/
    private ElementoDecorado<Celda>[][] celdasElem;
    private ArrayList<ElementoDecorado<Celda>> celdasRestantes;
    private Stack<ElementoDecorado<Celda>> celdasPrevias;
    private ElementoDecorado<Celda> c0;
    
    public Laberinto (int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        
        this.num_vecinos = 4;
        
        this.movimientos = new int [4][2];
        this.movimientos[0][0] = -1; this.movimientos[0][1] = 0;
        this.movimientos[1][0] = 0; this.movimientos[1][1] = 1;
        this.movimientos[2][0] = 1; this.movimientos[2][1] = 0;
        this.movimientos[3][0] = 0; this.movimientos[3][1] = -1;
        
        this.id_mov = new char [4];
        this.id_mov[0] = 'N'; this.id_mov[1] = 'E'; this.id_mov[2] = 'S'; this.id_mov[3] = 'O';
        
        this.cells = new Celda [filas][columnas];
        for (int i=0; i < this.cells.length; i++) {
            for (int j=0; j < this.cells[0].length; j++) {
                this.cells[i][j] = new Celda(i, j, this.num_vecinos);
            }
        }
    }
    
    public void generarLaberinto () {
        ElementoDecorado<Celda> aux;
        
        /* Inicializamos a null la celda c0 y el Stack de celdas previas */
        this.c0 = null;
        this.celdasPrevias = new Stack();
        /* Metemos todas las celdas en el ArrayList auxiliar encapsuladas en la clase ElementoDecorado para saber cuáles son las restantes */
        this.celdasElem = new ElementoDecorado [this.filas][this.columnas];
        this.celdasRestantes = new ArrayList();
        for (int i=0; i < this.cells.length; i++) {
            for (int j=0; j < this.cells[0].length; j++) {
                aux = new ElementoDecorado(this.cells[i][j]);
                this.celdasElem[i][j] = aux;
                this.celdasRestantes.add(aux); 
            }
        }
        
        /* Casilla inicial para comenzar el laberinto */
        int longitudLista = celdasRestantes.size();
        int celdaAleatoria = (int) Math.floor(Math.random()*(0-(longitudLista))+(longitudLista));
        
        ElementoDecorado<Celda> inicio = celdasRestantes.remove(celdaAleatoria);
        inicio.setVisitado(true);
        
        //System.out.println(inicio.elemento().getId());
        
        /* Bucle para realizar todos los caminos hasta que no queden más */
        boolean finalizado = false;
        while (!finalizado) {
            if (excavarCaminoAleatorio()) {
                finalizado = true;
            }
        }
    }
    
    private boolean excavarCaminoAleatorio () {
        ElementoDecorado<Celda> c1 = null;
        
        /* Cogemos una celda aleatoria para comenzar (No debe estar en el laberinto) */
        if (c0 == null) {
            /* Longitud de la lista con las celdas restantes */
            int longitudLista, celdaAleatoria;
            
            do {
                /*** De entre las celdas restantes no visitadas cogemos una al azar ***/
                longitudLista = celdasRestantes.size();
                celdaAleatoria = (int) (Math.random()*((longitudLista-1)-(1))+0);
                
                if (celdasRestantes.isEmpty()) {
                    return true;
                } else {
                    c0 = celdasRestantes.remove(celdaAleatoria);
                }
            } while (c0.visitado());
            
            /* Marcamos la celda como visitada */
            celdasPrevias.push(c0);
            
            return false;
        }
        
        
        /*** Se selecciona una dirección valida al azar ***/
        int mov_y = -1; int mov_x = -1;
        boolean direccionValida = false;
        while (!direccionValida) {
            int aleat = (int) Math.floor(Math.random()*4);
            switch (aleat) {
                case 0:
                    if (celdasPrevias.peek().elemento().getFila()-1 >= 0) {
                        direccionValida = true;
                        mov_y = this.movimientos[0][0]; mov_x = this.movimientos[0][1];
                    }
                    
                    break;
                case 1:
                    if (celdasPrevias.peek().elemento().getColumna()+1 < this.columnas) {
                        direccionValida = true;
                        mov_y = this.movimientos[1][0]; mov_x = this.movimientos[1][1];
                    }
                    
                    break;
                case 2:
                    if (celdasPrevias.peek().elemento().getFila()+1 < this.filas) {
                        direccionValida = true;
                        mov_y = this.movimientos[2][0]; mov_x = this.movimientos[2][1];
                    }
                    
                    break;
                case 3:
                    if (celdasPrevias.peek().elemento().getColumna()-1 >= 0) {
                        direccionValida = true;
                        mov_y = this.movimientos[3][0]; mov_x = this.movimientos[3][1];
                    }
                    
                    break;
            }
        }
        
        /*** Comprobamos si la nueva celda ha sido visitada previamente ***/
        ElementoDecorado<Celda> aux = celdasPrevias.peek();
        int fila = aux.elemento().getFila() + mov_y; int columna = aux.elemento().getColumna() + mov_x;
        c1 = this.celdasElem[fila][columna];
        
        if (celdasPrevias.search(this.celdasElem[fila][columna]) != -1) {
            borrarCamino(celdasPrevias, this.celdasElem[fila][columna]);
        } else {
            c1.setAntecesor(c0);  
        }
        
        celdasPrevias.push(c1);
        
        /*** Comprobamos si la celda hacia donde nos dirigimos está visitada ***/
        if (c1.visitado()) {
            /*** Si está visitada hemos encontrado un camino que hay que añadir al laberinto ***/
            c1 = celdasPrevias.pop();
            while (c1.getAntecesor() != null) {
                /*** Excavar el camino ***/
                c0 = c1.getAntecesor();
                
                if (c0 != null) {
                    derribarPared (c0.elemento(), c1.elemento());
                    derribarPared (c1.elemento(), c0.elemento());
                }
                
                /*** Poner la celda a visitada y continuar volviendo hacia la casilla inicial ***/
                c1.setVisitado(true);
                c1.setAntecesor(null);
                
                /*** Traza para depurar ***/
                //System.out.printf("%s ", c1.elemento().getId());
                
                /*** Siguiente iteración ***/
                c1 = celdasPrevias.pop();
            }
            
            /*** OJO: El ultimo debe ponerse a visitado ***/
            c1.setVisitado(true);
            
            //System.out.printf("%s\n", c1.elemento().getId());
            c0 = null;
        } else {
            c0 = c1;
        }
        
        return false;
    }
    
    private void borrarCamino (Stack<ElementoDecorado<Celda>> celdasPrevias, ElementoDecorado<Celda> c2) {
        ElementoDecorado<Celda> c1 = celdasPrevias.pop();
        
        do {
            c1.setAntecesor(null);
        } while (!(c1 = celdasPrevias.pop()).equals(c2));
        
    }
    
    private void derribarPared (Celda c0, Celda c1) {
        int dif_y = c1.getFila() - c0.getFila(); int dif_x = c1.getColumna() - c0.getColumna();
        
        if (dif_y == -1 && dif_x == 0) {
            c0.setPared(0, true);
        } else if (dif_y == 0 && dif_x == 1) {
            c0.setPared(1, true);
        } else if (dif_y == 1 && dif_x == 0) {
            c0.setPared(2, true);
        } else if (dif_y == 0 && dif_x == -1) {
            c0.setPared(3, true);
        }   
    }
    
    public String comprobarLaberinto () {
        
        String mensaje="";
       
        for (int i=0; i<this.cells.length; i++){
            for (int j=0; j < this.cells[i].length; j++) {
                if (i-1 >= 0) {
                    if (cells[i][j].getVecinos()[0] != cells[i-1][j].getVecinos()[2]) {
                        cells[i][j].getVecinos()[0] = cells[i-1][j].getVecinos()[2];
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA NORTE - " + i + " " + j +"\n";
                    }
                } else {
                    if (cells[i][j].getVecinos()[0] != false) {
                        cells[i][j].getVecinos()[0] = false;
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA NORTE - " + i + " " + j +"\n";
                    }
                }
               
                if (j+1 < cells[i].length) {
                    if (cells[i][j].getVecinos()[1] != cells[i][j+1].getVecinos()[3]) {
                        cells[i][j].getVecinos()[1] = cells[i][j+1].getVecinos()[3];
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA ESTE - " + i + " " + j +"\n";
                    }
                } else {
                    if (cells[i][j].getVecinos()[1] != false) {
                        cells[i][j].getVecinos()[1] = false;
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA ESTE - " + i + " " + j +"\n";
                    }
                }
               
                if (i+1 < cells.length) {
                    if (cells[i][j].getVecinos()[2] != cells[i+1][j].getVecinos()[0]) {
                        cells[i][j].getVecinos()[2] = cells[i+1][j].getVecinos()[0];
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA SUR - " + i + " " + j +"\n";
                    }
                } else {
                    if (cells[i][j].getVecinos()[2] != false) {
                        cells[i][j].getVecinos()[2] = false;
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA SUR - " + i + " " + j +"\n";
                    }
                }
               
                if (j-1 >= 0) {
                    if (cells[i][j].getVecinos()[3] != cells[i][j-1].getVecinos()[1]) {
                        cells[i][j].getVecinos()[3] = cells[i][j-1].getVecinos()[1];
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA OESTE - " + i + " " + j +"\n";
                    }
                } else {
                    if (cells[i][j].getVecinos()[3] != false) {
                        cells[i][j].getVecinos()[3] = false;
                        mensaje = mensaje + "ERROR AL COMPARAR CON LA CASILLA OESTE - " + i + " " + j +"\n";
                    }
                }
            }
        }
       return mensaje;
    }
    
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getNum_vecinos() {
        return num_vecinos;
    }

    public int[][] getMovimientos() {
        return movimientos;
    }

    public char[] getId_mov() {
        return id_mov;
    }

    public Celda[][] getCells() {
        return cells;
    }
}
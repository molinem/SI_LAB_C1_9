package esi.uclm.maze;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************
 * 
 * Class Name: Estado
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class:  Representa el estado
 * 
 *****************************************************************************/
public class Estado implements Cloneable {
    
    //Atributos
    private int fila;
    private int columna;
    private int value;
    private int num_vecinos;
    private char[] id_movimientos;
    private int[][] movimientos;
    private boolean[] vecinos;
    
    
    /*****************************************************************************
    * 
    * Constructor Name: Estado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: crea un estado proporcionando los siguientes
    * parámetros: fila,columna,num_vecinos,id_movimientos, movientos,value, vecinos
    * 
    *****************************************************************************/
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
    
    //-------------Métodos necesarios para el problema de búsqueda ----------------
    
    /*****************************************************************************
    * 
    * Method Name: getAcciones
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Crea y añade una lista con las acciones.
    * Devuelve una lista con las acciones previamente añadidas
    * 
    *****************************************************************************/    
    public List<Accion> getAcciones () {
        List<Accion> acciones = new ArrayList<>();
        
        for (int i = 0; i< this.id_movimientos.length; i++) {
            if (vecinos[i] == true) {
                acciones.add(new Accion(this.id_movimientos[i], 1, this.movimientos[i][0], this.movimientos[i][1]));
            } 
        }
        
        return acciones;
    }

    /*****************************************************************************
    * 
    * Method Name: move
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Modifica la fila y la columna a partir de los 
    * parámetros indicados
    * 
    *****************************************************************************/     
    public void move (int inc_x, int inc_y) {
        this.fila = this.fila + inc_x;
        this.columna = this.columna + inc_y;
    }
    
    /*****************************************************************************
    * 
    * Method Name: getEstado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtiene y devuelve el estado de una accion
    * pasada por parámetros
    * 
    *****************************************************************************/     
    public Estado getEstado (Accion accion) {
        Estado estado = (Estado) this.clone();
        estado.move(accion.getInc_y(), accion.getInc_x());
            
        //Hay que añadir cosas
        return estado;
    }
    
    /*****************************************************************************
    * 
    * Method Name: clone
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Clona el laberinto, devuelve el objeto
    * con el laberinto clonado
    * 
    *****************************************************************************/     
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
            ex.printStackTrace();
        }
 
        return obj;
    }
    
    public int getHeuristica (int f, int c) {
        int dif_fila = fila - f;
        int dif_columna = columna - c;
        
        int heuristica = Math.abs(dif_fila) + Math.abs(dif_columna);
        
        return heuristica;
    }
    
    /*****************************************************************************
    * 
    * Method Name: esObjetivo
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: comprueba si es un objetivo, devuelve true en caso 
    * de que sea objetivo y false en caso contrario
    * 
    *****************************************************************************/     
    public boolean esObjetivo (int f, int c) {
        return ((fila == f) && (columna == c));
    }

    public String getID() {
        return "(" + this.getFila() + ", " + this.getColumna() + ")";
    }
    
    /*****************************************************************************
    * 
    * Method Name: getFila
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la Fila 
    * 
    *****************************************************************************/     
    public int getFila() {
        return fila;
    }

    /*****************************************************************************
    * 
    * Method Name: setFila
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece la fila
    * 
    *****************************************************************************/     
    public void setFila(int fila) {
        this.fila = fila;
    }

    /*****************************************************************************
    * 
    * Method Name: getColumna
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la columna
    * 
    *****************************************************************************/     
    public int getColumna() {
        return columna;
    }

    /*****************************************************************************
    * 
    * Method Name: setColumna
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece la columna
    * 
    *****************************************************************************/     
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /*****************************************************************************
    * 
    * Method Name: getValue
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el valor
    * 
    *****************************************************************************/     
    public int getValue() {
        return value;
    }

    /*****************************************************************************
    * 
    * Method Name: setValue
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el valor
    * 
    *****************************************************************************/     
    public void setValue(int value) {
        this.value = value;
    }

    /*****************************************************************************
    * 
    * Method Name: getNum_vecinos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el número de vecinos
    * 
    *****************************************************************************/     
    public int getNum_vecinos() {
        return num_vecinos;
    }

    /*****************************************************************************
    * 
    * Method Name: setNum_vecinos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el número de vecinos
    * 
    *****************************************************************************/     
    public void setNum_vecinos(int num_vecinos) {
        this.num_vecinos = num_vecinos;
    }

    /*****************************************************************************
    * 
    * Method Name: getId_movimientos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el array de caracteres con los
    * distintos movientos
    * 
    *****************************************************************************/    
    public char[] getId_movimientos() {
        return id_movimientos;
    }
    
    /*****************************************************************************
    * 
    * Method Name: setId_movimientos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el array de caracteres con los
    * distintos movientos
    * 
    *****************************************************************************/    
    public void setId_movimientos(char[] id_movimientos) {
        this.id_movimientos = id_movimientos;
    }

    /*****************************************************************************
    * 
    * Method Name: getMovimientos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el array de caracteres con los
    * distintos movientos
    * 
    *****************************************************************************/    
    public int[][] getMovimientos() {
        return movimientos;
    }

    /*****************************************************************************
    * 
    * Method Name: setMovimientos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Establece los movientos en la matriz
    * 
    *****************************************************************************/     
    public void setMovimientos(int[][] movimientos) {
        this.movimientos = movimientos;
    }

    /*****************************************************************************
    * 
    * Method Name: getVecinos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtiene los vecinos 
    * 
    *****************************************************************************/      
    public boolean[] getVecinos() {
        return vecinos;
    }

    /*****************************************************************************
    * 
    * Method Name: setVecinos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece los vecinos 
    * 
    *****************************************************************************/    
    public void setVecinos(boolean[] vecinos) {
        this.vecinos = vecinos;
    }
}

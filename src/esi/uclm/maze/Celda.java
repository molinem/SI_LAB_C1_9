package esi.uclm.maze;

/*****************************************************************************
* 
* Class Name: Celda
* Author/s Name: Antonio, Luis y Teresa
* Description of the class:  representa una celda del laberinto
* 
*****************************************************************************/
public class Celda {
    
    //Identificador formado por fila y columna
    private int fila;
    private int columna;
    
    //Value de la celda
    private int value;
    
    //Vecinos de la celda
    private boolean[] vecinos;
    
    
    /*****************************************************************************
    * 
    * Constructor Name: Celda
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: Construye una celda pasando mediante parámetros
    * la fila,columna y el número de vecinos
    * 
    *****************************************************************************/
    public Celda (int fila, int columna, int num_vecinos) {
        this.fila = fila;
        this.columna = columna;
        
        this.value = 0;
        this.vecinos = new boolean[num_vecinos];
        
        for (int i = 0; i < this.vecinos.length; i++) {
            this.vecinos[i] = false;
        }
    }
    
    /*****************************************************************************
    * 
    * Constructor Name: Celda
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: Construye una celda pasando mediante parámetros
    * la fila,columna, el número de vecinos, el valor, y el array de boolean vecinos
    * 
    *****************************************************************************/
    public Celda (int fila, int columna, int num_vecinos, int value, boolean[] vecinos) {
        this(fila, columna, num_vecinos);
        this.value = 0;
        
        //Se hace una copia el array de vecinos
        this.vecinos = new boolean[vecinos.length];
        System.arraycopy(vecinos, 0, this.vecinos, 0, vecinos.length);  
    }
    
    
    /*****************************************************************************
    * 
    * Method Name: setPared
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Establecemos una pared, indicando vecino y el estado
    * boolean de la pared
    * 
    *****************************************************************************/
    public void setPared (int vecino, boolean pared) {
        this.vecinos[vecino] = pared;
    }  
    
    /*****************************************************************************
    * 
    * Method Name: setValue
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Establecemos el valor de la celda
    * 
    *****************************************************************************/    
    public void setValue (int value) {
        this.value = value;
    }

    /*****************************************************************************
    * 
    * Method Name: getFila
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtenemos la fila
    * 
    *****************************************************************************/     
    public int getFila() {
        return fila;
    }

    /*****************************************************************************
    * 
    * Method Name: getColumna
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtenemos la columna
    * 
    *****************************************************************************/         
    public int getColumna() {
        return columna;
    }

    /*****************************************************************************
    * 
    * Method Name: getValue
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtenemos la el valor de la celda
    * 
    *****************************************************************************/    
    public int getValue() {
        return value;
    }

    /*****************************************************************************
    * 
    * Method Name: getVecinos
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Obtenemos los vecinos
    * 
    *****************************************************************************/        
    public boolean[] getVecinos() {
        return vecinos;
    }
    
       
    /*****************************************************************************
    * 
    * Method Name: equals
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Devuelve verdadero si son objetos de la clase Celda
    * con el mismo id, falso en otro caso
    * 
    *****************************************************************************/    
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

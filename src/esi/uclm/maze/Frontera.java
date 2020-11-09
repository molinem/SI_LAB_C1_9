package esi.uclm.maze;

import java.util.PriorityQueue;

/*****************************************************************************
 * 
 * Class Name: Frontera
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: representa una frontera usando para el control
 * de los nodos una PriorityQueue
 * 
 *****************************************************************************/

public class Frontera {
    
    private PriorityQueue<NodoArbol> colaNodos;
    
    /*****************************************************************************
    * 
    * Constructor Name: Frontera
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: encargado de crear una PriorityQueue donde 
    * para los nodos
    * 
    *****************************************************************************/    
    public Frontera () {
        this.colaNodos = new PriorityQueue<>();
    }
    
    /*****************************************************************************
    * 
    * Method Name: crearFronteraVacia
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: encargado de crear una frontera vacía usando
    * para ello el método clear que limpia la PriorityQueue
    * 
    *****************************************************************************/    
    public void crearFronteraVacia() {
        colaNodos.clear();
    }
    
    /*****************************************************************************
    * 
    * Method Name: Insertar
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Inserta en la cola un NodoArbol pasado
    * por parámetros
    * 
    *****************************************************************************/    
    public void insertar (NodoArbol n) {
        colaNodos.add(n);
    }
    
    /*****************************************************************************
    * 
    * Method Name: eliminar
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: elimina de la cola un NodoArbol pasado
    * por parámetros
    * 
    *****************************************************************************/       
    public NodoArbol eliminar() {
        return colaNodos.remove();
    }
    
    /*****************************************************************************
    * 
    * Method Name: estavacia
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Comprueba si la cola está vacía o no, devolviendo
    * true si está llena y false en caso contrario
    * 
    *****************************************************************************/       
    public boolean estaVacia() {
        return colaNodos.isEmpty();
    }   
}

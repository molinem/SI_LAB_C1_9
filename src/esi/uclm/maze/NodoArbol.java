package esi.uclm.maze;

/*****************************************************************************
 * 
 * Class Name: NodoArbol
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: representa el nodo del arbol
 * 
 *****************************************************************************/
public class NodoArbol implements Comparable<NodoArbol> {
    
    //Atributos
    private int id;
    private double coste;
    private Estado estado;
    private NodoArbol padre;
    private Accion accion;
    private int p;
    private double f;
    
    //Atributos para probar en la frontera
    private static int contadorId = 0;
    private static int fila = 0;
    private static int columna = 0;
    
    /*****************************************************************************
    * 
    * Constructor Name: NodoArbol
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: construye un arbol indicando el padre,
    * estado,coste,acción, p y f
    * 
    *****************************************************************************/
    public NodoArbol (NodoArbol padre, Estado estado, double coste, Accion accion, int p, double f) {
        this.padre = padre;
        this.estado = estado;
        this.coste = coste;
        this.accion = accion;
        this.p = p;
        this.f = f;
    }
    
    /*****************************************************************************
    * 
    * Constructor Name: NodoArbol
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: Constructor para realizar la prueba en la frontera
    * 
    *****************************************************************************/    
    public NodoArbol () {
        this.id = ++contadorId;
        
        this.padre = new NodoArbol(-1);
        
        //int fila, int columna, int num_vecinos, char[] id_movimientos, int[][] movimientos, int value, boolean[] vecinos
        char[] id_mov = new char[]{'N','E','S','O'}; int[][] movimientos = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        boolean[] vecinos = new boolean[]{false, false, false, false};
        
        if ((columna % 49) == 0) {
            columna = 0;
            this.estado = new Estado (fila++, columna, 4, id_mov, movimientos, 0, vecinos);
        } else {
            this.estado = new Estado (fila, columna++, 4, id_mov, movimientos, 0, vecinos);
        }
        
        
        this.coste = 1;
        this.accion = new Accion ('N',1,-1,0);
        this.p = 0;
        this.f = Math.floor(Math.random() * 5000000) + 1;  
    }
    
    /*****************************************************************************
    * 
    * Constructor Name: NodoArbol
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: Constructor para realizar la prueba en la frontera
    * 
    *****************************************************************************/     
    public NodoArbol (int id) {
        this.id = id;
    }

    /*****************************************************************************
    * 
    * Method Name: getPadre()
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el padre 
    * 
    *****************************************************************************/
    public NodoArbol getPadre() {
        return padre;
    }

    /*****************************************************************************
    * 
    * Method Name: 
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el padre
    * 
    *****************************************************************************/
    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

    /*****************************************************************************
    * 
    * Method Name: getId
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el id
    * 
    *****************************************************************************/    
    public int getID() {
        return id;
    }

    /*****************************************************************************
    * 
    * Method Name: setId
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Establece el id
    * 
    *****************************************************************************/

    public void setID(int id) {
        this.id = id;
    }


    /*****************************************************************************
    * 
    * Method Name: getEstado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el estado
    * 
    *****************************************************************************/    
    public Estado getEstado() {
        return estado;
    }

    
    /*****************************************************************************
    * 
    * Method Name: setEstado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Establece el estado
    * 
    *****************************************************************************/
    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    /*****************************************************************************
    * 
    * Method Name: getCoste
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el coste
    * 
    *****************************************************************************/    
    public double getCoste() {
        return coste;
    }

    
    /*****************************************************************************
    * 
    * Method Name: setCoste
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el coste
    * 
    *****************************************************************************/
    public void setCoste(double coste) {
        this.coste = coste;
    }


    /*****************************************************************************
    * 
    * Method Name: getAccion
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la acción
    * 
    *****************************************************************************/    
    public Accion getAccion() {
        return accion;
    }

    
    /*****************************************************************************
    * 
    * Method Name: setAccion
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece la accion
    * 
    *****************************************************************************/
    public void setAccion(Accion accion) {
        this.accion = accion;
    }


    /*****************************************************************************
    * 
    * Method Name: getF
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la F
    * 
    *****************************************************************************/    
    public double getF() {
        return f;
    }

    
    /*****************************************************************************
    * 
    * Method Name: setF
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece la F
    * 
    *****************************************************************************/
    public void setF(double f) {
        this.f = f;
    }

    
    /*****************************************************************************
    * 
    * Method Name: getP
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la P
    * 
    *****************************************************************************/
    public int getP() {
        return p;
    }

    /*****************************************************************************
    * 
    * Method Name: setP
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece la P
    * 
    *****************************************************************************/    
    public void setP(int p) {
        this.p = p;
    }

    /*****************************************************************************
    * 
    * Method Name: compareTo
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: compare To usado para la priority queue
    * 
    *****************************************************************************/    
    @Override
    public int compareTo (NodoArbol nodo) {
        int r = 0;
        
        if (nodo.getF() < getF()) {
            r = +1;
        } else if (nodo.getF() > getF()){
            r = -1;
        } else {
            if (nodo.getEstado().getFila() < getEstado().getFila()) {
                r = +1;
            } else if (nodo.getEstado().getFila() > getEstado().getFila()) {
                r = -1;
            } else {
                if (nodo.getEstado().getColumna() < getEstado().getColumna()) {
                    r = +1;
                } else if (nodo.getEstado().getColumna() > getEstado().getColumna()) {
                    r = -1;
                }
            }
        } 
        
        return r;
    }

    /*****************************************************************************
    * 
    * Method Name: ToString
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve en una cadena el id,costo,id_estado....
    * 
    *****************************************************************************/    
    @Override
    public String toString() {
        // [<ID>][<COSTO>,<ID_ESTADO>,<ID_PADRE>,<ACCIÓN>,<PROFUNDIDAD>,<HEURISTICA>,<VALOR>]        
        return "[<" + id + ">] [<" + coste + ">, <(" + estado.getFila() + ", " + estado.getColumna() + ")>, <" +
                padre.getID() + ">, <" + accion.getMov() + ">, <" + p + ">, <" + f + ">]";
    }
}

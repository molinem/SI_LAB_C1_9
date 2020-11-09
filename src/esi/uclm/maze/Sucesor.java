package esi.uclm.maze;

/*****************************************************************************
 * 
 * Class Name: Sucesor
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: formada por la tripleta
 * (acción, estado y costo del movimiento) siendo los movimientos
 * posibles los indicados en las especificaciones indicadas en el
 * campus virtual. El estado tras realizar la acción o movimiento
 * y el costo de la tarea, que normalmente será 1. 
 * 
 *****************************************************************************/
public class Sucesor {
    
    //Atributos
    private Accion accion;
    private Estado estado;
    private double coste;
    
    /*****************************************************************************
    * 
    * Constructor Name: Sucesor
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: constructor dada la acción,estado y coste
    * 
    *****************************************************************************/    
    public Sucesor (Accion accion, Estado estado, double coste) {
        this.accion = accion;
        this.estado = estado;
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
    * Method Name: toString
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve la cadena con los atributos de la acción
    * 
    *****************************************************************************/
    @Override
    public String toString() {
        //"['N',(2,3),1]"
        return "['"+ getAccion().getMov() + "',(" + getEstado().getFila() + "," + getEstado().getColumna() + ")," + getCoste() + "]";
    }
}

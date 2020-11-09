package esi.uclm.util;

/*****************************************************************************
 * 
 * Class Name: ElementoDecorado<T>
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: clase auxiliar 
 * 
 *****************************************************************************/
public class ElementoDecorado<T> {
    
    //Atributos
    private T elemento;
    private boolean visitado;
    private ElementoDecorado<T> antecesor;
    
    /*****************************************************************************
    * 
    * Constructor Name: ElementoDecorado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: 
    * 
    *****************************************************************************/

    public ElementoDecorado (T element) {
        this.elemento = element;
        this.visitado = false;
        this.antecesor = null;
    }
    
    /*****************************************************************************
    * 
    * Method Name: getter de T
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve el elemento T
    * 
    *****************************************************************************/
    public T elemento() {
        return this.elemento;
    }
    
    /*****************************************************************************
    * 
    * Method Name: getAntecesor
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el ElementoDecorado<T>
    * 
    *****************************************************************************/
    public ElementoDecorado<T> getAntecesor () {
        return antecesor;
    }
    
    /*****************************************************************************
    * 
    * Method Name: setAntecesor
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el antecesor
    * 
    *****************************************************************************/
    public void setAntecesor (ElementoDecorado<T> antecesor){
        this.antecesor = antecesor;
    }   
    
    /*****************************************************************************
    * 
    * Method Name: visitado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene si un elemento ha sido visitado
    * 
    *****************************************************************************/

    public boolean visitado() {
        return this.visitado;
    }
    
    /*****************************************************************************
    * 
    * Method Name: setVisitado
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece en un elemento si ha 
    * sido visitado o no
    * 
    *****************************************************************************/
    public void setVisitado (boolean t) {
        this.visitado = t;
    }
    
    /*****************************************************************************
    * 
    * Method Name: equals
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: compara dos elementos decorados
    * 
    *****************************************************************************/
    public boolean equals (ElementoDecorado n) {
        return elemento.equals(n.elemento());
    }
    
    /*****************************************************************************
    * 
    * Method Name: toString
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve una cadena mostrando el elemento
    * 
    *****************************************************************************/
    @Override
    public String toString() {
        return elemento.toString();
    }
    
}

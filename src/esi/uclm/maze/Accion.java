
package esi.uclm.maze;

/*****************************************************************************
 * 
 * Class Name: Accion
 * Author/s Name: Antonio, Luis y Teresa
 * Description of class: Encargada de recoger las posibles Acciones(mov,
 * el coste del movimiento..)
 * 
 *****************************************************************************/
public class Accion {
    
    //Atributos
    private char mov;
    private int costo_mov;
    private int inc_x;
    private int inc_y;
    
     /*****************************************************************************
     * 
     * Constructor Name: Acción
     * Author/s Name: Antonio, Luis y Teresa
     * Description of constructor: Construye una acción compuesta por
     * (mov,costo_mov,inc_y,inc_x)
     * 
     *****************************************************************************/

    public Accion (char mov, int costo_mov, int inc_y, int inc_x) {
        this.mov = mov;
        this.costo_mov = costo_mov;
        this.inc_y = inc_y;
        this.inc_x = inc_x;
    }

    /*****************************************************************************
    * 
    * Method Name:  getMov
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve el movimiento que realiza la acción
    * 
    *****************************************************************************/

    public char getMov() {
        return mov;
    }
    
    /*****************************************************************************
    * 
    * Method Name:  setMov
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el movimiento que realiza la acción
    * 
    *****************************************************************************/    
    public void setMov(char mov) {
        this.mov = mov;
    }

    /*****************************************************************************
    * 
    * Method Name:  getMov
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el costo que realiza la acción
    * 
    *****************************************************************************/ 
    public int getCosto_mov() {
        return costo_mov;
    }

    /*****************************************************************************
    * 
    * Method Name:  setCosto_mov
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el costo que realiza la acción
    * 
    *****************************************************************************/     
    public void setCosto_mov(int costo_mov) {
        this.costo_mov = costo_mov;
    }
    
    /*****************************************************************************
    * 
    * Method Name:  getInc_x
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: devuelve inc_x 
    * 
    *****************************************************************************/ 
    public int getInc_x() {
        return inc_x;
    }

    /*****************************************************************************
    * 
    * Method Name:  setInc_x
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece inc_x
    * 
    *****************************************************************************/     
    public void setInc_x(int inc_x) {
        this.inc_x = inc_x;
    }

    /*****************************************************************************
    * 
    * Method Name:  getInc_y
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtine inc_y
    * 
    *****************************************************************************/     
    public int getInc_y() {
        return inc_y;
    }

    /*****************************************************************************
    * 
    * Method Name:  setInc_y
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece inc_y
    * 
    *****************************************************************************/     
    public void setInc_y(int inc_y) {
        this.inc_y = inc_y;
    }
}

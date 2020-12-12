package esi.uclm.maze;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************
 * 
 * Class Name: EspacioDeEstados
 * Author/s Name: Antonio, Luis y Teresa
 * 
 *****************************************************************************/
public class EspacioDeEstados {
    //Atributo
    private Estado[][] laberinto;
    
    /*****************************************************************************
    * 
    * Constructor Name: EspacioDeEstados
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: constructor de la clase 
    * 
    *****************************************************************************/ 
    public EspacioDeEstados(Estado[][] laberinto) {
        this.laberinto = laberinto;
    }
    
    /*****************************************************************************
    * 
    * Constructor Name: getSucesores
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene los sucesores a partir de cierto estado
    * y devuelve una lista con ellos
    * 
    *****************************************************************************/
    public List<Sucesor> getSucesores(Estado estado){ 
        List<Sucesor> suc = new ArrayList<>();
        List<Accion> acc = new ArrayList<>(estado.getAcciones());
        
        Estado nuevoEstado;
        for (int i = 0; i < acc.size(); i++) {
            nuevoEstado = estado.getEstado(acc.get(i));
            nuevoEstado.setVecinos(laberinto[nuevoEstado.getFila()][nuevoEstado.getColumna()].getVecinos());
            nuevoEstado.setValue(laberinto[nuevoEstado.getFila()][nuevoEstado.getColumna()].getValue());
            suc.add(new Sucesor (acc.get(i), nuevoEstado, acc.get(i).getCosto_mov() + nuevoEstado.getValue()));
        }
        
        return suc;  
    }
    
    /*****************************************************************************
    * 
    * Method Name: getFila
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el número de filas del laberinto
    * 
    *****************************************************************************/    
    public int getFilas () {
        return this.laberinto.length;
    }
    
    /*****************************************************************************
    * 
    * Method Name: getColumnas
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el número de columnas por las que está compuesto
    * el laberinto
    * 
    *****************************************************************************/    
    public int getColumnas () {
        return this.laberinto[0].length;
    }
}

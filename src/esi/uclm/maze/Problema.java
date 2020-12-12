package esi.uclm.maze;

import esi.uclm.util.JSONParser;

/*****************************************************************************
 * 
 * Class Name: Problema
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: se encarga de construir de forma manual
 * los JSon además de tener otro tipo de métodos
 * 
 *****************************************************************************/
public class Problema {
    
    //Atributos
    private Estado estadoInicial;
    private Estado estadoFinal;
    
    private EspacioDeEstados espacioDeEstados;
    /*****************************************************************************
    * 
    * Constructor Name: Problema
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: constructor de la clase 
    * 
    *****************************************************************************/    
    public Problema (String fileJSON) {
        JSONParser parser = new JSONParser();
        String[] problema = parser.parseToProblema(fileJSON);
        
        Estado[][] laberinto = parser.parseToEstado(problema[2]);
        this.espacioDeEstados = new EspacioDeEstados (laberinto);
        
        problema[0] = problema[0].replace("(", "").replace(")", "").replace(",","").trim();
        String[] casillaInicial = problema[0].split(" ");
        int inicio_x = Integer.parseInt(casillaInicial[0]); int inicio_y = Integer.parseInt(casillaInicial[1]);
        this.estadoInicial = laberinto[inicio_x][inicio_y];
        
        problema[1] = problema[1].replace("(", "").replace(")", "").replace(",","").trim();
        String[] casillaFinal = problema[1].split(" ");
        int final_x = Integer.parseInt(casillaFinal[0]); int final_y = Integer.parseInt(casillaFinal[1]);
        this.estadoFinal = laberinto[final_x][final_y];
    }

    /*****************************************************************************
    * 
    * Method Name: esObjetivo
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: devuelve true si es objetivo y false en 
    * caso contrario 
    * 
    *****************************************************************************/       
    public boolean esObjetivo (Estado estado) {
        return estado.esObjetivo(estadoFinal.getFila(), estadoFinal.getColumna());
    }
    
    /*****************************************************************************
    * 
    * Method Name: getEstadoInicial
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado inicial
    * 
    *****************************************************************************/     
    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    /*****************************************************************************
    * 
    * Method Name: getEstadoInicial
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado inicial
    * 
    *****************************************************************************/   
    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /*****************************************************************************
    * 
    * Method Name: getEstadoFinal
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado final
    * 
    *****************************************************************************/    
    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    /*****************************************************************************
    * 
    * Method Name: setEstadoFinal
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: establece el estado final
    * 
    *****************************************************************************/     
    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    /*****************************************************************************
    * 
    * Method Name: getEspacioDeEstados
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el espacio de estados
    * 
    *****************************************************************************/    
    public EspacioDeEstados getEspacioDeEstados() {
        return espacioDeEstados;
    }

    /*****************************************************************************
    * 
    * Method Name: setEspacioDeEstados
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: establece el espacio de estados
    * 
    *****************************************************************************/  
    public void setEspacioDeEstados(EspacioDeEstados espacioDeEstados) {
        this.espacioDeEstados = espacioDeEstados;
    }
}

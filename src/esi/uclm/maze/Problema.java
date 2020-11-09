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
    private Laberinto laberinto;
    
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
        
        this.laberinto = parser.parseToLaberinto(problema[2]);
        
        problema[0] = problema[0].replace("(", "").replace(")", "").replace(",","").trim();
        String[] casillaInicial = problema[0].split(" ");
        int inicio_x = Integer.parseInt(casillaInicial[0]); int inicio_y = Integer.parseInt(casillaInicial[1]);
        this.estadoInicial = new Estado(inicio_x, inicio_y, laberinto.getNum_vecinos(), laberinto.getId_mov(), laberinto.getMovimientos(), laberinto.getCells()[inicio_x][inicio_y].getValue(), laberinto.getCells()[inicio_x][inicio_y].getVecinos());
        
        problema[1] = problema[1].replace("(", "").replace(")", "").replace(",","").trim();
        String[] casillaFinal = problema[1].split(" ");
        int final_x = Integer.parseInt(casillaFinal[0]); int final_y = Integer.parseInt(casillaFinal[1]);
        this.estadoFinal = new Estado(final_x, final_y, laberinto.getNum_vecinos(), laberinto.getId_mov(), laberinto.getMovimientos(), laberinto.getCells()[final_x][final_y].getValue(), laberinto.getCells()[final_x][final_y].getVecinos());
    }

    /*****************************************************************************
    * 
    * Constructor Name: esObjetivo
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
    * Constructor Name: getEstadoInicial
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado inicial
    * 
    *****************************************************************************/     
    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    /*****************************************************************************
    * 
    * Constructor Name: getEstadoInicial
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado inicial
    * 
    *****************************************************************************/   
    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /*****************************************************************************
    * 
    * Constructor Name: getEstadoFinal
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el estado final
    * 
    *****************************************************************************/    
    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    /*****************************************************************************
    * 
    * Constructor Name: setEstadoFinal
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: establece el estado final
    * 
    *****************************************************************************/     
    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }
    
    /*****************************************************************************
    * 
    * Constructor Name: getLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: obtiene el laberinto
    * 
    *****************************************************************************/     
    public Laberinto getLaberinto() {
        return laberinto;
    }

    /*****************************************************************************
    * 
    * Constructor Name: setLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of constructor: establece el laberinto
    * 
    *****************************************************************************/     
    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }
    
//    ¿Dónde debe ir ubicado? 
//    public List<Sucesor> getSucesores (Estado estado) {
//        List<Sucesor> suc = new ArrayList<>();
//        List<Accion> acc = new ArrayList<>(estado.getAcciones());
//        
//        acc.forEach((accion) -> {
//            suc.add(new Sucesor (accion, estado.getEstado(accion), 1));
//        });
//        
//        return suc;
//    }
}

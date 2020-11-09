/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

import esi.uclm.util.JSONParser;
/**
 *
 * @author pikac
 */
public class Problema {
    
    private Estado estadoInicial;
    private Estado estadoFinal;
    private Laberinto laberinto;
    
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
    
    public boolean esObjetivo (Estado estado) {
        return estado.esObjetivo(estadoFinal.getFila(), estadoFinal.getColumna());
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

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

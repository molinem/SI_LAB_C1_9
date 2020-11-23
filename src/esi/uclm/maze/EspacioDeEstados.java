/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pikac
 */
public class EspacioDeEstados {
    private Estado[][] laberinto;
    
    public EspacioDeEstados(Estado[][] laberinto) {
        this.laberinto = laberinto;
    }
    
    public List<Sucesor> getSucesores(Estado estado){ //metodo que obtiene una lista de los sucesores a partir de cierto estado.
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
}

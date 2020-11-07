/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

import esi.uclm.util.JSONParser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pikac
 */
public class EspacioDeEstados {
    
    private Laberinto estado;
    
    public EspacioDeEstados (String fileJSON) {
        /*** DEBE SER MODIFICADO ***/
        this.estado = new JSONParser().parseToLaberinto(fileJSON);
    }
    
    public List<Sucesor> getSucesores (Laberinto estado) {
        List<Sucesor> suc = new ArrayList<>();
        List<Accion> acc = new ArrayList<>(estado.getAcciones());
        
        acc.forEach((accion) -> {
            suc.add(new Sucesor (accion, estado.getEstado(accion), 1));
        });
        
        return suc;
    }
    
    public Laberinto getEstado () {
        return estado;
    } 
}

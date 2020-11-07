/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.maze;

/**
 *
 * @author pikac
 */
public class Problema {
    
    private EspacioDeEstados espacioDeEstados;
    private Laberinto estadoInicial;
    
    public Problema () {
        this.espacioDeEstados = new EspacioDeEstados("sucesores50x100.json");
        this.estadoInicial = espacioDeEstados.getEstado();
    }
    
    public boolean esObjectivo (Laberinto estado) {
        return estado.esObjetivo();
    }

    public EspacioDeEstados getEspacioDeEstados() {
        return espacioDeEstados;
    }

    public void setEspacioDeEstados(EspacioDeEstados espacioDeEstados) {
        this.espacioDeEstados = espacioDeEstados;
    }

    public Laberinto getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Laberinto estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.test;

import esi.uclm.maze.Frontera;
import esi.uclm.maze.NodoArbol;

/**
 *
 * @author 
 */
public class TestFrontera {
    
    public static void main (String[] args) {
        Frontera frontera = new Frontera();
        
        //COMPROBACION DE SALIDA CORRECTA
        for (int i = 0; i < 1000; i++) {
            frontera.insertar(new NodoArbol());
        }
        
        System.out.println("PRUEBA DE SALIDA -- ORDEN CORRECTO");
        System.out.println("----------------------------------");
        for (int i = 0; i < 1000; i++) {
            System.out.println(frontera.eliminar());
        }   
        
        //PRUEBA DE TIEMPO
        System.out.println("PRUEBA TIEMPOS -- 1000000 NODOS FRONTERA");
        System.out.println("----------------------------------------");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            frontera.insertar(new NodoArbol());
        }
        long end = System.currentTimeMillis();
        System.out.printf("Tiempo de inserción de nodos de la frontera: %d \n", (end - start));
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            frontera.eliminar();
        }
        end = System.currentTimeMillis();
        System.out.printf("Tiempo de sacar nodos de la frontera: %d \n", (end - start));   
    }
}

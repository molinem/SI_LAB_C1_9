package esi.uclm.main;

import esi.uclm.maze.Frontera;
import esi.uclm.maze.NodoArbol;

/*****************************************************************************
 * 
 * Class Name: TestFrontera
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: encargada de realizar el test para verificar 
 * el correcto funcionamiento de la implementación de la Tarea 2
 * 
 *****************************************************************************/
public class TestFrontera {
    
    public static void main (String[] args) {
        Frontera frontera = new Frontera();
        
        //Comprobación de salida correcta
        for (int i = 0; i < 1000; i++) {
            frontera.insertar(new NodoArbol());
        }
        
        System.out.println("PRUEBA DE SALIDA -- ORDEN CORRECTO");
        System.out.println("----------------------------------");
        for (int i = 0; i < 1000; i++) {
            System.out.println(frontera.eliminar());
        }   
        
        //Prueba de tiempo
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

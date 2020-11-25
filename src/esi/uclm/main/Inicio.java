/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.main;

import esi.uclm.maze.Frontera;
import esi.uclm.maze.NodoArbol;
import esi.uclm.maze.Problema;
import esi.uclm.maze.Sucesor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pikac
 */
public class Inicio {

    private final static int PROF_MAX = 1000000;
    private final static boolean CON_PODA = true;
    private static String [] ficheros = {"./ejemplos/problema_5x5.json","./ejemplos/problema_10x10.json", "./ejemplos/problema_25x25.json", "./ejemplos/problema_25x50.json", "./ejemplos/problema_50x25.json", "./ejemplos/problema_50x50.json", "./ejemplos/problema_100x100.json"};
    private static int contador;
    
    // "./ejemplos/problema_10x10.json", "./ejemplos/problema_25x25.json", "./ejemplos/problema_25x50.json", "./ejemplos/problema_50x25.json", "./ejemplos/problema_50x50.json", "./ejemplos/problema_100x100.json"
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String [] estrategias = {"BREADTH", "DEPTH", "UNIFORM", "GREEDY", "A"};
        contador = 0;
        
        for (int i = 0; i < ficheros.length; i++) {
            for (int j=0; j < estrategias.length; j++) {
                busqueda(new Problema(ficheros[i]), estrategias[j], PROF_MAX, CON_PODA);
            }
            contador++;
        }  
    }
    
    public static boolean busqueda(Problema prob, String estrategia, int Prof_Max, boolean conPoda) {
        //Se pone el total a 0
        int total = 0;
        
        //Se crea la frontera vacia
        Frontera frontera = new Frontera();
        frontera.crearFronteraVacia();
        
        //Se crea el nodo inicial y se inserta en la frontera
        NodoArbol n_inicial = new NodoArbol(null, prob.getEstadoInicial(), 0, null, 0, 0);
        frontera.insertar(n_inicial);
        
        //Atributos necesarios para la busqueda
        boolean solucion = false;
        NodoArbol n_actual = null;
        Map<String, Double> nodosVisitados = new HashMap(); // HashMap para la poda de estados repetidos
        Deque<NodoArbol> nodosSolucion = new LinkedList(); // cola doble para almacenar la solución generada

        while (!solucion && !frontera.estaVacia()) {
            n_actual = frontera.eliminar();

            if (prob.esObjetivo(n_actual.getEstado())) {
                solucion = true;
            } else {
                List<Sucesor> LS = prob.getEspacioDeEstados().getSucesores(n_actual.getEstado());
                List<NodoArbol> LN = CreaListaNodosArbol(prob, LS, n_actual, PROF_MAX, estrategia);

                for (NodoArbol nodo : LN) {
                    if (conPoda) { // si se ha elegido poda no se insertan en la frontera los estados repetidos                   
                        String nodoString = nodo.getEstado().getID();
                        if (nodosVisitados.containsKey(nodoString)) {
                            if ((Math.abs(nodo.getF()) < Math.abs(nodosVisitados.get(nodoString)))) {
                                nodo.setID(++total);
                                frontera.insertar(nodo);
                                nodosVisitados.replace(nodoString, nodo.getF());
                            }
                        } else {
                            nodo.setID(++total);
                            nodosVisitados.put(nodoString, nodo.getF());
                            frontera.insertar(nodo);
                        }
                    }
                }
            }
        }
        
        if (solucion) {
            //Si encontramos solucion la introducimos en la cola doble nodosSolucion            
            while (n_actual.getPadre() != null) {
                nodosSolucion.addFirst(n_actual);
                n_actual = n_actual.getPadre();
            }
            
            //Se inserta el nodo inicial y se genera el fichero en la carpeta de la solución
            nodosSolucion.addFirst(n_inicial);
            generarFichero(prob, nodosSolucion, estrategia, total);
        }
        
        return solucion;
    }
    
    public static List<NodoArbol> CreaListaNodosArbol(Problema prob, List<Sucesor> LS, NodoArbol n_actual, int Prof_Max, String estrategia) {
        List<NodoArbol> LN = new ArrayList();
        if (n_actual.getP() < Prof_Max) { // Si aún podemos seguir iterando por no alcanzar la profundidad máxima
            NodoArbol aux = null;
            for (Sucesor sucesor : LS) {
                //Dependiendo de la estrategia generamos los nodos
                switch (estrategia) {
                    case "BREADTH":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getP() + 1);
                        break;
                    case "DEPTH":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, 1/(n_actual.getP() + 1));
                        break;
                    case "UNIFORM":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste());
                        break;
                    case "GREEDY":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, sucesor.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                        break;
                    case "A":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste() + sucesor.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                        break;
                }
                LN.add(aux);
            }
        }
        return LN;
    }
    
    public static void generarFichero(Problema prob, Deque<NodoArbol> camino, String estrategia, int total) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File("./soluciones/sol_" + estrategia + "_" + contador + ".txt")))) {
            pw.println("[id][cost,state,father_id,action,depth,h,value]");
            
            int i = 0;
            for (NodoArbol nodoarbol : camino) {
                if (i == 0) {
                    pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + ",None,None," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + Math.abs((int) nodoarbol.getF()) + "]");
                } else {
                    pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + "," + nodoarbol.getPadre().getID() + "," + nodoarbol.getAccion().getMov() + "," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + Math.abs(nodoarbol.getF()) + "]");
                }
                i++; 
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

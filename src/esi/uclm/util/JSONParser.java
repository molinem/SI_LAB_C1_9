package esi.uclm.util;

import esi.uclm.maze.Laberinto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/*****************************************************************************
 * 
 * Class Name: JSONParser
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: clase encargada de gestionar y parsear ficheros JSON
 * 
 *****************************************************************************/
public class JSONParser {

    /*****************************************************************************
    * 
    * Method Name: parseToJSON
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: parsea el laberinto a json y lo guarda en 
    * la ruta proporcionada por parámetros
    * 
    *****************************************************************************/
    public void parseToJSON (Laberinto laberinto, String ruta) {
        JSONObject principal = new JSONObject();
        principal.put("rows", laberinto.getFilas());
        principal.put("cols", laberinto.getColumnas());
        principal.put("max_n", laberinto.getNum_vecinos());
        
        JSONArray movimientos = new JSONArray();
        JSONArray mov_individual;
        for (int i=0; i < laberinto.getMovimientos().length; i++) {
            mov_individual = new JSONArray (laberinto.getMovimientos()[i]);
            movimientos.put(mov_individual);
        }
        principal.put("mov", movimientos);
        
        JSONArray id_movimientos = new JSONArray(laberinto.getId_mov());
        principal.put("id_mov", id_movimientos);
        
        JSONObject celdas = new JSONObject();
        for (int i=0; i < laberinto.getCells().length; i++) {
            for (int j=0; j < laberinto.getCells()[i].length; j++) {
                String key = "(" + i + ", " + j + ")";
                
                JSONObject value = new JSONObject();
                value.put("value", laberinto.getCells()[i][j].getValue());
                value.put("neighbors", laberinto.getCells()[i][j].getVecinos());
                
                celdas.put(key, value);
            }
        }
        
        principal.put("cells", celdas);
        
        try (FileWriter writer = new FileWriter(ruta + laberinto.getFilas() + "x" + laberinto.getColumnas() + ".json")) {
            writer.write(principal.toString(3));
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR EN EL FICHERO JSON");
        }
    }
    
    /*****************************************************************************
    * 
    * Method Name: parseToLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: lee el fichero y devuelve un objeto laberinto
    * 
    *****************************************************************************/
    public Laberinto parseToLaberinto (String rutaFichero) {
        String fichero = "";
        try (BufferedReader reader = new BufferedReader (new FileReader(rutaFichero))) {  
            String line = reader.readLine();
            while (line != null) {
                fichero = fichero.concat(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL LEER EL FICHERO JSON");
        }
        
        
        JSONObject obj = new JSONObject (fichero);
        int filas = obj.getInt("rows");
        int columnas = obj.getInt("cols");
        int num_vecinos = obj.getInt("max_n");
        
        JSONArray mov = obj.getJSONArray("mov"); JSONArray movimiento_individual;
        int[][] movimientos = new int [mov.length()][mov.getJSONArray(0).length()];
        for (int i=0; i < mov.length(); i++) {
            movimiento_individual = mov.getJSONArray(i);
            for (int j=0; j < movimiento_individual.length(); j++) {
                movimientos[i][j] = movimiento_individual.getInt(j);
            }
        }
        
        JSONArray id_mov = obj.getJSONArray("id_mov");
        char[] id_movimientos = new char[id_mov.length()];
        for (int i=0; i < id_mov.length(); i++) {
            id_movimientos[i] = id_mov.getString(0).charAt(0);
        }
        
        JSONObject celdas = obj.getJSONObject("cells");
        JSONObject celdaAux; JSONArray vecinos; int value; 
        
        Laberinto lab = new Laberinto (filas, columnas, num_vecinos, movimientos, id_movimientos);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdaAux = celdas.getJSONObject("(" + i + ", " + j +")");
                value = celdaAux.getInt("value");
                vecinos = celdaAux.getJSONArray("neighbors");
                
                lab.getCells()[i][j].setValue(value);
                for (int k = 0; k < vecinos.length(); k++) {
                    lab.getCells()[i][j].setPared(k, vecinos.getBoolean(k));
                }
            }
        }
        
        return lab;
    }
    
    /*****************************************************************************
    * 
    * Method Name: parseToProblema
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: lee el fichero y devuelve un array de string con el contenido
    * 
    *****************************************************************************/    
    public String[] parseToProblema (String rutaFichero) {
        String[] problema = new String[3];
        String fichero = "";
        try (BufferedReader reader = new BufferedReader (new FileReader(rutaFichero))) {  
            String line = reader.readLine();
            while (line != null) {
                fichero = fichero.concat(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL LEER EL FICHERO JSON");
        }
        
        JSONObject obj = new JSONObject (fichero);
        problema[0] = obj.getString("INITIAL");
        problema[1] = obj.getString("OBJETIVE");
        problema[2] = obj.getString("MAZE");
        
        return problema;
    } 
}

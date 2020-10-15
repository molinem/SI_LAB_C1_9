/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pikac
 */
public class JSONParser {
    
    private Laberinto laberinto;
    
    public JSONParser () {
        
    }
    
    public void parseToJSON (Laberinto laberinto) {
        this.laberinto = laberinto;
        
        JSONObject principal = new JSONObject();
        principal.put("rows", laberinto.getFilas());
        principal.put("cols", laberinto.getColumnas());
        principal.put("max_n", laberinto.getNum_vecinos());
        
        JSONArray movimientos = new JSONArray();
        JSONArray mov_individual;
        for (int i=0; i < this.laberinto.getMovimientos().length; i++) {
            mov_individual = new JSONArray (this.laberinto.getMovimientos()[i]);
            movimientos.put(mov_individual);
        }
        principal.put("mov", movimientos);
        
        JSONArray id_movimientos = new JSONArray(this.laberinto.getId_mov());
        principal.put("id_mov", id_movimientos);
        
        JSONObject celdas = new JSONObject();
        for (int i=0; i < this.laberinto.getCells().length; i++) {
            for (int j=0; j < this.laberinto.getCells()[i].length; j++) {
                String key = "(" + i + ", " + j + ")";
                
                JSONObject value = new JSONObject();
                value.put("value", this.laberinto.getCells()[i][j].getValue());
                value.put("neighbors", this.laberinto.getCells()[i][j].getVecinos());
                
                celdas.put(key, value);
            }
        }
        
        principal.put("cells", celdas);
        
        try (FileWriter writer = new FileWriter("./laberinto" + laberinto.getFilas() + "x" + laberinto.getColumnas() + ".json")) {
            writer.write(principal.toString(3));
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR EN EL FICHERO JSON");
        }
    }
    
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
        JSONObject celdas = obj.getJSONObject("cells");
        JSONObject celdaAux; JSONArray vecinos; int value; 
        
        
        Laberinto lab = new Laberinto (filas, columnas);
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
    
}

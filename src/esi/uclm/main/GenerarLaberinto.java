/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.uclm.main;

import esi.uclm.Laberinto;
import esi.uclm.DibujaLaberinto;
import esi.uclm.JSONParser;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author pikac
 */
public class GenerarLaberinto {

    private static int leerEntero (Scanner sc, String msg) {
        int num = -1;
        boolean correcto = false;
        
        while (!correcto) {
            try {
                System.out.printf("%s", msg);

                num = sc.nextInt();
                if (num > 0) {
                    correcto = true;
                } else {
                    correcto = false;
                    System.out.println("--- LA FILAS/COLUMNAS DEBEN SER POSITIVAS ---");
                }
                
            } catch (Exception e) {
                correcto = false;
                System.out.println("--- ERROR LEYENDO DATOS ---");
            }
        }
        
        return num;
    }
    
    private static void imprimir(DibujaLaberinto lab){
        //JComponent
        Container contentPane = lab;
        BufferedImage imagen = new BufferedImage(960,960,BufferedImage.TYPE_INT_RGB);
        
        //Background blanco
        Graphics2D drawer = imagen.createGraphics() ;
        drawer.setBackground(Color.WHITE);
        drawer.clearRect(0,0,960,960);
        
        //Super importante
        lab.printAll(imagen.getGraphics());
        
        try {
            ImageIO.write(imagen, "jpg", new File("./laberinto" + lab.getLaberinto().getFilas() + "x" + lab.getLaberinto().getColumnas() + ".jpg"));
        } catch (IOException ex) {
            System.out.println("Se ha producido el siguiente error al generar la imagen "+ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*** PRUEBA 1: GENERAMOS IMAGEN A PARTIR DE ENTRADA DE FILAS Y COLUMNAS POR TECLADO ***/
        Scanner sc = new Scanner (System.in);
        
        /*** Se lee por consola las filas y las columnas ***/
        int filas = leerEntero (sc, "INTRODUCE UN VALOR PARA LAS FILAS: ");
        int columnas = leerEntero (sc, "INTRODUCE UN VALOR PARA LAS COLUMNAS: ");
        
        /*** Se crea el laberinto y se realiza el algoritmo de Wilson ***/
        Laberinto lab_1 = new Laberinto (filas, columnas);
        lab_1.generarLaberinto();
        
        /*** Generar imagen ***/
        JFrame frame = new JFrame("Laberinto");
        frame.setLocationRelativeTo(null);
        
        DibujaLaberinto dibujo = new DibujaLaberinto();

        dibujo.setLaberinto(lab_1);
        dibujo.setSize(960, 960);

        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(dibujo);
        frame.pack();
        
        //Maximizamos la ventana y la hacemos visible
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        
        //Exportamos la imagen(Graphics) a un fichero
        imprimir(dibujo);

        //Exportar el laberinto a JSON
        JSONParser parser = new JSONParser();
        parser.parseToJSON(lab_1);
        
        
        /*** PRUEBA PARA LEER DE FICHERO JSON Y GENERAR IMAGEN ***/
        Laberinto lab_2 = parser.parseToLaberinto("./puzzle_10x10.json");
        
        /*** Generar imagen ***/
        JFrame frame2 = new JFrame("Laberinto 2");
        frame2.setLocationRelativeTo(null);
        
        DibujaLaberinto dibujo2 = new DibujaLaberinto();

        dibujo2.setLaberinto(lab_2);
        dibujo2.setSize(960, 960);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().add(dibujo2);
        frame2.pack();
        
        //Maximizamos la ventana y la hacemos visible
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame2.setVisible(true);
        
        //Exportamos la imagen(Graphics) a un fichero
        imprimir(dibujo2);
    }
}

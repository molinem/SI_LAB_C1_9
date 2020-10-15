
package esi.uclm;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author luismolina
 */
public class DibujaLaberinto extends JComponent {
    
    private Laberinto laberinto;
    
    public DibujaLaberinto(){
        /*** Constructor por defecto ***/
    }
    
    public void setLaberinto(Laberinto lab){
        this.laberinto = lab;
    }
    
    public Laberinto getLaberinto () {
        return this.laberinto;
    }
    
    @Override
    public void paint(Graphics g){
        int x = 0, y = 0;
        int lado;
        
        if (this.laberinto.getFilas() > this.laberinto.getColumnas()) {
            lado = (960 / this.laberinto.getFilas()) - 1;
        } else {
            lado = (960 / this.laberinto.getColumnas()) - 1;
        }
        
        
        for (int i = 0; i < this.laberinto.getCells().length; i++) {
            for (int j = 0; j < this.laberinto.getCells()[i].length; j++) {
                /*** Se dibuja la pared norte ***/
                if (this.laberinto.getCells()[i][j].getVecinos()[0]) {
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, lado, 1);
                
                /*** Se dibuja la pared este ***/
                if (this.laberinto.getCells()[i][j].getVecinos()[3]) {
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, 1, lado);
                
                /*** Dibujamos el cuadrado ***/
                g.setColor(Color.WHITE); g.fillRect(x + 1, y + 1, lado, lado);
                x = x + lado;               
            }
            
            x = 0;
            y = y + lado;
        }
        
        /*** Dibujar bordes final ***/
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 2, lado * this.laberinto.getFilas());
        g.fillRect(0, 0, lado * this.laberinto.getColumnas(), 2);
        g.fillRect(lado * this.laberinto.getColumnas(), 0, 2, lado * this.laberinto.getFilas());
        g.fillRect(0, lado * this.laberinto.getFilas(), lado * this.laberinto.getColumnas() + 2, 2);
    }
}

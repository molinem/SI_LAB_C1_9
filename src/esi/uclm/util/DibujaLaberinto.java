package esi.uclm.util;

import esi.uclm.maze.Laberinto;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/*****************************************************************************
 * 
 * Class Name: DibujaLaberinto
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: clase encargada de dibujar el laberinto
 * 
 *****************************************************************************/
public class DibujaLaberinto extends JComponent {
    
    //Atributos
    private Laberinto laberinto;
    private int x_final;
    private int y_final;
    
    /*****************************************************************************
    * 
    * Method Name: DibujaLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: constructor por defecto
    * 
    *****************************************************************************/
    public DibujaLaberinto(){}
    
    /*****************************************************************************
    * 
    * Method Name: setLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: establece el laberinto
    * 
    *****************************************************************************/
    public void setLaberinto(Laberinto lab){
        this.laberinto = lab;
    }
    
    /*****************************************************************************
    * 
    * Method Name: getLaberinto
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene el laberinto
    * 
    *****************************************************************************/
    public Laberinto getLaberinto () {
        return this.laberinto;
    }
    
    /*****************************************************************************
    * 
    * Method Name: Paint
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: dibuja el laberinto
    * 
    *****************************************************************************/
    @Override
    public void paint(Graphics g){
        int x = 10, y = 10;
        int lado;
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1100, 1100);
        
        if (this.laberinto.getFilas() > this.laberinto.getColumnas()) {
            lado = (960 / this.laberinto.getFilas()) - 1;
        } else {
            lado = (960 / this.laberinto.getColumnas()) - 1;
        }
        
        g.setColor(Color.BLACK);
        for (int i = 0; i < this.laberinto.getCells().length; i++) {
            for (int j = 0; j < this.laberinto.getCells()[i].length; j++) {
                /*** Se dibuja la pared norte ***/
                if (!this.laberinto.getCells()[i][j].getVecinos()[0]) {
                   g.fillRect(x, y, lado, 3); 
                }
                
                /*** Se dibuja la pared este ***/
                if (!this.laberinto.getCells()[i][j].getVecinos()[3]) {
                    g.fillRect(x, y, 3, lado);
                }
                
                x = x + lado;               
            }
            
            x = 10;
            y = y + lado;
        }
        
        
        this.x_final = lado * this.laberinto.getColumnas() + 10;
        this.y_final = y;
        
        /*** Dibujar bordes final ***/
        g.fillRect(10, 10, 5, lado * this.laberinto.getFilas());
        g.fillRect(10, 10, lado * this.laberinto.getColumnas(), 5);
        g.fillRect(lado * this.laberinto.getColumnas() + 10, 10, 5, lado * this.laberinto.getFilas());
        g.fillRect(10, lado * this.laberinto.getFilas() + 10, lado * this.laberinto.getColumnas() + 2, 5);
    }

    /*****************************************************************************
    * 
    * Method Name: getX_final
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: obtiene la x_final
    * 
    *****************************************************************************/
    public int getX_final() {
        return x_final;
    }

    /*****************************************************************************
    * 
    * Method Name: getY_final
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: y_final
    * 
    *****************************************************************************/    
    public int getY_final() {
        return y_final;
    }
    
    
}

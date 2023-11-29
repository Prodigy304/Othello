package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import Logica.Othello;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Image;

public class BotonPersonalizado extends JButton implements ActionListener {
	    
    private boolean tieneColor = false; //  controlar si tiene un color asignado
    private String tipo;    
    private Othello othello;
    private int i = 0,j = 0;
    
    public BotonPersonalizado(Othello othello,String tipoBoton, int i,int j) {
    	this.tipo = tipoBoton;
    	this.othello = othello;
    	this.i = i;
    	this.j = j;
        this.setBorder(new LineBorder(Color.BLACK)); //  borde para separar los botones
        this.setBorderPainted(true);
        this.setBackground(new Color(0,144,103));
        // tam botón
        

        cambiarTipo(tipoBoton);

  
        this.addActionListener(this);
    }

    public void cambiarTipo(String tipoBoton) {
        // Lógica para cambiar el tipo del botón
    	//System.out.println("CAMBIANDO COLOR A "+tipoBoton);
        if (tipoBoton.equalsIgnoreCase("Negro")) {
        	
        	try {
				Image img = ImageIO.read(getClass().getResource("/imagen/Fichanegra.png"));				
				this.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
				this.tipo = tipoBoton;
				
			} 
        	catch (IOException e) {			
				e.printStackTrace();
			}
        	
            
            tieneColor = true; // Establecer que tiene un color asignado
        } else if (tipoBoton.equalsIgnoreCase("Blanco")) {
         	try {
    				Image img = ImageIO.read(getClass().getResource("/imagen/Fichablanca.png"));				
    				this.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
    				this.tipo = tipoBoton;
    			} 
            	catch (IOException e) {			
    				e.printStackTrace();
    			}
            	
            tieneColor = true; 
            
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        if (!tieneColor) {            
            
            othello.ponerFicha(this);
            	                                            
        }
        
    }

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public boolean tieneColor() {
		return tieneColor;
	}
}

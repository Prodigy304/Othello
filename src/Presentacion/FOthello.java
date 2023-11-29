package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Logica.Turno;
import Logica.Othello;
import java.awt.GridLayout;
import java.awt.Image;

public class FOthello extends JFrame {
    
	private BotonPersonalizado[][] botones;
    private final int filas = 8;
    private final int columnas = 8;
    
    
    private Othello othello;
    
    public FOthello() {
        this.setTitle("Othello");
        this.setSize(600, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(filas, columnas));
        
        this.botones = new BotonPersonalizado[filas][columnas];
        othello = new Othello(botones);
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new BotonPersonalizado(othello,"Default",i,j);
                this.add(botones[i][j]); // Asegúrate de agregar los botones al diseño
            }
        }
                                             
        setLocationRelativeTo(null);
        
        othello.jugar();
		
    }


    public static void main(String[] args) {
        FOthello fOthello = new FOthello();
        fOthello.setVisible(true);
    }
}

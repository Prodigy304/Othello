package Presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FOthello extends JFrame {
	private PTablero pTablero;
	
	public FOthello() {
		this.setTitle("Othello");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JButton bIniciar = new JButton("Iniciar");
		this.add(bIniciar, BorderLayout.NORTH);
		bIniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		}); //Por qué???
		
		this.pTablero = new PTablero();
		this.add(this.pTablero, BorderLayout.CENTER);
		
	}
	

	
	
	public static void main(String[] args) {
		FOthello fBlackJack = new FOthello();
		fBlackJack.setVisible(true);
	}

	
	
}

package Presentacion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logica.Othello;

public class PTablero extends JPanel implements MouseListener {
	
	private Othello othello;
	//private boolean perdio;
	
	public PTablero() {
		this.othello = new Othello();
		this.addMouseListener(this);
		this.repaint();
		
	}
	
	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0, 128, 128));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		for(int i=this.getWidth()/8; i<this.getWidth(); i+=this.getWidth()/8) {
		g2d.drawLine(i, 0, i, this.getHeight());
	}
		for(int i=this.getHeight()/8; i<this.getHeight(); i+=this.getHeight()/8) {
			g2d.drawLine(0, i, this.getWidth(), i);
		}

	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

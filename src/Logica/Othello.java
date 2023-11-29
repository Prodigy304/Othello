package Logica;

import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Presentacion.BotonPersonalizado;

public class Othello {
	
	private int[] di = {0,1,1,1,0,-1,-1,-1}; //f ->
	private int[] dj = {1,1,0,-1,-1,-1,0,1}; //c down
	
	private BotonPersonalizado[][] botones;
	private LinkedList<BotonPersonalizado> porCambiar = new LinkedList<>();
	private Turno turno;
	
	public Othello(BotonPersonalizado[][] botones) {
		this.botones = botones;
		this.turno = new Turno();
	}
	
	public boolean ponerFicha(BotonPersonalizado bp) {
		int turno = this.turno.obtenerTurno();
		int botonI = bp.getI();
		int botonJ = bp.getJ();
		String miColor;
		if(turno == 1 )miColor = "Negro";
		else miColor = "Blanco";
		String colorOpuesto;
		
		if(miColor.equalsIgnoreCase("Negro"))
			colorOpuesto = "Blanco";
		else colorOpuesto = "Negro";
		
		boolean movimientoValido = false;
		
		for(int direccion = 0; direccion < di.length ; direccion++) {	
			
			boolean fichaContraria = false;
			int nuevoI = botonI;
			int nuevoJ = botonJ;
			
			while(true) {
				nuevoI += di[direccion];
				nuevoJ += dj[direccion];
				
				//System.out.println(nuevoI + " "+ nuevoJ);
				
				if(nuevoI<0 || nuevoI == botones.length || nuevoJ<0 || nuevoJ == botones[nuevoI].length)break;
				
				BotonPersonalizado mirando = botones[nuevoI][nuevoJ];
				//System.out.println(mirando.getTipo());
				
				if(mirando.getTipo().equalsIgnoreCase(colorOpuesto)) {
					fichaContraria = true;
					//System.out.println("En la direccion "+k+ " en la posicion "+nuevoI+" "+nuevoJ);
					porCambiar.add(mirando);
				}
				
				else if(mirando.getTipo().equalsIgnoreCase("Default")){
					porCambiar.clear();
					//System.out.println("FICHA SIN COLOR " + nuevoI + " "+ nuevoJ);
					break;					
				}
				
				else if(mirando.getTipo().equalsIgnoreCase(miColor)){
					//System.out.println("LLEGUE A UNA FICHA DE MI COLOR "+miColor);
					if(fichaContraria) {
						//System.out.println("ENCONTRE OPUESTA");
						while(!porCambiar.isEmpty()) {
							BotonPersonalizado cambiar = porCambiar.poll();							
							cambiar.cambiarTipo(miColor);
							//import
						}
						
						movimientoValido = true;
					}
					//else System.out.println("NO ENCONTRE OPUESTA");
					break;
				
				}
				
			}
			
		}
		
		if(movimientoValido) {
			if (turno == 1) {
                bp.cambiarTipo("Negro");
            } else {
            	bp.cambiarTipo("Blanco");
            }
        	this.turno.cambiarTurno(); // Cambiar al siguiente turno
        	if(!mostrarPosibilidades(this.turno.obtenerTurno())){
        		this.turno.cambiarTurno();
        		if(!mostrarPosibilidades(this.turno.obtenerTurno())){
        			terminarPartida();
        		}
        	}
        	return true;
        }
		
		return false;
	}

	public boolean comprobarMovimiento(BotonPersonalizado bp,int turno) {
		
		int botonI = bp.getI();
		int botonJ = bp.getJ();
		String miColor;
		if(turno == 1 )miColor = "Negro";
		else miColor = "Blanco";
		String colorOpuesto;
		
		if(miColor.equalsIgnoreCase("Negro"))
			colorOpuesto = "Blanco";
		else colorOpuesto = "Negro";
		
		boolean movimientoValido = false;
		
		for(int k = 0; k < di.length ; k++) {	
			
			boolean fichaContraria = false;
			int nuevoI = botonI;
			int nuevoJ = botonJ;
			
			while(true) {
				
				nuevoI += di[k];
				nuevoJ += dj[k];
				
				//System.out.println(nuevoI + " "+ nuevoJ);
				
				if(nuevoI<0 || nuevoI == botones.length || nuevoJ<0 || nuevoJ == botones[nuevoI].length)break;
				
				BotonPersonalizado mirando = botones[nuevoI][nuevoJ];
				//System.out.println(mirando.getTipo());
				
				if(mirando.getTipo().equalsIgnoreCase(colorOpuesto)) {
					fichaContraria = true;
					//System.out.println("En la direccion "+k+ " en la posicion "+nuevoI+" "+nuevoJ);
					porCambiar.add(mirando);
				}
				
				else if(mirando.getTipo().equalsIgnoreCase("Default")){
					porCambiar.clear();
					//System.out.println("FICHA SIN COLOR " + nuevoI + " "+ nuevoJ);
					break;					
				}
				
				else {
					//System.out.println("LLEGUE A UNA FICHA DE MI COLOR "+miColor);
					if(fichaContraria) {
						return true;
					}
					//else System.out.println("NO ENCONTRE OPUESTA");
					break;
				
				}
				
			}
			
		}
		
		return false;
	}

	public boolean mostrarPosibilidades(int turno) {
		boolean ok = false;
		
		for(int i=0;i<botones.length;i++) {
			for(int j=0;j<botones[i].length;j++) {
				BotonPersonalizado boton = botones[i][j];
				if(boton.tieneColor())continue;
				if(comprobarMovimiento(boton, turno)) {
					ok = true;
					if (turno == 1) {			        		
			        	try {
							Image img = ImageIO.read(getClass().getResource("/imagen/Fichanegraposible.png"));				
							boton.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
							
						} 
			        	catch (IOException e) {			
							e.printStackTrace();
						}
			        	
			            			            
			        } else if (turno == 2) {
			         	try {
			    				Image img = ImageIO.read(getClass().getResource("/imagen/Fichablancaposible.png"));				
			    				boton.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_SMOOTH)));			    				
			    			} 
			            	catch (IOException e) {			
			    				e.printStackTrace();
			    			}
			            				            
			        } else {
			            // Configuración para otros tipos de botones (Default, Ayuda, etc.)
			        }
					
			        
				}
				else if(boton.getTipo().equalsIgnoreCase("Default"))boton.setIcon(null);
				boton.repaint();
			}
		}
		
		return ok;
	}
	
	public void terminarPartida() {
		
		int puntosNegros  = 0;
		int puntosBlancos = 0;
		
		for(int i=0;i<botones.length;i++) {
			for(int j=0;j<botones[i].length;j++) {
				BotonPersonalizado boton = botones[i][j];	
				if(boton.getTipo().equalsIgnoreCase("Negro"))
					puntosNegros++;
				else if(boton.getTipo().equalsIgnoreCase("Blanco"))
					puntosBlancos++;
			}
		}
		Image img = null;
		
		if(puntosBlancos>puntosNegros) {
			try{
				img = ImageIO.read(getClass().getResource("/imagen/Fichablanca.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(puntosNegros>puntosBlancos){
			try{
				img = ImageIO.read(getClass().getResource("/imagen/Fichanegra.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try{
				img = ImageIO.read(getClass().getResource("/imagen/Fichaempate.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "NEGRO = "+puntosNegros+"\nBLANCO = "+puntosBlancos+"\n", "FIN DE PARTIDA", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img));
		//JOptionPane.showMessageDialog(null, "", "Negro = "+puntosNegros+img+"\nBlanco = "+puntosBlancos);
		System.exit(0);
	
	}

	
	public void jugar() {
		inicializarMapa();		
		mostrarPosibilidades(this.turno.obtenerTurno());
	}
	
	private void inicializarMapa() {

    	((BotonPersonalizado) botones[3][4]).cambiarTipo("Negro");
    	((BotonPersonalizado) botones[4][3]).cambiarTipo("Negro");

    	((BotonPersonalizado) botones[3][3]).cambiarTipo("Blanco");
    	((BotonPersonalizado) botones[4][4]).cambiarTipo("Blanco");
    	
    }
}



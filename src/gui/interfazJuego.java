package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Celda;
import logica.Juego;
import logica.Cronometro;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class interfazJuego extends JFrame {
	private JPanel panelInicial;
	private JPanel panelJuego;
	private Juego juego;
	private Timer reloj;
	private Clip sonido;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazJuego frame = new interfazJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interfazJuego() {
		juego = new Juego();
		iniciarPanelInicial();
	}
	
	private void iniciarPanelInicial(){
		setResizable(false);
		setBounds(100, 100, 350, 400);
		setTitle("Sudoku");
		setIconImage(new ImageIcon(getClass().getResource("/img/icono.png")).getImage());
		panelInicial = new JPanel();
		panelInicial.setBackground(new Color(34, 139, 34));
		panelInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelInicial);
		panelInicial.setLayout(new BorderLayout(0, 0));
		iniciarPanelTitulo();
		iniciarPanelMedio();
		iniciarPanelInferio();
		iniciarPanelIzquierda();
		iniciarPanelDerecha();
	}
	
	private void iniciarPanelTitulo(){
		JPanel panelTitulo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTitulo.getLayout();
		panelTitulo.setBackground(new Color(34, 139, 34));
		panelInicial.add(panelTitulo, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("Sudoku");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Serif", Font.BOLD, 60));
		panelTitulo.add(titulo);
	}
	
	private void iniciarPanelMedio(){
		JPanel panelMedio = new JPanel();
		panelMedio.setBackground(new Color(0, 100, 0));
		panelInicial.add(panelMedio, BorderLayout.CENTER);
		panelMedio.setLayout(new BorderLayout(0, 0));
		
		JLabel cartelDificultad = new JLabel("Seleccione la dificultad");
		cartelDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		cartelDificultad.setFont(new Font("Arial", Font.PLAIN, 20));
		cartelDificultad.setForeground(new Color(255, 255, 255));
		panelMedio.add(cartelDificultad, BorderLayout.NORTH);
		
		panelMedio.add(iniciarBotonesDificultad());
	}
	
	private void iniciarPanelInferio(){
		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setVgap(20);
		panelInferior.setBackground(new Color(34, 139, 34));
		panelInicial.add(panelInferior, BorderLayout.SOUTH);
		
		JButton botonEmpezar = new JButton("Comenzar Juego");
		botonEmpezar.setForeground(new Color(255, 255, 255));
		botonEmpezar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonEmpezar.setBackground(Color.DARK_GRAY);
		botonEmpezar.addActionListener(new OyenteBotonIniciar());
		panelInferior.add(botonEmpezar);
	}
	
	private void iniciarPanelIzquierda(){
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(34, 139, 34));
		FlowLayout fl_panelIzquierda = (FlowLayout) panelIzquierda.getLayout();
		fl_panelIzquierda.setHgap(20);
		panelInicial.add(panelIzquierda, BorderLayout.WEST);
	}
	
	private void iniciarPanelDerecha(){
		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(new Color(34, 139, 34));
		FlowLayout fl_panelDerecha = (FlowLayout) panelDerecha.getLayout();
		fl_panelDerecha.setHgap(20);
		panelInicial.add(panelDerecha, BorderLayout.EAST);
	}
	
	private JPanel iniciarBotonesDificultad(){
		JPanel panelBotones = new JPanel();
		JRadioButton[] botones = new JRadioButton[4];
		ButtonGroup grupo = new ButtonGroup();;
		String [] tipos = {"Muy Facil","Facil","Normal","Dificil"};
		panelBotones.setBackground(new Color(0, 100, 0));
		for(int i = 0; i < 4; i++){
            botones[i] = new JRadioButton(tipos[i]);
            botones[i].setPreferredSize(new Dimension(150,35));
            grupo.add(botones[i]);
            botones[i].setBackground(new Color(0, 100, 0));
            botones[i].addActionListener(new OyenteDificultad());
            botones[i].setForeground(Color.white);
            botones[i].setFont(new Font("Arial", Font.BOLD, 12));
            panelBotones.add(botones[i]);
		}
		botones[0].setSelected(true);
		return panelBotones;
	}
	
	private void iniciarPanelJuego(){
		panelInicial.setVisible(false);
		setResizable(true);
		setBounds(100, 100, 620, 620);
		panelJuego = new JPanel();
		panelJuego.setBackground(new Color(34, 139, 34));
		panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelJuego.setLayout(new BorderLayout(0, 0));
		setContentPane(panelJuego);
		iniciarReloj();
		iniciarGrilla();
		iniciarBotonesJuego();
	}
	
	private void iniciarReloj(){
		JPanel panelArriba = new JPanel();
		panelArriba.setBackground(new Color(34, 139, 34));
		panelArriba.setLayout(new GridLayout(3,1));
		
		JLabel titulo = new JLabel("Sudoku",SwingConstants.CENTER);
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Serif", Font.BOLD, 45));
		panelArriba.add(titulo);
		
		JPanel panelReloj = new JPanel();
		panelReloj.setPreferredSize(new Dimension(10,10));
		panelReloj.setBackground(new Color(0, 100, 0));
		panelReloj.setLayout(new GridLayout(1,8));
		Cronometro cronometro = new Cronometro();
		EntidadGraficaReloj imagenesReloj = new EntidadGraficaReloj();
		JLabel[] labels = new JLabel[8];
		
		ActionListener accionTimer = new ActionListener(){
			int segundos;
			int minutos;
			int horas;
			public void actionPerformed(ActionEvent e) {
				try {
				cronometro.actualizar();
				segundos = cronometro.getSegundos();
				minutos = cronometro.getMinutos();
				horas = cronometro.getHoras();
				
				labels[7].setIcon(imagenesReloj.getImagen(segundos%10));
				labels[6].setIcon(imagenesReloj.getImagen(segundos/10));
				labels[4].setIcon(imagenesReloj.getImagen(minutos%10));
				labels[3].setIcon(imagenesReloj.getImagen(minutos/10));
				labels[1].setIcon(imagenesReloj.getImagen(horas%10));
				labels[0].setIcon(imagenesReloj.getImagen(horas/10));
				
				for(int i = 0; i < 8; i++){
					ImageIcon grafico = (ImageIcon) labels[i].getIcon();
					reDimensionar(labels[i],grafico);
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Hubo un problema con la ejecucion del juego.");
	        	System.exit(0);
			}}
		};
		
		reloj = new Timer(cronometro.getTiempoDeActualizacion(),accionTimer);
		
		for(int i = 0; i < 8; i++){
			labels[i] = new JLabel();
			labels[i].setIcon(imagenesReloj.getImagen(0));

			labels[i].addComponentListener(new ComponentAdapter(){
				public void componentResized(ComponentEvent e){
					JLabel label = (JLabel) e.getComponent();
					ImageIcon grafico = (ImageIcon) label.getIcon();
					reDimensionar(label,grafico);
				}
			});	
			panelReloj.add(labels[i]);
		}
		
		labels[5].setIcon(imagenesReloj.getDosPuntos());
		labels[2].setIcon(imagenesReloj.getDosPuntos());
		panelArriba.add(panelReloj);
		panelJuego.add(panelArriba,BorderLayout.NORTH);
		reloj.start();
	}
	
	private void iniciarGrilla(){
			int bordex;
			int bordey;
			JPanel grilla = new JPanel();
			grilla.setBackground(new Color(34, 139, 34));
			grilla.setLayout(new GridLayout(juego.getCantFilaSubPanel(),juego.getCantFilaSubPanel()));
			panelJuego.add(grilla,BorderLayout.CENTER);
			int cantFilaSubPanel = juego.getCantFilaSubPanel();
			
			JPanel paneles[][] = new JPanel[cantFilaSubPanel][cantFilaSubPanel];
			for (int i =0; i< cantFilaSubPanel; i++){
				for (int j =0; j< cantFilaSubPanel; j++){
				        paneles[i][j] = new JPanel();
				        paneles[i][j].setLayout(new GridLayout(juego.getCantFilaSubPanel(),juego.getCantFilaSubPanel()));
				        paneles[i][j].setBackground(new Color(34, 139, 34));
				        paneles[i][j].setBorder(new LineBorder(Color.BLACK, 2, false));
				        grilla.add(paneles[i][j]);
				}
			}
			
			for (int i=0; i< juego.getCantFilas(); i++){
			    int m = (int) i / cantFilaSubPanel;
			    for (int j=0; j<juego.getCantFilas(); j++){
			        int n = (int) (j / cantFilaSubPanel);
			        bordex = 1; 
			        bordey = 1;
			        Celda c = juego.getCelda(i,j);
			        ImageIcon grafico = c.getEntidadGrafica().getGrafico();
			        JLabel label = new JLabel();
			        c.getEntidadGrafica().setLabel(label);
			        if(c.getModificable()){
			        	label.setBackground(new Color(215,215,215));
			        }else{
			        	label.setBackground(new Color(0, 100, 0));
			        }
			        if((i == (juego.getCantFilas() / 3)-1) || (i == ((juego.getCantFilas()/3)*2)-1) || (i == (juego.getCantFilas()-1))){
						bordex = 0;
					}
					if(j == 0 || (j == juego.getCantFilas() / 3) || (j == (juego.getCantFilas()/3)*2)){
						bordey = 0;
					}
					label.setBorder(BorderFactory.createMatteBorder(0, bordey, bordex, 0, Color.gray));
					label.setOpaque(true);
					label.validate();
					label.repaint();
					paneles[m][n].add(label);
			        
			        label.addComponentListener(new ComponentAdapter(){
						@Override
						public void componentResized(ComponentEvent e){
							reDimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});	
					
			        label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							boolean gano = true;
							juego.accionar(c);
							reDimensionar(label,grafico);
							for(int i = 0; i < juego.getCantFilas(); i++){
								for(int j = 0; j < juego.getCantFilas(); j++){
									if(juego.getCelda(i,j).getValor() != null){
										if(juego.getCelda(i,j).getModificable()){
											if(juego.comprobarColumna(i,j) && juego.comprobarFila(i,j) && juego.comprobarPanel(i,j)){
												juego.getCelda(i,j).getEntidadGrafica().getLabel().setBackground(Color.GREEN);
											}else{
												juego.getCelda(i,j).getEntidadGrafica().getLabel().setBackground(Color.RED);
												gano = false;
											}
										}
									}else{
										gano = false;
									}
								}
							}
							if(gano){
								iniciarPantallaFinal();
							}
						}
					});
			    }
			}
		
	}
	
	private void iniciarBotonesJuego(){
		JPanel panelFinal = new JPanel();
		panelFinal.setBackground(new Color(34, 139, 34));
		
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new OyenteBotonCerrar());
		botonCerrar.setForeground(new Color(255, 255, 255));
		botonCerrar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonCerrar.setBackground(Color.DARK_GRAY);
		
		JButton botonActivar = new JButton("Activar Sonido");
		botonActivar.addActionListener(new OyenteActivarSonido());
		botonActivar.setForeground(new Color(255, 255, 255));
		botonActivar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonActivar.setBackground(Color.DARK_GRAY);
		
		JButton botonDesactivar = new JButton("Desactivar Sonido");
		botonDesactivar.addActionListener(new OyenteDesactivarSonido());
		botonDesactivar.setForeground(new Color(255, 255, 255));
		botonDesactivar.setFont(new Font("Arial", Font.PLAIN, 15));
		botonDesactivar.setBackground(Color.DARK_GRAY);
		
		panelFinal.add(botonActivar);
		panelFinal.add(botonCerrar);
		panelFinal.add(botonDesactivar);

		panelJuego.add(panelFinal,BorderLayout.SOUTH);
	}
	
	private void iniciarPantallaFinal(){
		reloj.stop();
    	sonido.stop();
		JOptionPane.showMessageDialog(null,"Felicidades, usted ha encontrado una solucion valida.");
    	System.exit(0);
	}
	
	private class OyenteDificultad implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	if(e.getActionCommand().compareTo("Muy Facil") == 0){
        		juego.setDificultad(0.15f);
        	}
        	if(e.getActionCommand().compareTo("Facil") == 0){
        		juego.setDificultad(0.25f);
        	}
        	if(e.getActionCommand().compareTo("Normal") == 0){
        		juego.setDificultad(0.45f);

        	}
        	if(e.getActionCommand().compareTo("Dificil") == 0){
        		juego.setDificultad(0.65f);

        	}
        }
	}

	private class OyenteBotonIniciar implements ActionListener {
        public void actionPerformed(ActionEvent e){
        	juego.iniciar();
        	if(juego.getError()){
        		JOptionPane.showMessageDialog(null,"El juego no se a podido iniciar correctamente");
        	}else{
        		try{
					sonido = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Sound/Fondo.wav"));
				    sonido.open(inputStream);
				     sonido.loop(sonido.LOOP_CONTINUOUSLY);
				     sonido.start(); 	
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"Hubo un problema con la ejecucion del juego.");
		        	System.exit(0);
				}
        		iniciarPanelJuego();
        	}
        }
    }
	
	private class OyenteBotonCerrar implements ActionListener {
        public void actionPerformed(ActionEvent e){
        	System.exit(0);
        }
    }
	
	private class OyenteActivarSonido implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	sonido.loop(sonido.LOOP_CONTINUOUSLY);
		    sonido.start(); 
        }
    }
	
	private class OyenteDesactivarSonido implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	sonido.stop();
        }
    }
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

}

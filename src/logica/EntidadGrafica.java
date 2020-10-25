package logica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EntidadGrafica {
	private ImageIcon grafico;
	private String[] imagenes;
	private JLabel label;
	
	public EntidadGrafica() {
		label = new JLabel();
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/img/Uno.png", "/img/Dos.png", "/img/Tres.png", "/img/Cuatro.png", "/img/Cinco.png", "/img/Seis.png", "/img/Siete.png", "/img/Ocho.png", "/img/Nueve.png"};
	}
	
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void setLabel(JLabel l){
		label = l;
	}
	
}

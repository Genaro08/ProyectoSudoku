package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class EntidadGraficaReloj {
	private String[] imagenes;
	private ImageIcon grafico;
	private String dosPuntos;
	
	public EntidadGraficaReloj() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/img/RCero.png","/img/RUno.png", "/img/RDos.png", "/img/RTres.png", "/img/RCuatro.png", "/img/RCinco.png", "/img/RSeis.png", "/img/RSiete.png", "/img/ROcho.png", "/img/RNueve.png"};
		this.dosPuntos = "/img/RPuntos.png";
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
	
	public Icon getImagen(int imagen){
		ImageIcon imageIcon = null;
		if(imagen>= 0 && imagen < imagenes.length){
			imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[imagen]));
		}
		return imageIcon;
	}
	
	public Icon getDosPuntos(){
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.dosPuntos));
		return imageIcon;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}

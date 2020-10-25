package logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private boolean modificable;
	
	public Celda() {
		modificable = true;
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
	}
	
	public void actualizar() {
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		entidadGrafica.actualizar(this.valor);
	}
	
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	public void setModificable(boolean x){
		modificable = x;
	}
	
	public boolean getModificable(){
		return modificable;
	}
}

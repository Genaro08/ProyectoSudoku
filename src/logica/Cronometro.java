package logica;

public class Cronometro{
	private int horas;
	private int minutos;
	private int segundos;
	private int tiempoDeActualizacion;
	
	public Cronometro(int t){
		segundos = 0;
		horas = 0;
		minutos = 0;
		tiempoDeActualizacion = t;
	}
	
	public Cronometro(){
		this(1000);
	}
	
	public void actualizar(){
		if(segundos < 59){
			segundos++;
		}else{
			segundos = 0;
			if(minutos < 59){
				minutos++;
			}else{
				minutos = 0;
				if(horas < 99){
					horas++;
				}else{
					horas = 0;
				}
			}
		}
	}
	
	public int getSegundos(){
		return segundos;
	}

	public int getMinutos(){
		return minutos;
	}
	
	public int getHoras(){
		return horas;
	}
	
	public int getTiempoDeActualizacion(){
		return tiempoDeActualizacion;
	}
}

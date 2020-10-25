package logica;

import gui.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Juego {
	private Celda [][] tablero;
	private int cantFilas;
	private boolean error;
	private float dificultad;
	
	public Juego() {
		this.cantFilas = 9;
		tablero = new Celda[this.cantFilas][this.cantFilas];
		error = false;
		dificultad = 0.15f;
	}
	
	public void iniciar(){
		try{
			InputStream in = interfazJuego.class.getClassLoader().getResourceAsStream("Archivo/Sudoku.txt");
			InputStreamReader inr =new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			String linea;
			char caracter;
			char espacio;
			int posicion;
			//Se lee el archivo mientras no se hayan terminado las filas y no haya habiado ningun error.
			for (int i = 0; i<cantFilas && !error; i++){
				if((linea = br.readLine()) != null){
					posicion = 0;
					if(linea.length() != ((cantFilas*2)-1)){
						error = true; 
					}
					//Se lee la fila mientras no se hayan terminado las columnas y no haya habiado ningun error.
					for(int j = 0; j < cantFilas && !error; j++){
						caracter = linea.charAt(posicion);
						posicion++;
						espacio = ' ';
						if(posicion < linea.length()){
							espacio = linea.charAt(posicion);
							posicion++;
						}
						if(caracter >= 49 && caracter <= 57){
							if(posicion == linea.length() || espacio == ' '){
								tablero[i][j] = new Celda();
								tablero[i][j].setValor((int) caracter - 49);
								tablero[i][j].setModificable(false);
							}else{
								error = true;
							}
						}else{
							error = true;
						}
					}
				}else{
					error = true;
				}
			}
			if((linea = br.readLine()) != null){
				error = true;
			}
			if(error == false && comprobarArchivo()){
				remover();
			}else{
				error = true;
			}
			in.close();
			inr.close();
			br.close();
		}catch(Exception e){
			error = true;
		}
	}
	
	private void remover(){
		Random rand = new Random();
		int cantidadRemover = (int) ((cantFilas*cantFilas) * dificultad);
		boolean borro;
		//borro tantas veces como cantidadRemover
		for(int i = 0 ; i < cantidadRemover; i++){
			borro = false;
			//Si no borro ya que la celda era nula sigo intentando remover
			while(!borro){
				int x = rand.nextInt(9);
				int y = rand.nextInt(9);
				if(tablero[x][y].getEntidadGrafica().getGrafico().getImage() != null){
					tablero[x][y] = new Celda();
					borro = true;
				}
			}
		}
	}
	
	private boolean comprobarArchivo(){
		boolean correcto = true;
		//Por cada celda del tablero se comprueba que este bien en su fila, su columna y su panel. 
		for(int i = 0; i < cantFilas && correcto; i++){
			for(int j = 0; j < cantFilas && correcto; j++){
				if(!comprobarFila(i,j) || !comprobarColumna(i,j) || !comprobarPanel(i,j)){
					correcto = false;
				}
			}
		}
		return correcto;
	}
	
	public boolean comprobarFila(int x, int y){
		boolean filaBien = true;
		//Recorre toda la final, para ver si el valor de la celda actual coincide con alguna celda de esta.
		for(int i = 0; i < cantFilas && tablero[x][y].getValor() != null; i++){
			if(y != i){
				if((tablero[x][i].getValor() != null) && (tablero[x][y].getValor() != null)){
					if((tablero[x][y].getValor() == tablero[x][i].getValor())){
						filaBien = false;
					}
				}
			}
		}
		return filaBien;
	}
	
	public boolean comprobarColumna(int x, int y){
		boolean columnaBien = true;
		//Recorre toda la columna, para ver si el valor de la celda actual coincide con alguna celda de esta.
		for(int i = 0; i < cantFilas && tablero[x][y].getValor() != null; i++){
			if(x != i){
				if((tablero[i][y].getValor() != null) && (tablero[x][y].getValor() != null)){
					if((tablero[x][y].getValor() == tablero[i][y].getValor())){
						columnaBien = false;
					}
				}
			}
		}
		return columnaBien;
	}
	
	public boolean comprobarPanel(int x, int y){
		boolean panelBien = true;
		int inicioC = 0;
		int finC = 0;
		int inicioF = 0;
		int finF = 0;
		int cantPanel = (int) Math.sqrt(cantFilas);
		
		inicioC = x - (x % cantPanel);
		finC = inicioC+cantPanel;
		
		inicioF = y - (y % cantPanel);
		finF = inicioF+cantPanel;
		//Recorre toda el panel, para ver si el valor de la celda actual coincide con alguna celda de este.
		for(int i = inicioC; i < finC; i++){
			for(int j = inicioF; j < finF; j++){
				if(!(x == i && y == j)){
					if((tablero[i][j].getValor() != null) && (tablero[x][y].getValor() != null)){
						if(tablero[x][y].getValor() == tablero[i][j].getValor()){
							panelBien = false;
						}
					}
				}
			}
		}
		
		return panelBien;
	}
	
	public void accionar(Celda c) {
		if(c.getModificable()){
			c.actualizar();
		}
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	public int getCantFilas() {
		return this.cantFilas;
	}
	
	public boolean getError(){
		return error;
	}
	
	public void setDificultad(float d){
		if(d >0){
			if(d < 1){
				dificultad = d;
			}else{
				dificultad = 0.65f;
			}
		}else{
			dificultad = 0.15f;
		}
	}
	
	public int getCantFilaSubPanel(){
		return (int) Math.sqrt(cantFilas);
	}
	
}

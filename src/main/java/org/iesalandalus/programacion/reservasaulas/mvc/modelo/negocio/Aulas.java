package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private Aula[] coleccionAulas;
	private int capacidad;
	private int tamano;
	
	// Constructor que acepta la capacidad de esta como parámetro
	public Aulas(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			this.tamano = 0;
			this.coleccionAulas = new Aula[capacidad];
		}
	}

	// Método que devuelve una copia profunda de las aulas
	public Aula[] get() {
		return copiaProfundaAulas();
	}
	
	// Implementación del método de copia profunda
	private Aula[] copiaProfundaAulas() {
		Aula[] copia = new Aula[coleccionAulas.length];
		for(int i = 0; !tamanoSuperado(i); i++) {
			copia[i] = new Aula(coleccionAulas[i]);
		}
		return copia;
	}
	
	// Devuelve el tamaño
	public int getTamano() {
		return tamano;
	}
	
	// Devuelve la capacidad
	public int getCapacidad() {
		return capacidad;
	}
	
	// Inserta un aula pasada por parámetro
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		} else if(capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		} else if(buscarIndice(aula) != tamano) {
			throw new OperationNotSupportedException("ERROR: Ya existe ese aula.");
		}
		
		coleccionAulas[tamano] = new Aula(aula);
		tamano++;
	}
	
	// Comprueba si la capacidad se ha superado y devuelve un booleano como resultado
	private boolean capacidadSuperada(int indice) {
		return indice >= this.capacidad;
	}
	
	// Busca un aula y la devuelve
	public Aula buscar(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		for (int i = 0; i < tamano; i++) {
			Aula c = coleccionAulas[i];
			if(aula.equals(c)) {
				return new Aula(c);
			}
		}
		return null;
	}
	
	// Desplaza el contenido del array una posición hacia la izquierda
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice + 1; i < coleccionAulas.length; i++) {
			coleccionAulas[i-1] = coleccionAulas[i];
		}
	}
	
	// Elimina un aula
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		int indiceAula = buscarIndice(aula);
		if(indiceAula == tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indiceAula);
		tamano--;
	}
	
	// Busca el índice del aula que se pasa por parámetro
	private int buscarIndice(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar el índice de una cita nula.");
		} else {
			int i;
			for(i = 0; i < this.tamano; i++) {
				if(coleccionAulas[i].equals(aula)) {
					return i;
				}
			}
			return i;
		}
	}
	
	// Comprueba si el tamaño se ha superado
	private boolean tamanoSuperado(int indice) {
		return indice >= this.tamano;
	}
	
	// Representación de las aulas
	public String[] representar() {
		String[] aulas = new String[tamano];
		for(int i = 0; !tamanoSuperado(i); i++) {
			aulas[i] = coleccionAulas[i].toString();
		}
		return aulas;
	}
	
}

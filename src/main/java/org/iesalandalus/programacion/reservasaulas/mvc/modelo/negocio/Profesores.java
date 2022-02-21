package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	
	private Profesor[] coleccionProfesores;
	private int capacidad;
	private int tamano;
	
	public Profesores(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			this.tamano = 0;
			this.coleccionProfesores = new Profesor[capacidad];
		}
	}
	
	public Profesor[] get() {
		return copiaProfundaProfesores();
	}
	
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copia = new Profesor[coleccionProfesores.length];
		for(int i = 0; i < coleccionProfesores.length; i++) {
			copia[i] = new Profesor(coleccionProfesores[i]);
		}
		return copia;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		} else if(capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if(buscarIndice(profesor) != tamano) {
			throw new OperationNotSupportedException("ERROR: Ya existe ese un profesor.");
		}
		
		coleccionProfesores[tamano] = new Profesor(profesor);
		tamano++;
	}
	
	private boolean capacidadSuperada(int indice) {
		return indice >= this.capacidad;
	}
	
	private boolean tamanoSuperado(int indice) {
		return indice >= this.tamano;
	}
	
	public Profesor buscar(Profesor profesor) {
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		for (int i = 0; i < tamano; i++) {
			Profesor c = coleccionProfesores[i];
			if(profesor.equals(c)) {
				return new Profesor(c);
			}
		}
		return null;
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice+1; i < coleccionProfesores.length; i++) {
			coleccionProfesores[i-1] = coleccionProfesores[i];
		}
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		int indiceProfesor = buscarIndice(profesor);
		if(indiceProfesor == tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indiceProfesor);
		tamano--;
	}
	
	private int buscarIndice(Profesor profesor) {
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar el índice de una cita nula.");
		} else {
			int i;
			for(i = 0; i < this.tamano; i++) {
				if(coleccionProfesores[i].equals(profesor)) {
					return i;
				}
			}
			return i;
		}
	}
	
	public String[] representar() {
		String[] profesores = new String[tamano];
		for(int i = 0; !tamanoSuperado(i); i++) {
			profesores[i] = coleccionProfesores[i].toString();
		}
		return profesores;
	}
}

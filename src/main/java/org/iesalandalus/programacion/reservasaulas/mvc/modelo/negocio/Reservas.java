package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private Reserva[] coleccionReservas;
	private int capacidad;
	private int tamano;
	
	public Reservas(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.capacidad = capacidad;
			this.tamano = 0;
			this.coleccionReservas = new Reserva[capacidad];
		}
	}
	
	public Reserva[] get() {
		return copiaProfundaReservas();
	}
	
	private Reserva[] copiaProfundaReservas() {
		Reserva[] copia = new Reserva[coleccionReservas.length];
		for(int i = 0; !tamanoSuperado(i); i++) {
			copia[i] = new Reserva(coleccionReservas[i]);
		}
		return copia;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		} else if(reserva.getPermanencia().getDia().compareTo(LocalDate.now()) < 0) {
			throw new IllegalArgumentException("ERROR: No se puede insertar una cita a una fecha pasada.");
		} else if(capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		} else if(buscarIndice(reserva) != tamano) {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva para esa fecha y hora.");
		}
		
		coleccionReservas[tamano] = new Reserva(reserva);
		tamano++;
	}
	
	private int buscarIndice(Reserva reserva) {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar el índice de una reserva nula.");
		} else {
			int i;
			for(i = 0; i < this.tamano; i++) {
				if(coleccionReservas[i].equals(reserva)) {
					return i;
				}
			}
			return i;
		}
	}
	
	private boolean tamanoSuperado(int indice) {
		return indice >= this.tamano;
	}
	
	private boolean capacidadSuperada(int indice) {
		return indice >= this.capacidad;
	}
	
	public Reserva buscar(Reserva reserva) {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		for (int i = 0; i < tamano; i++) {
			Reserva c = coleccionReservas[i];
			if(reserva.equals(c)) {
				return new Reserva(c);
			}
		}
		return null;
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		int indiceReserva = buscarIndice(reserva);
		if(indiceReserva == tamano) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indiceReserva);
		tamano--;
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice+1; i < coleccionReservas.length; i++) {
			coleccionReservas[i-1] = coleccionReservas[i];
		}
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {
		if(profesor == null) {
			throw new NullPointerException("ERROR:");
		} else {
			Reserva[] reservas = new Reserva[coleccionReservas.length];
			for(int i = 0; i < tamano; i++) {
				if(coleccionReservas[i].getProfesor().equals(profesor)) {
					reservas[i] = coleccionReservas[i];
				}
			}
			return reservas;
		}
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		} else {
			Reserva[] reservas = new Reserva[coleccionReservas.length];
			for(int i = 0; i < tamano; i++) {
				if(coleccionReservas[i].getAula().equals(aula)) {
					reservas[i] = coleccionReservas[i];
				}
			}
			return reservas;
		}
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		if(permanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		} else {
			Reserva[] reservas = new Reserva[coleccionReservas.length];
			int acumuladorReserva = 0;
			for(int i = 0; !tamanoSuperado(i); i++) {
				if(coleccionReservas[i].getPermanencia().equals(permanencia)) {
					reservas[acumuladorReserva] = new Reserva(coleccionReservas[i]);
					acumuladorReserva++;
				}
			}
			return reservas;
		}
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}
		
		if(permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		
		for(int i = 0; i < getTamano(); i++) {
			if(coleccionReservas[i].getAula().equals(aula) && coleccionReservas[i].getPermanencia().equals(permanencia)) {
				return false;
			}
		}
		return true;
	}
	
	public String[] representar() {
		String[] reservas = new String[tamano];
		for(int i = 0; !tamanoSuperado(i); i++) {
			reservas[i] = coleccionReservas[i].toString();
		}
		return reservas;
	}
}

package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {

	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Tramo tramo;
	
	// Constructor que acepta un día y un tramo como parámetros
	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}
	
	// Constructor copia
	public Permanencia(Permanencia permanencia) {
		if(permanencia == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		} else {
			setDia(permanencia.getDia());
			setTramo(permanencia.getTramo());
		}
	}

	// Devuelve el día
	public LocalDate getDia() {
		return dia;
	}

	// Establece el día
	private void setDia(LocalDate dia) {
		if(dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		} else {
			this.dia = dia;
		}
	}

	// Devuelve el tramo
	public Tramo getTramo() {
		return tramo;
	}

	// Establece el tramo
	private void setTramo(Tramo tramo) {
		if(tramo == null) {
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		}
		this.tramo = tramo;
	}

	// Métodos hashCode/equals
	@Override
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permanencia other = (Permanencia) obj;
		return Objects.equals(dia, other.dia) && tramo == other.tramo;
	}

	// Método toString que nos muestra el día y el tramo
	@Override
	public String toString() {
		return "dia=" + dia.format(FORMATO_DIA) + ", tramo=" + tramo;
	}
}

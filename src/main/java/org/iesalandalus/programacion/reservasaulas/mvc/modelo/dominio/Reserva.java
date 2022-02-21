package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {

	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;
	
	// Constructor que acepta un  profesor, un aula y una permanencia como parámetros
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}
	
	// Constructor copia
	public Reserva(Reserva reserva) {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		} else {
			setProfesor(reserva.getProfesor());
			setAula(reserva.getAula());
			setPermanencia(reserva.getPermanencia());
		}
	}

	// Devuelve un profesor
	public Profesor getProfesor() {
		return profesor;
	}

	// Establece un profesor para la reserva
	private void setProfesor(Profesor profesor) {
		if(profesor == null) {
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}
		this.profesor = profesor;
	}

	// Devuelve un aula
	public Aula getAula() {
		return aula;
	}

	// Establece un aula para la reserva
	private void setAula(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		this.aula = aula;
	}

	// Devuelve una permanencia
	public Permanencia getPermanencia() {
		return permanencia;
	}

	// Establece una permanencia para la reserva
	private void setPermanencia(Permanencia permanencia) {
		if(permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		this.permanencia = permanencia;
	}

	// Métodos hashCode/equals
	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	}

	// Método toString que muestra el nombre, el correo, el aula y la permanencia de la reserva
	@Override
	public String toString() {
		return "Profesor=nombre=" + profesor.getNombre() + ", correo=" + profesor.getCorreo() + 
				", aula=" + aula + ", permanencia=" + permanencia.toString();
	}
}
